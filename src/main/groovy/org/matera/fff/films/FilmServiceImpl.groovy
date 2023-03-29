package org.matera.fff.films

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import io.micronaut.core.annotation.NonNull
import jakarta.inject.Singleton
import org.matera.fff.films.api.FilmService
import org.matera.fff.films.api.FilmView
import org.matera.fff.films.api.NewFilm

@Singleton
@CompileStatic
@PackageScope
class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository

    FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository
    }

    @Override
    List<FilmView> list() {
        filmRepository.findAll().collect {FilmFactory.toFilmView(it)}
    }

    @Override
    FilmView save(NewFilm newFilm) {
        FilmEntity film = FilmFactory.toFilm(newFilm)
        FilmEntity result = saveInternal(film)
        FilmFactory.toFilmView(result)
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
        filmRepository.findById(id).map {FilmFactory.toFilmView(it)}
    }

    @Override
    Optional<FilmView> findByTitle(String title) {
        Optional.ofNullable(filmRepository.findByTitleInList([title]).find{true}).map {FilmFactory.toFilmView(it)}
    }
}
