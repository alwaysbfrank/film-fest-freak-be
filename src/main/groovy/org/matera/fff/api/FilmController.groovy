package org.matera.fff.api

import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.validation.Validated
import org.matera.fff.films.api.FilmService
import org.matera.fff.films.api.FilmView
import org.matera.fff.films.api.NewFilm

import javax.validation.Valid

@CompileStatic
@Controller("/films")
@ExecuteOn(TaskExecutors.IO)
@Validated
class FilmController {
    private final FilmService filmService

    FilmController(FilmService filmService) {
        this.filmService = filmService
    }

    @Get
    Iterable<FilmView> list() {
        filmService.list()
    }

    @Post
    @Status(HttpStatus.CREATED)
    FilmView save(@Body @Valid NewFilm film) {
        filmService.save(film)
    }

    @Get("/{id}")
    Optional<FilmView> find(@PathVariable String id) {
        filmService.find(id)
    }

    @Get("/search")
    Optional<FilmView> query(@QueryValue @NonNull String title) {
        filmService.findByTitle(title)
    }
}
