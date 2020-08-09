package dev.caladh.validation.predicate;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Factory methods for basic predicates for {@link Object}.
 *
 * @author Dariusz Szwarc
 * @implSpec All returned predicates are null-safe.
 */
public final class ObjectPredicates {

    private ObjectPredicates() {
        throw new AssertionError("not allowed");
    }

    public static <T> Predicate<T> isNotNull() {
        return Objects::nonNull;
    }

    public static <T> Predicate<T> isOfType(final Class<T> cls) {
        Objects.requireNonNull(cls);
        return cls::isInstance;
    }
}
