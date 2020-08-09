package dev.caladh.validation.predicate;

import java.util.function.IntPredicate;

/**
 * Factory methods for basic predicates for {@link Integer}.
 *
 * @author Dariusz Szwarc
 * @implSpec All returned predicates are not null-safe.
 */
public final class IntegerPredicates {

    private IntegerPredicates() {
        throw new AssertionError("not allowed");
    }

    public static IntPredicate isEqualTo(final int value) {
        return i -> i == value;
    }

    public static IntPredicate isBetween(final int min, final int max) {
        return isGreaterThan(min).and(isSmallerThan(max));
    }

    public static IntPredicate isGreaterThan(final int value) {
        return n -> n > value;
    }

    public static IntPredicate isSmallerThan(final int value) {
        return n -> n < value;
    }

    public static IntPredicate isPositive() {
        return isGreaterThan(0);
    }

    public static IntPredicate isNegative() {
        return isSmallerThan(0);
    }
}
