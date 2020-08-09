package dev.caladh.validation.predicate

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class NumberPredicatesScenarios extends Specification {

    def '#a "Is equal to #b" is #result'() {
        expect:
          NumberPredicates.isEqualTo(b).test(a) == result

        where:
          a              | b               || result
          1              | 1               || true
          1.1            | 1.1             || true
          BigDecimal.ONE | BigDecimal.ONE  || true
          1              | 2               || false
          BigDecimal.ONE | BigDecimal.ZERO || false
    }

    def '#a "Is between #min and #max" is #result'() {
        expect:
          NumberPredicates.isBetween(min, max).test(a) == result

        where:
          a              | min             | max             || result
          2              | 1               | 3               || true
          1.2            | 1.1             | 1.3             || true
          BigDecimal.ONE | BigDecimal.ZERO | BigDecimal.TEN  || true
          2              | 1               | 1               || false
          BigDecimal.ONE | BigDecimal.ZERO | BigDecimal.ZERO || false
    }

    def '#a "Is smaller than #b" is #result'() {
        expect:
          NumberPredicates.isSmallerThan(b).test(a) == result

        where:
          a               | b               || result
          1               | 2               || true
          1.1             | 1.2             || true
          BigDecimal.ZERO | BigDecimal.ONE  || true
          1               | 1               || false
          BigDecimal.ZERO | BigDecimal.ZERO || false
    }

    def '#a "Is greater than #b" is #result'() {
        expect:
          NumberPredicates.isGreaterThan(b).test(a) == result

        where:
          a               | b               || result
          2               | 1               || true
          1.2             | 1.1             || true
          BigDecimal.ONE  | BigDecimal.ZERO || true
          1               | 1               || false
          BigDecimal.ZERO | BigDecimal.ZERO || false
    }
}
