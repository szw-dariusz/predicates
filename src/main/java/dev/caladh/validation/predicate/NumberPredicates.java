package dev.caladh.validation.predicate;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Factory methods for basic predicates for {@link Number}. Predicates in this class require that the tested object
 * also extends {@link Comparable}.
 *
 * @author Dariusz Szwarc
 * @implSpec All returned predicates are not null-safe.
 */
public final class NumberPredicates {

    private NumberPredicates() {
        throw new AssertionError("not allowed");
    }

    public static <T extends Number & Comparable<T>> Predicate<T> isEqualTo(final T number) {
        Objects.requireNonNull(number);
        return n -> n.compareTo(number) == 0;
    }

    public static <T extends Number & Comparable<T>> Predicate<T> isBetween(final T min, final T max) {
        return isGreaterThan(min).and(isSmallerThan(max));
    }

    public static <T extends Number & Comparable<T>> Predicate<T> isGreaterThan(final T number) {
        Objects.requireNonNull(number);
        return n -> n.compareTo(number) > 0;
    }

    public static <T extends Number & Comparable<T>> Predicate<T> isSmallerThan(final T number) {
        Objects.requireNonNull(number);
        return n -> n.compareTo(number) < 0;
    }
}
