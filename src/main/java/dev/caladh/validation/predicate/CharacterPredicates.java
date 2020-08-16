package dev.caladh.validation.predicate;

import java.util.function.Predicate;

/**
 * Factory methods for basic predicates for {@link Character}.
 *
 * @author Dariusz Szwarc
 * @implSpec All returned predicates are null-safe.
 */
public final class CharacterPredicates {

    private CharacterPredicates() {
        throw new AssertionError("not allowed");
    }

    public static Predicate<Character> equalsTo(final char character) {
        return equalsTo(character, false);
    }

    public static Predicate<Character> equalsToIgnoringCase(final char character) {
        return equalsTo(character, true);
    }

    public static Predicate<Character> equalsTo(final char character, final boolean ignoreCase) {
        if (ignoreCase) {
            var normalizedChar = Character.toLowerCase(character);
            return c -> Character.toLowerCase(c) == normalizedChar;
        }
        return c -> c == character;
    }

    public static Predicate<Character> isWhitespace() {
        return Character::isWhitespace;
    }

    public static Predicate<Character> isDigit() {
        return Character::isDigit;
    }

    public static Predicate<Character> isLetter() {
        return Character::isLetter;
    }

    public static Predicate<Character> isUpperCase() {
        return Character::isUpperCase;
    }

    public static Predicate<Character> isLowerCase() {
        return Character::isLowerCase;
    }
}
