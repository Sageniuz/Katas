package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.String.*;
import static java.lang.String.join;
import static java.util.Arrays.asList;

public class StringCalculator {

    private static final int DEFAULT_VALUE = 0;
    private static final String SINGLE_CHAR_CUST_SEPERATOR_PREFIX = "//";
    private static final String MULTI_CHAR_CUST_SEPERATOR_PREFIX = "//[";

    private final ArrayList<String> seperators = new ArrayList<>(asList(",", "\n"));

    public int Add(String numbers) {
        if (numbers == null || numbers == "")
            return DEFAULT_VALUE;

        if (numbers.startsWith(MULTI_CHAR_CUST_SEPERATOR_PREFIX)) {
            seperators.add(mutliCharCustSeperatorFrom(numbers));
            numbers = withoutMultiCharCustSeperatorPattern(numbers);
        }

        if (numbers.startsWith(SINGLE_CHAR_CUST_SEPERATOR_PREFIX)) {
            seperators.add(singleCharCustSeperatorFrom(numbers));
            numbers = withoutSingleCharCustSeperatorPattern(numbers);
        }

        int sum = asList(numbers.split(custSeperatorRegex()))
            .stream()
            .mapToInt(s -> Integer.parseInt(s))
            .sum();

        return sum;
    }

    private String mutliCharCustSeperatorFrom(String numbers) {
        return numbers.split("\\[")[1].split("\\]")[0];
    }

    private String withoutMultiCharCustSeperatorPattern(String numbers) {
        return numbers.split("\\]")[1];
    }

    private String singleCharCustSeperatorFrom(String numbers) {
        return valueOf(numbers.charAt(2));
    }

    private String withoutSingleCharCustSeperatorPattern(String numbers) {
        return numbers.substring(3);
    }

    private String custSeperatorRegex() {
        String regex = join("|",
                seperators
                    .stream()
                    .map(s -> "(" + s + ")")
                    .collect(Collectors.toList()));

        return regex
                .replace("?", "\\?")
                .replace("*", "\\*");
    }
}
