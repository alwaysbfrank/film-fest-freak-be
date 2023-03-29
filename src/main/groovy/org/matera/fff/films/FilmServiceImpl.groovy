package org.matera.fff.films

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import io.micronaut.core.annotation.NonNull
import jakarta.inject.Singleton;

@Singleton
@CompileStatic
//@PackageScope
class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository

    FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository
    }

    @Override
    Iterable<Film> list() {
        filmRepository.findAll()
    }

    @Override
    Film save(Film film) {
        if (film.id == null) {
            filmRepository.save(film)
        } else {
            filmRepository.update(film)
        }
    }

    @Override
    Optional<Film> find(@NonNull String id) {
        filmRepository.findById(id)
    }

    @Override
    Iterable<Film> findByNameInList(List<String> names) {
        filmRepository.findByNameInList(names)
    }
}
