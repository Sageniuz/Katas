package domain;

import domain.api.Calculator;

import java.util.function.Function;
import java.util.function.Predicate;

import static java.lang.Integer.*;

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
        Function<String, Integer> onValid,
        Function<Void, Integer> onInvalid) {

        if (isNotValid.test(numbers)) {
            return onInvalid.apply(null);
        } else {
            return onValid.apply(numbers);
        }
    }

    private Predicate<String> isNotValid = (numbers) -> numbers == null || numbers.isEmpty();

    private int sum(String numbers) {
        Parser parser = Parser.create(numbers);

        int sum = parser.getNumbers()
            .stream()
            .mapToInt(s -> parseInt(s))
            .sum();

        return sum;
    }

}
