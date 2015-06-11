package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.*;
import static java.lang.String.join;
import static java.util.Arrays.asList;

public class StringCalculator {

    public static final int DEFAULT_VALUE = 0;
    private static final ArrayList<String> seperators = new ArrayList<>(asList(",", "\n"));
    public static final String SINGLE_CHARACTER_CUSTOM_SEPERATOR_PREFIX = "//";

    public int Add(String numbers) {
        if (numbers == null || numbers == "")
            return DEFAULT_VALUE;

        if (numbers.startsWith("//[")) {
            String seperator = numbers.split("\\[")[1].split("\\]")[0];
            seperators.add(seperator);
            numbers = numbers.split("\\]")[1];
        }

        if (numbers.startsWith(SINGLE_CHARACTER_CUSTOM_SEPERATOR_PREFIX)) {
            seperators.add(customSeperatorFrom(numbers));
            numbers = withoutCustomSeperatorPattern(numbers);
        }

        int sum = asList(numbers.split(customSeperatorRegex()))
            .stream()
            .mapToInt(s -> Integer.parseInt(s))
            .sum();

        return sum;
    }

    private String customSeperatorFrom(String numbers) {
        return valueOf(numbers.charAt(2));
    }

    private String withoutCustomSeperatorPattern(String numbers) {
        return numbers.substring(3);
    }

    private String customSeperatorRegex() {
        List<String> multiCharSep = seperators
                .stream()
                .filter(s -> s.length() > 1)
                .collect(Collectors.toList());

        String multiRegex = "";

        if (!multiCharSep.isEmpty())
            multiRegex = "|(" + join("|", multiCharSep) + ")";

        List<String> singleCharSep = seperators
                .stream()
                .filter(s -> s.length() == 1)
                .collect(Collectors.toList());

        String regex = "[" + join("", singleCharSep) + "]";

        return regex + multiRegex;
    }
}
