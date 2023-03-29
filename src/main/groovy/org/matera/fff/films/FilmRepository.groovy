package org.matera.fff.films

import io.micronaut.core.annotation.NonNull
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
interface FilmRepository extends CrudRepository<Film, String> {

    @NonNull
    Iterable<Film> findByNameInList(@NonNull List<String> names)
}
