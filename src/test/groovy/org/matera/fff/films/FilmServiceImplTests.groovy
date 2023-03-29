package org.matera.fff.films

import org.matera.fff.films.api.NewFilm
import spock.lang.Specification
import spock.lang.Unroll

class FilmServiceImplTests extends Specification {

    def filmRepository = Mock(FilmRepository)
    def filmService = new FilmServiceImpl(filmRepository)

    def "list should return a list of FilmView objects"() {
        given:
        def film1 = new Film(title: "film1", directors: ["director1"], description: "desc1", countries: ["country1"], duration: 120)
        def film2 = new Film(title: "film2", directors: ["director2"], description: "desc2", countries: ["country2"], duration: 90)
        filmRepository.findAll() >> [film1, film2]

        when:
        def result = filmService.list()

        then:
        result.size() == 2
        result[0].title == "film1"
        result[0].directors == ["director1"]
        result[0].description == "desc1"
        result[0].countries == ["country1"]
        result[0].duration == 120
        result[1].title == "film2"
        result[1].directors == ["director2"]
        result[1].description == "desc2"
        result[1].countries == ["country2"]
        result[1].duration == 90
    }

    def "save should return a FilmView object"() {
        given:
        def newFilm = new NewFilm(title: "newFilm", directors: ["director1"], description: "desc1", countries: ["country1"], duration: 120)
        def savedFilm = new Film(id: "1", title: "newFilm", directors: ["director1"], description: "desc1", countries: ["country1"], duration: 120)
        filmRepository.save(_) >> savedFilm

        when:
        def result = filmService.save(newFilm)

        then:
        result.id == "1"
        result.title == "newFilm"
        result.directors == ["director1"]
        result.description == "desc1"
        result.countries == ["country1"]
        result.duration == 120
    }

    def "find should return an Optional of FilmView object"() {
        given:
        def film = new Film(id: "1", title: "film1", directors: ["director1"], description: "desc1", countries: ["country1"], duration: 120)
        filmRepository.findById("1") >> Optional.of(film)

        when:
        def result = filmService.find("1")

        then:
        result.isPresent()
        result.get().id == "1"
        result.get().title == "film1"
        result.get().directors == ["director1"]
        result.get().description == "desc1"
        result.get().countries == ["country1"]
    }

    def "findByTitle should return an Optional of FilmView object"() {
        given:
        def film = new Film(id: "1", title: "film1", directors: ["director1"], description: "desc1", countries: ["country1"], duration: 120)
        filmRepository.findByTitleInList(["film1"]) >> [film]
        when:
        def optionalResult = filmService.findByTitle("film1")

        then:
        optionalResult.isPresent()

        def result = optionalResult.get()
        result.id == "1"
        result.title == "film1"
        result.directors == ["director1"]
        result.description == "desc1"
        result.countries == ["country1"]
        result.duration == 120
    }

    @Unroll
    def "findByTitle should return an empty Optional when title is '#title'"() {
        given:
        filmRepository.findByTitleInList([title]) >> []

        when:
        def result = filmService.findByTitle(title)

        then:
        !result.isPresent()

        where:
        title           | _
        "invalidTitle1" | _
        "invalidTitle2" | _
        "invalidTitle3" | _
    }
}
