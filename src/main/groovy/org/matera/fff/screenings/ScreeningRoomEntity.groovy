package org.matera.fff.screenings

import groovy.transform.PackageScope
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.Index
import io.micronaut.data.annotation.Indexes
import io.micronaut.data.annotation.MappedEntity

@PackageScope
@MappedEntity("Room")
@Indexes([
        @Index(name = 'shortName', unique = true)
])
class ScreeningRoomEntity {
    @Id
    @GeneratedValue
    String id
    String name
    String shortName
    Map<String, Integer> distancesToOtherRoomsByShortName
}
