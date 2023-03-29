package org.matera.fff.screenings

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import groovy.transform.TupleConstructor
import io.micronaut.context.annotation.Value
import jakarta.inject.Singleton
import org.matera.fff.films.api.FilmService
import org.matera.fff.films.api.FilmView
import org.matera.fff.screenings.api.NewScreening
import org.matera.fff.screenings.api.NewScreeningRoom
import org.matera.fff.screenings.api.ScreeningRoomView
import org.matera.fff.screenings.api.ScreeningService
import org.matera.fff.screenings.api.ScreeningView

import java.time.LocalDateTime

@Singleton
@CompileStatic
@PackageScope
@TupleConstructor
class ScreeningServiceImpl implements ScreeningService {

    final ScreeningRoomService screeningRoomService
    final FilmService filmService
    final ScreeningRepository screeningRepository

    @Value('${fff.festival.commercialsInMinutes}')
    Integer commercialsInMinutes

    @Override
    ScreeningRoomView addNewScreeningRoom(NewScreeningRoom newScreeningRoom) {
        screeningRoomService.addNewScreeningRoom(newScreeningRoom)
    }

    @Override
    ScreeningView addNewScreening(NewScreening newScreening) {
        List<FilmView> films = newScreening.filmIds
                .collect {filmService.find(it)}
                .findAll {it.isPresent()}
                .collect{it.get()}

        int totalLength = calculateTotalLength(films, newScreening.extraLength)
        LocalDateTime end = newScreening.start.plusMinutes(totalLength)
        ScreeningRoomEntity roomEntity = screeningRoomService.findByShortName(newScreening.screeningRoomShortName).orElse(null)
        List<String> filmIds = films.collect {it.id }
        ScreeningEntity screeningEntity = new ScreeningEntity(
                filmIds: filmIds,
                screeningRoom: roomEntity,
                start: newScreening.start,
                end: end,
                comment: newScreening.comment,
                extraLength: newScreening.extraLength,
                totalLength: totalLength
        )
        def saveResult = screeningRepository.save(screeningEntity)
        //todo add new screening to film
        convert(saveResult, films)
    }

    private int calculateTotalLength(List<FilmView> films, int extraLength) {
        films.collect { it.duration }.sum() + extraLength + commercialsInMinutes
    }

    private static ScreeningView convert(ScreeningEntity entity, List<FilmView> films) {
        new ScreeningView(
                screeningRoomShortName: entity.screeningRoom.shortName,
                start: entity.start,
                end: entity.end,
                length: entity.totalLength,
                filmTitles: films.collect{it.getTitle()},
                comment: entity.comment
        )
    }
}
