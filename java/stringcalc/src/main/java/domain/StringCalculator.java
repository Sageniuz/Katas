package domain;

import static java.util.Arrays.asList;

public class StringCalculator {

    public int Add(String numbers) {
        if (numbers == null || numbers == "")
            return 0;

        int sum = asList(numbers.split(","))
            .stream()
            .mapToInt(s -> Integer.parseInt(s))
            .sum();

        return sum;
    }
}
