package org.matera.fff.screenings.api

import java.time.LocalDateTime

class ScreeningView {
    String screeningRoomShortName
    LocalDateTime start
    LocalDateTime end
    Integer length
    List<String> filmTitles
    String comment
}
