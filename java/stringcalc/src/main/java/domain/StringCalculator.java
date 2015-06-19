package domain;

import domain.api.Calculator;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.lang.Integer.*;
import static java.lang.String.format;
import static java.lang.String.join;

public class StringCalculator implements Calculator<String> {

    private static final int DEFAULT_VALUE = 0;

    @Override
    public int add(String numbers) {
        return validate(numbers,
            this::sum,
            (Void) -> DEFAULT_VALUE);
    }

    private int validate(
        String numbers,
        BiFunction<String, Consumer<List<String>>, Integer> onValid,
        Function<Void, Integer> onInvalid) {

        if (isNotValid.test(numbers)) {
            return onInvalid.apply(null);
        } else {
            return onValid.apply(
                numbers,
                (nrs) -> {
                    List<String> negatives = nrs
                        .stream()
                        .mapToInt(s -> parseInt(s))
                        .filter(nr -> nr < 0)
                        .boxed()
                        .map(nr -> nr.toString())
                        .collect(Collectors.toList());

                    if (! negatives.isEmpty())
                        throw new IllegalArgumentException(
                            format("Negative numbers are not allowed: %s", join(",", negatives)));
                });
        }
    }

    private Predicate<String> isNotValid = (numbers) -> numbers == null || numbers.isEmpty();

    private int sum(String numbers, Consumer<List<String>> handleNegatives) {
        Parser parser = Parser.create(numbers);

        handleNegatives.accept(parser.getNumbers());

        int sum = parser.getNumbers()
            .stream()
            .mapToInt(s -> parseInt(s))
            .sum();

        return sum;
    }

}
