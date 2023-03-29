package org.matera.fff.screenings.api

interface ScreeningService {
    ScreeningRoomView addNewScreeningRoom(NewScreeningRoom newScreeningRoom)
    ScreeningView addNewScreening(NewScreening newScreening)
}