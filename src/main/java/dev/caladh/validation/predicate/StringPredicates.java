package dev.caladh.validation.predicate;

import java.util.Objects;
import java.util.function.IntPredicate;
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

    public static Predicate<String> equalsTo(final String text) {
        return equalsTo(text, false);
    }

    public static Predicate<String> equalsToIgnoringCase(final String text) {
        return equalsTo(text, true);
    }

    public static Predicate<String> equalsTo(final String text, final boolean ignoreCase) {
        Objects.requireNonNull(text);
        if (ignoreCase) {
            return text::equalsIgnoreCase;
        }
        return text::equals;
    }

    public static Predicate<String> startsWith(final String text) {
         return startsWith(text, false);
    }

     public static Predicate<String> startsWithIgnoringCase(final String text) {
         return startsWith(text, true);
    }

     public static Predicate<String> startsWith(final String text, final boolean ignoreCase) {
        Objects.requireNonNull(text);
        if (ignoreCase) {
            return s -> s.regionMatches(true, 0, text, 0, text.length());
        }
        return s -> s.startsWith(text);
    }

    public static Predicate<String> endsWith(final String text) {
        return endsWith(text, false);
    }

    public static Predicate<String> endsWithIgnoringCase(final String text) {
        return endsWith(text, true);
    }

    public static Predicate<String> endsWith(final String text, final boolean ignoreCase) {
        Objects.requireNonNull(text);
        if (ignoreCase) {
            return s -> s.regionMatches(true, s.length() - text.length(), text, 0, text.length());
        }
        return s -> s.endsWith(text);
    }

    public static Predicate<String> isNumber() {
        return s -> {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        };
    }

    public static Predicate<String> hasLength(final int length) {
        IntPredicate equalTo = IntegerPredicates.isEqualTo(length);
        return s -> equalTo.test(s.length());
    }

    public static Predicate<String> isShorterThan(final int max) {
        IntPredicate smallerThan = IntegerPredicates.isSmallerThan(max);
        return s -> smallerThan.test(s.length());
    }

    public static Predicate<String> isLongerThan(final int min) {
        IntPredicate greaterThan = IntegerPredicates.isGreaterThan(min);
        return s -> greaterThan.test(s.length());
    }

    public static Predicate<String> hasLengthBetween(final int min, final int max) {
        IntPredicate between = IntegerPredicates.isBetween(min, max);
        return s -> between.test(s.length());
    }
}
