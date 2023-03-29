package org.matera.fff.screenings

import groovy.transform.PackageScope
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
@PackageScope
interface ScreeningRoomRepository extends CrudRepository<ScreeningRoomEntity, String> {

    Optional<ScreeningRoomEntity> findByShortName(String shortName)

}