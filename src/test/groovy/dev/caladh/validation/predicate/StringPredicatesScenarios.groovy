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

    def 'Text "#sample" "Equals to" #text is #result'() {
        expect:
          StringPredicates.equalsTo(text).test(sample) == result

        where:
          sample | text  || result
          'Abc'  | 'Abc' || true
          ''     | ''    || true
          'A'    | 'A'   || true
          'Abc'  | 'ABC' || false
    }

    def 'Text "#sample" "Equals to ignoring case" #text is #result'() {
        expect:
          StringPredicates.equalsToIgnoringCase(text).test(sample) == result

        where:
          sample | text  || result
          'Abc'  | 'abc' || true
          'ABC'  | 'abc' || true
          'Xyz'  | 'abc' || false
    }

    def 'Text "#sample" "Starts with" #text is #result'() {
        expect:
          StringPredicates.startsWith(text).test(sample) == result

        where:
          sample | text || result
          'Abc'  | 'Ab' || true
          'Abc'  | 'A'  || true
          'Abc'  | ''   || true
          'Abc'  | 'AB' || false
    }

    def 'Text "#sample" "Starts with ignoring case" #text is #result'() {
        expect:
          StringPredicates.startsWithIgnoringCase(text).test(sample) == result

        where:
          sample | text || result
          'Abc'  | 'Ab' || true
          'ABC'  | 'Ab' || true
          'ABC'  | ''   || true
          'Xyz'  | 'Ab' || false
    }

    def 'Text "#sample" "Ends with" #text is #result'() {
        expect:
          StringPredicates.endsWith(text).test(sample) == result

        where:
          sample | text || result
          'Abc'  | 'bc' || true
          'Abc'  | 'c'  || true
          'Abc'  | ''   || true
          'Abc'  | 'BC' || false
    }

    def 'Text "#sample" "Ends with ignoring case" #text is #result'() {
        expect:
          StringPredicates.endsWithIgnoringCase(text).test(sample) == result

        where:
          sample | text || result
          'Abc'  | 'bc' || true
          'ABC'  | 'bc' || true
          'ABC'  | ''   || true
          'Xyz'  | 'bc' || false
    }

    def 'Text "#sample" "Is number" is #result'() {
        expect:
          StringPredicates.isNumber().test(sample) == result

        where:
          sample || result
          '123'  || true
          'Abc'  || false
    }

    def 'Text "#sample" "Has length" #length is #result'() {
        expect:
          StringPredicates.hasLength(length).test(sample) == result

        where:
          sample | length || result
          'Abc'  | 3      || true
          ''     | 0      || true
          'Abc'  | 2      || false
    }

    def 'Text "#sample" "Has length between" #min and #max is #result'() {
        expect:
          StringPredicates.hasLengthBetween(min, max).test(sample) == result

        where:
          sample | min | max || result
          'Abc'  | 2   | 5   || true
          'Abc'  | 2   | 4   || true
          'Abc'  | 2   | 3   || false
    }

    def 'Text "#sample" "Is longer than" #length is #result'() {
        expect:
          StringPredicates.isLongerThan(length).test(sample) == result

        where:
          sample | length || result
          'Abc'  | 2      || true
          'Abc'  | 0      || true
          'Abc'  | 3      || false
    }

    def 'Text "#sample" "Is shorter than" #length is #result'() {
        expect:
          StringPredicates.isShorterThan(length).test(sample) == result

        where:
          sample | length || result
          'Abc'  | 4      || true
          ''     | 1      || true
          'Abc'  | 3      || false
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
