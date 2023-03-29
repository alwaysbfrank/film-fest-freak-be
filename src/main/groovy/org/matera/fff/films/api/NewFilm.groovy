package org.matera.fff.films.api

import io.micronaut.core.annotation.Introspected

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Introspected
class NewFilm {
    @NotBlank
    String title
    @NotEmpty
    List<String> directors
    String description
    @NotEmpty
    List<String> countries
    @Min(1L)
    int duration
}
