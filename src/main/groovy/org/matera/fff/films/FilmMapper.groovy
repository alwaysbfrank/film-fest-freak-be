package org.matera.fff.films

import org.matera.fff.films.api.FilmView
import org.matera.fff.films.api.NewFilm

interface FilmMapper {
    FilmEntity toEntity(NewFilm newFilm)

    FilmView toView(FilmEntity film)
}