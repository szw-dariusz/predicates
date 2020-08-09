package dev.caladh.validation.predicate

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ObjectPredicatesScenarios extends Specification {

    def '#sample.getClass().getSimpleName() is "Not null" is #result'() {
        expect:
          ObjectPredicates.isNotNull().test(sample) == result

        where:
          sample       || result
          new Object() || true
          'String'     || true
          1L           || true
          null         || false
    }

    def '#sample.getClass().getSimpleName() "Is of type #type" is #result'() {
        expect:
          ObjectPredicates.isOfType(type).test(sample) == result

        where:
          sample       | type   || result
          1L           | Long   || true
          1L           | Number || true
          null         | Number || false
          new Object() | Number || false
    }

    def '#sample.getClass().getSimpleName() "Is not null" and "Is of type #type" is #result'() {
        given:
          def predicate = ObjectPredicates.isNotNull().and ObjectPredicates.isOfType(type)

        expect:
          predicate.test(sample) == result

        where:
          sample       | type || result
          1L           | Long || true
          null         | Long || false
          new Object() | Long || false
    }
}
