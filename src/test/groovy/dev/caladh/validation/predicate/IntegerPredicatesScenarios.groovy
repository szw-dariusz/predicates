package dev.caladh.validation.predicate

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class IntegerPredicatesScenarios extends Specification {

    def '#a "Is equal to #b" is #result'() {
        expect:
          IntegerPredicates.isEqualTo(b).test(a) == result

        where:
          a | b || result
          1 | 1 || true
          1 | 2 || false
    }

    def '#a "Is between #min and #max" is #result'() {
        expect:
          IntegerPredicates.isBetween(min, max).test(a) == result

        where:
          a | min | max || result
          2 | 1   | 3   || true
          2 | 1   | 1   || false
    }

    def '#a "Is smaller than #b" is #result'() {
        expect:
          IntegerPredicates.isSmallerThan(b).test(a) == result

        where:
          a | b || result
          1 | 2 || true
          1 | 1 || false
    }

    def '#a "Is greater than #b" is #result'() {
        expect:
          IntegerPredicates.isGreaterThan(b).test(a) == result

        where:
          a | b || result
          2 | 1 || true
          1 | 1 || false
    }

    def '#a "Is positive" is #result'() {
        expect:
          IntegerPredicates.isPositive().test(a) == result

        where:
          a  || result
          1  || true
          -1 || false
    }

    def '#a "Is negative" is #result'() {
        expect:
          IntegerPredicates.isNegative().test(a) == result

        where:
          a  || result
          -1 || true
          1  || false
    }

    def '#a "Is positive" and "Is smaller than #b" is #result'() {
        given:
          def predicate = IntegerPredicates.isPositive().and IntegerPredicates.isSmallerThan(b)

        expect:
          predicate.test(a) == result

        where:
          a  | b || result
          1  | 2 || true
          -2 | 1 || false
          1  | 1 || false
    }
}
