package org.matera.fff.films.api

import groovy.transform.EqualsAndHashCode

import java.time.LocalDateTime

@EqualsAndHashCode
class FilmScreeningView {
    String screeningRoomShortName
    LocalDateTime start
    LocalDateTime end
    Integer length
    String comment
}
