package org.matera.fff.films

import groovy.transform.PackageScope
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import jakarta.persistence.Embedded

@MappedEntity("Film")
@PackageScope
class FilmEntity {

    @Id
    @GeneratedValue
    String id
    String title
    List<String> directors = []
    String description
    List<String> countries = []
    int duration
    @Embedded
    FilmScreeningEntityList screenings = new FilmScreeningEntityList()
}
