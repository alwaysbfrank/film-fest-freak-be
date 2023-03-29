package org.matera.fff.screenings

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import groovy.transform.TupleConstructor
import jakarta.inject.Singleton
import org.matera.fff.screenings.api.NewScreeningRoom
import org.matera.fff.screenings.api.ScreeningRoomView

@Singleton
@CompileStatic
@PackageScope
@TupleConstructor
class ScreeningRoomService {

    final ScreeningRoomRepository screeningRoomRepository

    ScreeningRoomView addNewScreeningRoom(NewScreeningRoom newScreeningRoom) {
        List<ScreeningRoomEntity> entitiesToSave = []
        String newShortName = newScreeningRoom.shortName
        Map<String, Integer> newScreeningRoomDistances = [newShortName: 1]
        newScreeningRoom.distancesByShortName.forEach { key, value ->
            screeningRoomRepository.findByShortName(key).ifPresent {
                it.distancesToOtherRoomsByShortName[newShortName] = value
                entitiesToSave += it
                newScreeningRoomDistances[it.shortName] = value
            }
        }
        String newName = newScreeningRoom.name
        entitiesToSave += new ScreeningRoomEntity(name: newName, shortName: newShortName, distancesToOtherRoomsByShortName: newScreeningRoomDistances)
        screeningRoomRepository.saveAll(entitiesToSave)
        new ScreeningRoomView(shortName: newShortName, name: newName)
    }

    Optional<ScreeningRoomEntity> findByShortName(String shortName) {
        screeningRoomRepository.findByShortName(shortName)
    }
}
