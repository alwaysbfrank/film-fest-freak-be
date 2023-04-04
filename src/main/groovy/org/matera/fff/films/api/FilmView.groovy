package org.matera.fff.films.api

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class FilmView {
    String id
    String title
    List<String> directors
    String description
    List<String> countries
    int duration
    List<FilmScreeningView> screenings
}
