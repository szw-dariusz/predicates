package dev.caladh.validation.predicate;

import java.util.function.LongPredicate;

/**
 * Factory methods for basic predicates for {@link Long}.
 *
 * @author Dariusz Szwarc
 * @implSpec All returned predicates are not null-safe.
 */
public final class LongPredicates {

    private LongPredicates() {
        throw new AssertionError("not allowed");
    }

    public static LongPredicate isEqualTo(final long value) {
        return i -> i == value;
    }

    public static LongPredicate isBetween(final long min, final long max) {
        if (max - min == 2) {
            return isEqualTo(min + 1);
        }
        return isGreaterThan(min).and(isSmallerThan(max));
    }

    public static LongPredicate isGreaterThan(final long value) {
        return n -> n > value;
    }

    public static LongPredicate isSmallerThan(final long value) {
        return n -> n < value;
    }

    public static LongPredicate isPositive() {
        return isGreaterThan(0);
    }

    public static LongPredicate isNegative() {
        return isSmallerThan(0);
    }
}
