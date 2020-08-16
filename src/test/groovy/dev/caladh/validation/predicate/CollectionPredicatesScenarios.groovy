package dev.caladh.validation.predicate

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class CollectionPredicatesScenarios extends Specification {

    def 'Collection of size #sample.size() "Is empty" is #result'() {
        expect:
          CollectionPredicates.isEmpty().test(sample) == result

        where:
          sample || result
          []     || true
          [1]    || false
    }

    def 'Collection of size #sample.size() "Is not empty" is #result'() {
        expect:
          CollectionPredicates.isNotEmpty().test(sample) == result

        where:
          sample || result
          [1]    || true
          []     || false
    }

    def 'Collection of size #sample.size() "Has more elements than #min" is #result'() {
        expect:
          CollectionPredicates.hasMoreElementsThan(min).test(sample) == result

        where:
          sample | min || result
          [1, 2] | 1   || true
          [1, 2] | 0   || true
          [1, 2] | 2   || false
    }

    def 'Collection of size #sample.size() "Has fewer elements than #max" is #result'() {
        expect:
          CollectionPredicates.hasFewerElementsThan(max).test(sample) == result

        where:
          sample | max || result
          [1, 2] | 3   || true
          []     | 1   || true
          [1, 2] | 2   || false
    }

    def 'Collection #sample "Contains #value" is #result'() {
        expect:
          CollectionPredicates.contains(value).test(sample) == result

        where:
          sample | value || result
          [1, 2] | 1     || true
          [1, 2] | 0     || false
          [1, 2] | null  || false
    }

    def 'Collection #sample "Contains an element that #definition" is #result'() {
        expect:
          CollectionPredicates.containsElementThat(condition).test(sample) == result

        where:
          sample | condition     | definition || result
          [1, 2] | (e -> e == 1) | 'is 1'     || true
          [1, 2] | (e -> e == 0) | 'is 0'     || false
    }

    def 'Collection #sample "Contains only elements that #definition" is #result'() {
        expect:
          CollectionPredicates.containsOnlyElementsThat(condition).test(sample) == result

        where:
          sample    | condition         | definition || result
          [2, 4]    | (e -> e % 2 == 0) | 'are even' || true
          [2, 4, 5] | (e -> e % 2 == 0) | 'are even' || false
    }

    def 'Collection #sample "Contains no elements that #definition" is #result'() {
        expect:
          CollectionPredicates.containsNoElementsThat(condition).test(sample) == result

        where:
          sample | condition         | definition || result
          [0, 2] | (e -> e % 2 == 1) | 'are odd'  || true
          [1, 2] | (e -> e % 2 == 1) | 'are odd'  || false
    }

    def 'Collection #sample "Has more elements than #size" and "Contains #value" is #result'() {
        def predicate = CollectionPredicates.hasMoreElementsThan(size).and CollectionPredicates.contains(value)

        expect:
          predicate.test(sample) == result

        where:
          sample    | size | value || result
          [1, 2, 3] | 2    | 3     || true
          [1, 2, 3] | 2    | 4     || false
          [1, 2, 3] | 3    | 3     || false
    }

    def 'Collection #sample "Has more elements than #size" and "Contains an element that #definition" is #result'() {
        given:
          def predicate = CollectionPredicates.hasMoreElementsThan(size).and CollectionPredicates.
              containsElementThat(condition)

        expect:
          predicate.test(sample) == result

        where:
          sample    | size | condition    | definition          || result
          [1, 2, 3] | 2    | (e -> e > 2) | 'is greater than 2' || true
          [1, 2, 3] | 2    | (e -> e > 3) | 'is greater than 3' || false
          [1, 2, 3] | 3    | (e -> e > 3) | 'is greater than 3' || false
    }
}
