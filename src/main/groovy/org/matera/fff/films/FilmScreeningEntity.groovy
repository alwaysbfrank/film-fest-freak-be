package org.matera.fff.films

import groovy.transform.PackageScope
import io.micronaut.core.annotation.Introspected
import jakarta.persistence.Embeddable

import java.time.LocalDateTime

@Introspected
@PackageScope
@Embeddable
class FilmScreeningEntity {
    String screeningId
    String screeningRoomShortName
    LocalDateTime start
    LocalDateTime end
    Integer length
    String comment
}
