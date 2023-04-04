package org.matera.fff.films.api

import java.time.LocalDateTime

class NewFilmScreening {
    String filmId
    String screeningId
    String screeningRoomShortName
    LocalDateTime start
    LocalDateTime end
    Integer length
    String comment
}
