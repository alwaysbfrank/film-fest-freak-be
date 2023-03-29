package org.matera.fff.films

import groovy.transform.PackageScope
import org.matera.fff.films.api.FilmView
import org.matera.fff.films.api.NewFilm

@PackageScope
class FilmFactory {

    FilmFactory() {
        throw new IllegalStateException("Do not instantiate")
    }

    static Film toFilm(NewFilm newFilm) {
        new Film(
                title: newFilm.title, 
                directors: newFilm.directors, 
                description: newFilm.description,
                countries: newFilm.countries,
                duration: newFilm.duration
        )
    }
    
    static FilmView toFilmView(Film film) {
        new FilmView(
                id: film.id,
                title: film.title,
                directors: film.directors,
                description: film.description,
                countries: film.countries,
                duration: film.duration
        )
    }
}
