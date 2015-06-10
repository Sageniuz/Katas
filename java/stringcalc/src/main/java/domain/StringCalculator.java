package domain;

import static java.util.Arrays.asList;

public class StringCalculator {

    public static final int DEFAULT_VALUE = 0;

    public int Add(String numbers) {
        if (numbers == null || numbers == "")
            return DEFAULT_VALUE;

        int sum = asList(numbers.split(","))
            .stream()
            .mapToInt(s -> Integer.parseInt(s))
            .sum();

        return sum;
    }
}
