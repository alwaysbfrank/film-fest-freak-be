package org.matera.fff.films.api

import io.micronaut.core.annotation.NonNull

interface FilmService {
    List<FilmView> list()

    FilmView save(NewFilm film)

    Optional<FilmView> find(@NonNull String id)

    Optional<FilmView> findByTitle(String s)
}