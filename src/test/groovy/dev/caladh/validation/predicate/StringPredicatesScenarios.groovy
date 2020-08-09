package dev.caladh.validation.predicate

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class StringPredicatesScenarios extends Specification {

    def 'String of size #sample.length() "Is empty" is #result'() {
        expect:
          StringPredicates.isEmpty().test(sample) == result

        where:
          sample         || result
          ''             || true
          '\t'           || false
          ' '            || false
          '1'            || false
          '123456qwerty' || false
    }

    def 'String of size #sample.length() "Is not empty" is #result'() {
        expect:
          StringPredicates.isNotEmpty().test(sample) == result

        where:
          sample         || result
          '\t'           || true
          ' '            || true
          '1'            || true
          '123456qwerty' || true
          ''             || false
    }

    def 'Text "#sample" "Is blank" is #result'() {
        expect:
          StringPredicates.isBlank().test(sample) == result

        where:
          sample         || result
          ''             || true
          '\t'           || true
          ' '            || true
          '1'            || false
          '123456qwerty' || false
    }

    def 'Text "#sample" "Is not blank" is #result'() {
        expect:
          StringPredicates.isNotBlank().test(sample) == result

        where:
          sample         || result
          '1'            || true
          '123456qwerty' || true
          ''             || false
          '\t'           || false
          ' '            || false
    }

    def 'Text "#sample" "Matches compiled #pattern" is #result'() {
        given:
          def regex = ~pattern

        expect:
          StringPredicates.matches(regex).test(sample) == result

        where:
          sample | pattern || result
          'B'    | /[A-Z]/ || true
          'BB'   | /[A-Z]/ || false
          ''     | /[A-Z]/ || false
          ' '    | /[A-Z]/ || false
    }

    def 'Text "#sample" "Matches #pattern" is #result'() {
        expect:
          StringPredicates.matches(pattern).test(sample) == result

        where:
          sample | pattern || result
          'B'    | /[A-Z]/ || true
          'BB'   | /[A-Z]/ || false
          ''     | /[A-Z]/ || false
          ' '    | /[A-Z]/ || false
    }

    def 'Text "#sample" "Contains #chars" is #result'() {
        expect:
          StringPredicates.contains(chars).test(sample) == result

        where:
          sample | chars || result
          'B'    | 'B'   || true
          'BB'   | 'BB'  || true
          'B'    | 'BB'  || false
          ' '    | 'B'   || false
    }

    def 'Text "#sample" "Is not blank" and "Contains #chars" is #result'() {
        given:
          def predicate = StringPredicates.isNotBlank().and StringPredicates.contains(chars)

        expect:
          predicate.test(sample) == result

        where:
          sample | chars || result
          'BB'   | 'B'   || true
          ''     | 'B'   || false
          'AA'   | 'B'   || false
    }
}
