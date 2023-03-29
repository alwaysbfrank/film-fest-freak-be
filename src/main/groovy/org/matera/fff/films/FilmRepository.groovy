package org.matera.fff.films

import groovy.transform.PackageScope
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
@PackageScope
interface FilmRepository extends CrudRepository<Film, String> {

    @NonNull
    List<Film> findByTitleInList(@NonNull List<String> names)
}
