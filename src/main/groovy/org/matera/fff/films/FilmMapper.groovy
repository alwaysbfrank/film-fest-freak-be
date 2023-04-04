package org.matera.fff.films

import groovy.transform.PackageScope
import org.matera.fff.films.api.FilmView
import org.matera.fff.films.api.NewFilm
import org.matera.fff.films.api.NewFilmScreening

@PackageScope
interface FilmMapper {
    FilmEntity toEntity(NewFilm newFilm)
    FilmView toView(FilmEntity film)
    FilmScreeningEntity toScreeningEntity(NewFilmScreening newScreening)
}