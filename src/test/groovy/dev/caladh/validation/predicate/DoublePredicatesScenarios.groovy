package dev.caladh.validation.predicate

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class DoublePredicatesScenarios extends Specification {

    def '#a "Is equal to #b" is #result'() {
        expect:
          DoublePredicates.isEqualTo(b).test(a) == result

        where:
          a   | b   || result
          1.0 | 1.0 || true
          1.1 | 1.1 || true
          1.0 | 2.0 || false
    }

    def '#a "Is between #min and #max" is #result'() {
        expect:
          DoublePredicates.isBetween(min, max).test(a) == result

        where:
          a   | min | max || result
          2.0 | 1.0 | 3.0 || true
          1.2 | 1.1 | 1.3 || true
          2.0 | 1.0 | 1.0 || false
    }

    def '#a "Is smaller than #b" is #result'() {
        expect:
          DoublePredicates.isSmallerThan(b).test(a) == result

        where:
          a   | b   || result
          1.0 | 2.0 || true
          1.1 | 1.2 || true
          1.0 | 1.0 || false
    }

    def '#a "Is greater than #b" is #result'() {
        expect:
          DoublePredicates.isGreaterThan(b).test(a) == result

        where:
          a   | b   || result
          2.0 | 1.0 || true
          1.2 | 1.1 || true
          1.0 | 1.0 || false
    }

    def '#a "Is positive" is #result'() {
        expect:
          DoublePredicates.isPositive().test(a) == result

        where:
          a    || result
          1.2  || true
          -1.0 || false
    }

    def '#a "Is negative" is #result'() {
        expect:
          DoublePredicates.isNegative().test(a) == result

        where:
          a    || result
          -1.2 || true
          1.0  || false
    }

    def '#a "Is positive" and "Is smaller than #b" is #result'() {
        given:
          def predicate = DoublePredicates.isPositive().and DoublePredicates.isSmallerThan(b)

        expect:
          predicate.test(a) == result

        where:
          a    | b   || result
          1.0  | 2.0 || true
          -2.0 | 1.0 || false
          1.0  | 1.0 || false
    }
}
