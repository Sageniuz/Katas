package domain;

import domain.api.Calculator;

import java.util.function.Consumer;
import java.util.function.Function;

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

        if (numbers == null || numbers == "") {
            return onInvalid.apply(null);
        } else {
            return onValid.apply(numbers);
        }
    }

    private int sum(String numbers) {
        Parser parser = Parser.create(numbers);

        int sum = parser.getNumbers()
            .stream()
            .mapToInt(s -> parseInt(s))
            .sum();

        return sum;
    }

}
