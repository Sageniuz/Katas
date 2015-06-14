package domain;

import domain.api.Calculator;

import static java.lang.Integer.*;

public class StringCalculator implements Calculator<String> {

    private static final int DEFAULT_VALUE = 0;

    @Override
    public int add(String numbers) {
        if (numbers == null || numbers == "") {
            return DEFAULT_VALUE;
        }

        Parser parser = Parser.create(numbers);

        int sum = parser.getNumbers()
            .stream()
            .mapToInt(s -> parseInt(s))
            .sum();

        return sum;
    }

}
