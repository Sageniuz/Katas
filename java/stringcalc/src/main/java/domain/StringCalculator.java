package domain;

import domain.api.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.join;
import static java.util.Arrays.asList;

public class StringCalculator implements Calculator<String> {

    private static final int DEFAULT_VALUE = 0;
    private static final String SINGLE_CHAR_CUST_SEPERATOR_PREFIX = "//";
    private static final String MULTI_CHAR_CUST_SEPERATOR_PREFIX = "//[";

    @Override
    public int add(String numbers) {
        if (numbers == null || numbers == "") {
            return DEFAULT_VALUE;
        }

        ArrayList<String> seperators = new ArrayList<>(asList(",", "\n"));

        if (numbers.startsWith(MULTI_CHAR_CUST_SEPERATOR_PREFIX)) {
            seperators.add(mutliCharCustSeperatorFrom(numbers));
            numbers = withoutMultiCharCustSeperatorPattern(numbers);
        }

        if (numbers.startsWith(SINGLE_CHAR_CUST_SEPERATOR_PREFIX)) {
            seperators.add(singleCharCustSeperatorFrom(numbers));
            numbers = withoutSingleCharCustSeperatorPattern(numbers);
        }

        int sum = asList(numbers.split(custSeperatorRegex(seperators)))
            .stream()
            .mapToInt(s -> Integer.parseInt(s))
            .sum();

        return sum;
    };

    private static String mutliCharCustSeperatorFrom(String numbers) {
        return numbers.split("\\[")[1].split("\\]")[0];
    }

    private static String withoutMultiCharCustSeperatorPattern(String numbers) {
        return numbers.split("\\]")[1];
    }

    private static String singleCharCustSeperatorFrom(String numbers) {
        return String.valueOf(numbers.charAt(2));
    }

    private static String withoutSingleCharCustSeperatorPattern(String numbers) {
        return numbers.substring(3);
    }

    private static String custSeperatorRegex(List<String> seperators) {
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
