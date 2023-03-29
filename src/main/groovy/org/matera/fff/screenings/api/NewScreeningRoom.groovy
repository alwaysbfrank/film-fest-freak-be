package org.matera.fff.screenings.api

import io.micronaut.core.annotation.Introspected

import javax.validation.constraints.NotBlank

@Introspected
class NewScreeningRoom {
    @NotBlank
    String shortName
    @NotBlank
    String name
    Map<String, Integer> distancesByShortName = [:]

    NewScreeningRoom(String shortName, String name, Map<String, Integer> distancesByShortName = [:]) {
        this.shortName = shortName
        this.name = name
        this.distancesByShortName = distancesByShortName
    }
}
