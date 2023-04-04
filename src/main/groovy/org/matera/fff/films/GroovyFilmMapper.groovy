package org.matera.fff.films

import org.matera.fff.films.api.FilmScreeningView
import org.matera.fff.films.api.FilmView
import org.matera.fff.films.api.NewFilm
import org.matera.fff.films.api.NewFilmScreening

class GroovyFilmMapper implements FilmMapper {
    @Override
    FilmEntity toEntity(NewFilm newFilm) {
        new FilmEntity(newFilm.properties.findAll {it.key != 'class'})
    }

    @Override
    FilmView toView(FilmEntity film) {
        def result = new FilmView(film.properties.findAll {!(it.key as String in ['class', 'screenings'])})
        result.screenings = film.screenings.values.collect {toScreeningView(it)}
        result
    }

    private static FilmScreeningView toScreeningView(FilmScreeningEntity entity) {
        new FilmScreeningView(entity.properties.findAll {!(it.key as String in ['class', 'screeningId'])})
    }

    @Override
    FilmScreeningEntity toScreeningEntity(NewFilmScreening newScreening) {
        new FilmScreeningEntity(newScreening.properties.findAll {!(it.key as String in ['class', 'filmId'])})
    }
}
