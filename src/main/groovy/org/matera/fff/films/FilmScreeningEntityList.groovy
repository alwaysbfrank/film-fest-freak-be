package org.matera.fff.films

import groovy.transform.PackageScope
import jakarta.persistence.Embeddable

@PackageScope
@Embeddable
class FilmScreeningEntityList {
    List<FilmScreeningEntity> values = []
}
