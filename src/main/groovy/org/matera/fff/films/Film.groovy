package org.matera.fff.films

import groovy.transform.PackageScope
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
@PackageScope
class Film {

    @Id
    @GeneratedValue
    String id
    String title
    List<String> directors
    String description
    List<String> countries
    int duration
}
