package org.matera.fff

import spock.lang.Specification

class FffBackendAppTest extends Specification {
    def "application runs"() {
        when:
        FffBackendApp.main()

        then:
        noExceptionThrown()
    }
}
