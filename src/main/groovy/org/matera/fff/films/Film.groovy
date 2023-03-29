package org.matera.fff.films

import io.micronaut.core.annotation.NonNull
import io.micronaut.core.annotation.Nullable
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

import javax.validation.constraints.NotBlank

@MappedEntity
class Film {

    @Id
    @GeneratedValue
    String id

    @NonNull
    @NotBlank
    final String name

    @Nullable
    String description

    Film(@NonNull String name, @Nullable String description) {
        this.name = name
        this.description = description
    }
}
