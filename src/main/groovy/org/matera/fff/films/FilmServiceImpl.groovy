package org.matera.fff.films

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import io.micronaut.core.annotation.NonNull
import jakarta.inject.Singleton
import org.matera.fff.films.api.FilmService
import org.matera.fff.films.api.FilmView
import org.matera.fff.films.api.NewFilm
import org.matera.fff.films.api.NewFilmScreening

@Singleton
@CompileStatic
@PackageScope
class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository
    private final FilmMapper filmMapper

    FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository
        filmMapper = new GroovyFilmMapper()
    }

    @Override
    List<FilmView> list() {
        filmRepository.findAll().collect { filmMapper.toView(it) }
    }

    @Override
    FilmView save(NewFilm newFilm) {
        FilmEntity film = filmMapper.toEntity(newFilm)
        FilmEntity result = saveInternal(film)
        filmMapper.toView(result)
    }

    private FilmEntity saveInternal(FilmEntity film) {
        if (film.id == null) {
            filmRepository.save(film)
        } else {
            filmRepository.update(film)
        }
    }

    @Override
    Optional<FilmView> find(@NonNull String id) {
        filmRepository.findById(id).map { filmMapper.toView(it) }
    }

    @Override
    Optional<FilmView> findByTitle(String title) {
        Optional.ofNullable(filmRepository.findByTitleInList([title]).find { true }).map { filmMapper.toView(it) }
    }

    @Override
    Optional<FilmView> addNewScreening(NewFilmScreening newFilmScreening) {
        filmRepository.findById(newFilmScreening.filmId)
                .map { addNewScreeningInternal(it, newFilmScreening) }
    }

    @Override
    @NonNull List<FilmView> addNewScreenings(@NonNull List<NewFilmScreening> newFilmScreenings) {
        newFilmScreenings.collect { addNewScreening(it) }
                .findAll { it.isPresent() }
                .collect { it.get() }
    }

    private FilmView addNewScreeningInternal(FilmEntity entity, NewFilmScreening newFilmScreening) {
        def screeningEntity = filmMapper.toScreeningEntity(newFilmScreening)
        entity.screenings.values += screeningEntity
        filmRepository.save(entity)
        filmMapper.toView(entity)
    }
}
