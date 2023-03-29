package org.matera.fff.films

import groovy.transform.CompileStatic
import io.micronaut.core.annotation.NonNull
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.annotation.Status
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

import javax.validation.Valid

@CompileStatic
@Controller("/films")
@ExecuteOn(TaskExecutors.IO)
class FilmController {
    private final FilmService filmService

    FilmController(FilmService filmService) {
        this.filmService = filmService
    }

    @Get
    Iterable<Film> list() {
        filmService.list()
    }

    @Post
    @Status(HttpStatus.CREATED)
    Film save(@NonNull @Valid Film film) {
        filmService.save(film)
    }

    @Put
    Film update(@NonNull @Valid Film film) {
        filmService.save(film)
    }

    @Get("/{id}")
    Optional<Film> find(@PathVariable String id) {
        filmService.find(id)
    }

    @Get("/q")
    Iterable<Film> query(@QueryValue @NonNull List<String> names) {
        filmService.findByNameInList(names)
    }
}
