package org.matera.fff.films

import io.micronaut.core.annotation.NonNull

interface FilmService {
    Iterable<Film> list()

    Film save(Film film)

    Optional<Film> find(@NonNull String id)

    Iterable<Film> findByNameInList(List<String> names)
}