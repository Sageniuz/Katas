package domain;

import java.util.ArrayList;

import static java.lang.String.*;
import static java.lang.String.join;
import static java.util.Arrays.asList;

public class StringCalculator {

    public static final int DEFAULT_VALUE = 0;
    private static final ArrayList<String> seperators = new ArrayList<>(asList(",", "\n"));

    public int Add(String numbers) {
        if (numbers == null || numbers == "")
            return DEFAULT_VALUE;

        if (numbers.startsWith("//")) {
            seperators.add(valueOf(numbers.charAt(2)));
            numbers = numbers.substring(3);
        }

        int sum = asList(numbers.split("[" + join("", seperators) + "]"))
            .stream()
            .mapToInt(s -> Integer.parseInt(s))
            .sum();

        return sum;
    }
}
