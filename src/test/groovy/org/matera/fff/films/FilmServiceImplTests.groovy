package org.matera.fff.films

import org.matera.fff.films.api.FilmScreeningView
import org.matera.fff.films.api.FilmView
import org.matera.fff.films.api.NewFilm
import org.matera.fff.films.api.NewFilmScreening
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class FilmServiceImplTests extends Specification {

    def filmRepository = Mock(FilmRepository)
    def filmService = new FilmServiceImpl(filmRepository)

    def "list should return a list of FilmView objects"() {
        given:
        def film1 = new FilmEntity(title: "film1", directors: ["director1"], description: "desc1", countries: ["country1"], duration: 120)
        def film2 = new FilmEntity(title: "film2", directors: ["director2"], description: "desc2", countries: ["country2"], duration: 90)
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
        def savedFilm = new FilmEntity(id: "1", title: "newFilm", directors: ["director1"], description: "desc1", countries: ["country1"], duration: 120)
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
        def film = new FilmEntity(id: "1", title: "film1", directors: ["director1"], description: "desc1", countries: ["country1"], duration: 120)
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
        def film = new FilmEntity(id: "1", title: "film1", directors: ["director1"], description: "desc1", countries: ["country1"], duration: 120)
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


    def "addNewScreening adds new screening to film"() {

        // Set up test data
        given:
        def newFilmScreening = new NewFilmScreening(
                filmId: "1",
                screeningId: "s1",
                screeningRoomShortName: "A1",
                start: LocalDateTime.of(2023, 4, 15, 18, 0),
                end: LocalDateTime.of(2023, 4, 15, 20, 0),
                length: 120,
                comment: "Opening Night"
        )

        // Define expected filmView
        def expectedFilmView = new FilmView(
                id: "1",
                title: "Film Title",
                directors: ["Director 1", "Director 2"],
                description: "Film Description",
                countries: ["USA"],
                duration: 120,
                screenings: [
                        new FilmScreeningView(
                                screeningRoomShortName: "A1",
                                start: LocalDateTime.of(2023, 4, 15, 18, 0),
                                end: LocalDateTime.of(2023, 4, 15, 20, 0),
                                length: 120,
                                comment: "Opening Night"
                        )
                ]
        )

        // Set up the film entity
        def filmEntity = new FilmEntity(
                id: "1",
                title: "Film Title",
                directors: ["Director 1", "Director 2"],
                description: "Film Description",
                countries: ["USA"],
                duration: 120,
                screenings: []
        )

        // Configure the stubs
        filmRepository.findById("1") >> Optional.of(filmEntity)

        when:
        def result = filmService.addNewScreening(newFilmScreening)

        then:
        result.get() == expectedFilmView
    }
}
