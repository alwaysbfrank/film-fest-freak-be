package org.matera.fff.films

import org.matera.fff.films.api.FilmView
import org.matera.fff.films.api.NewFilm

class GroovyFilmMapper implements FilmMapper {
    @Override
    FilmEntity toEntity(NewFilm newFilm) {
        new FilmEntity(newFilm.properties.findAll {it.key != 'class'})
    }

    @Override
    FilmView toView(FilmEntity film) {
        new FilmView(film.properties.findAll {it.key != 'class'})
    }
}
