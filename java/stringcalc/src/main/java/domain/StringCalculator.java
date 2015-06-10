package domain;

import static java.lang.String.join;
import static java.util.Arrays.asList;

public class StringCalculator {

    public static final int DEFAULT_VALUE = 0;
    public static final String DEFAULT_SEPERATORS = join("", asList(",", "\n"));
    public static final String BY_DEFAULT_SEPERATORS = "[" + DEFAULT_SEPERATORS + "]";

    public int Add(String numbers) {
        if (numbers == null || numbers == "")
            return DEFAULT_VALUE;

        int sum = asList(numbers.split(BY_DEFAULT_SEPERATORS))
            .stream()
            .mapToInt(s -> Integer.parseInt(s))
            .sum();

        return sum;
    }
}
