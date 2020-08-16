package dev.caladh.validation.predicate;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Factory methods for basic predicates for {@link Collection}.
 *
 * @author Dariusz Szwarc
 * @implSpec All returned predicates are not null-safe.
 */
public final class CollectionPredicates {

    private CollectionPredicates() {
        throw new AssertionError("not allowed");
    }

    public static <T extends Collection<?>> Predicate<T> isEmpty() {
        return Collection::isEmpty;
    }

    public static <T extends Collection<?>> Predicate<T> isNotEmpty() {
        return Predicate.not(Collection::isEmpty);
    }

    public static <T extends Collection<?>> Predicate<T> hasMoreElementsThan(final int min) {
        if (min == 0) {
            return isNotEmpty();
        }
        var greaterThan = IntegerPredicates.isGreaterThan(min);
        return c -> greaterThan.test(c.size());
    }

    public static <T extends Collection<?>> Predicate<T> hasFewerElementsThan(final int max) {
        if (max == 1) {
            return isEmpty();
        }
        var smallerThan = IntegerPredicates.isSmallerThan(max);
        return c -> smallerThan.test(c.size());
    }

    public static <T extends Collection<E>, E> Predicate<T> contains(final E element) {
        return c -> c.contains(element);
    }

    public static <T extends Collection<E>, E> Predicate<T> containsElementThat(final Predicate<? super E> predicate) {
        Objects.requireNonNull(predicate);
        return c -> c.stream().anyMatch(predicate);
    }

    public static <T extends Collection<E>, E> Predicate<T> containsOnlyElementsThat(final Predicate<? super E> predicate) {
        Objects.requireNonNull(predicate);
        return c -> c.stream().allMatch(predicate);
    }

    public static <T extends Collection<E>, E> Predicate<T> containsNoElementsThat(final Predicate<? super E> predicate) {
        Objects.requireNonNull(predicate);
        return c -> c.stream().noneMatch(predicate);
    }
}
