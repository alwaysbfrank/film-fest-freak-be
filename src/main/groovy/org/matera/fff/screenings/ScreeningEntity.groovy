package org.matera.fff.screenings

import groovy.transform.PackageScope
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

import java.time.LocalDateTime

@MappedEntity("Screening")
@PackageScope
class ScreeningEntity {
    @Id
    @GeneratedValue
    String id

    List<String> filmIds

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screening_room_id")
    ScreeningRoomEntity screeningRoom

    LocalDateTime start
    LocalDateTime end
    String comment
    Integer extraLength
    Integer totalLength
}
