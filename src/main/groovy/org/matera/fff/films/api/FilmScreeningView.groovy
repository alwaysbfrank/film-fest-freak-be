package org.matera.fff.films.api

import java.time.LocalDateTime

class FilmScreeningView {
    String screeningRoomShortName
    LocalDateTime start
    LocalDateTime end
    Integer length
    String comment
}
