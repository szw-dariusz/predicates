# Validation

This library is a collection of Predicates wrapping basic tests like object being non-null or a number being greater than some value.
 Their aim is to simplify validation logic and allow better reuse of common rules.

Currently, there are predicates for following classes
- [java.lang.Object](src/main/java/dev/caladh/validation/predicate/ObjectPredicates.java)
- [java.lang.Number](src/main/java/dev/caladh/validation/predicate/NumberPredicates.java) (requires objects to implement Comparable interface)
- [java.lang.Integer](src/main/java/dev/caladh/validation/predicate/IntegerPredicates.java)
- [java.lang.Long](src/main/java/dev/caladh/validation/predicate/LongPredicates.java)
- [java.lang.Double](src/main/java/dev/caladh/validation/predicate/DoublePredicates.java)
- [java.lang.String](src/main/java/dev/caladh/validation/predicate/StringPredicates.java)
- [java.util.Collection](src/main/java/dev/caladh/validation/predicate/CollectionPredicates.java)

Majority of predicates are not null-safe and may throw NullPointerException when used to test null objects.

## Usage
The predicates can be used alone like

``` java
import dev.caladh.validation.predicate.IntegerPredicates;

import java.util.ArrayList;
import java.util.List;

class Years {

    static List<Integer> getYearsIn20thCentury(List<Integer> years) {
        return years.stream()
                    .mapToInt(Integer::intValue)
                    .filter(IntegerPredicates.isBetween(1899, 2000))
                    .collect(ArrayList::new, List::add, List::addAll);
    }
}
```

Or combined into longer chains using Java API. In this case it may be necessary to provide explicit type parameter for first call, as show in example below.

``` java
import dev.caladh.validation.predicate.ObjectPredicates;
import dev.caladh.validation.predicate.StringPredicates;

public class Name {

    private final String value;

    public Name(String value) {
        if (ObjectPredicates.<String>isNotNull().and(StringPredicates.isNotBlank())
                                                .and(StringPredicates.matches("[A-Za-z ]"))
                                                .negate()
                                                .test(value)) {
            throw new IllegalArgumentException("This name is not valid!");
        }
        this.value = value;
    }
}
```

## Author
Dariusz Szwarc

## Versioning
Project uses [SemVer](https://semver.org).

## License
See [license](LICENSE.txt)
