package org.matera.fff

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@MicronautTest
class ArchitectureTests extends Specification {

    void 'no cyclic dependencies'() {
        given:
        def rule = slices().matching("org.matera.fff.(*)..").should().beFreeOfCycles()
        def classes = new ClassFileImporter([new ImportOption.DoNotIncludeTests()]).importPackages("org.matera.fff")

        expect:
        rule.check(classes)
    }
}
