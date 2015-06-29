package domain;

import domain.api.Calculator;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.lang.Integer.*;
import static java.lang.String.format;
import static java.lang.String.join;

public class StringCalculator implements Calculator<String> {

    private static final int DEFAULT_VALUE = 0;

    @Override
    public int add(String numbers) {
        return validate(numbers,
            this::sum,                /** onValid */
            (Void) -> DEFAULT_VALUE); /** onInvalid */
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
                this::handleNegatives);
        }
    }

    private void handleNegatives(List<String> numbers) {
            List<String> negatives = numbers
                .stream()
                .mapToInt(s -> parseInt(s))
                .filter(nr -> nr < 0)
                .boxed()
                .map(nr -> nr.toString())
                .collect(Collectors.toList());

            if (! negatives.isEmpty())
                throw new IllegalArgumentException(
                    format("Negative numbers are not allowed: %s", join(", ", negatives)));
    }

    private Predicate<String> isNotValid = (numbers) -> numbers == null || numbers.isEmpty();

    private int sum(String numbers, Consumer<List<String>> onContainingNegatives) {
        Parser parser = Parser.createParserFor(numbers);
        List<String> nrs = parser.getNumbers();

        onContainingNegatives.accept(nrs);

        int sum = nrs
            .stream()
            .mapToInt(s -> parseInt(s))
            .sum();

        return sum;
    }

}
