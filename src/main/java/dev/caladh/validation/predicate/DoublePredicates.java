package dev.caladh.validation.predicate;

import java.util.function.DoublePredicate;

/**
 * Factory methods for basic predicates for {@link Double}.
 *
 * @author Dariusz Szwarc
 * @implSpec All returned predicates are not null-safe.
 */
public final class DoublePredicates {

    private DoublePredicates() {
        throw new AssertionError("not allowed");
    }

    public static DoublePredicate isEqualTo(final double value) {
        return d -> Double.compare(d, value) == 0;
    }

    public static DoublePredicate isBetween(final double min, final double max) {
        return isGreaterThan(min).and(isSmallerThan(max));
    }

    public static DoublePredicate isGreaterThan(final double value) {
        return n -> n > value;
    }

    public static DoublePredicate isSmallerThan(final double value) {
        return n -> n < value;
    }

    public static DoublePredicate isPositive() {
        return isGreaterThan(0.0);
    }

    public static DoublePredicate isNegative() {
        return isSmallerThan(0.0);
    }
}
