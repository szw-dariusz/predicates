package dev.caladh.validation.predicate;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Factory methods for basic predicates for {@link String}.
 *
 * @author Dariusz Szwarc
 * @implSpec All returned predicates are not null-safe.
 */
public final class StringPredicates {

    private StringPredicates() {
        throw new AssertionError("not allowed");
    }

    public static Predicate<String> isEmpty() {
        return String::isEmpty;
    }

    public static Predicate<String> isNotEmpty() {
        return Predicate.not(String::isEmpty);
    }

    public static Predicate<String> isBlank() {
        return String::isBlank;
    }

    public static Predicate<String> isNotBlank() {
        return Predicate.not(String::isBlank);
    }

    public static Predicate<String> matches(final String pattern) {
        Objects.requireNonNull(pattern);
        return matches(Pattern.compile(pattern));
    }

    public static Predicate<String> matches(final Pattern pattern) {
        Objects.requireNonNull(pattern);
        return pattern.asMatchPredicate();
    }

    public static Predicate<String> contains(final CharSequence cs) {
        Objects.requireNonNull(cs);
        return s -> s.contains(cs);
    }
}
