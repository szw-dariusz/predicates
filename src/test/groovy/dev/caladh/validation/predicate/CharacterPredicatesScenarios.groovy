package dev.caladh.validation.predicate

import spock.lang.Specification

class CharacterPredicatesScenarios extends Specification {

    def 'Character "#sample" "Equals to" char #character is #result'() {
        expect:
          CharacterPredicates.equalsTo(character as char).test(sample as char) == result

        where:
          sample | character || result
          'c'    | 'c'       || true
          'C'    | 'c'       || false
    }

    def 'Character "#sample" "Equals to ignoring case" char #character is #result'() {
        expect:
          CharacterPredicates.equalsToIgnoringCase(character as char).test(sample as char) == result

        where:
          sample | character || result
          'c'    | 'c'       || true
          'C'    | 'c'       || true
          'A'    | 'c'       || false
    }

    def 'Character "#sample" "Is whitespace" is #result'() {
        expect:
          CharacterPredicates.isWhitespace().test(sample as char) == result

        where:
          sample || result
          '\t'   || true
          'A'    || false
    }

    def 'Character "#sample" "Is digit" is #result'() {
        expect:
          CharacterPredicates.isDigit().test(sample as char) == result

        where:
          sample || result
          '1'    || true
          'A'    || false
    }

    def 'Character "#sample" "Is letter" is #result'() {
        expect:
          CharacterPredicates.isLetter().test(sample as char) == result

        where:
          sample || result
          'A'    || true
          '1'    || false
    }

    def 'Character "#sample" "Is upper case" is #result'() {
        expect:
          CharacterPredicates.isUpperCase().test(sample as char) == result

        where:
          sample || result
          'A'    || true
          'a'    || false
    }

    def 'Character "#sample" "Is lower case" is #result'() {
        expect:
          CharacterPredicates.isLowerCase().test(sample as char) == result

        where:
          sample || result
          'a'    || true
          'A'    || false
    }
}
