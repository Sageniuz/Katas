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
    public static final String SINGLE_CHAR_CUST_SEPERATOR_PREFIX = "//";
    public static final String MULTI_CHAR_CUST_SEPERATOR_PREFIX = "//[";

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
        String single = createRegexForSingleCharacterSeperator();
        String multi = createRegexForMultipleCharacterSeperator();
        return single + multi;
    }

    private String createRegexForSingleCharacterSeperator() {
        List<String> singleCharSep = seperators
                .stream()
                .filter(s -> s.length() == 1)
                .collect(Collectors.toList());

        return "[" + join("", singleCharSep) + "]";
    }

    private String createRegexForMultipleCharacterSeperator() {
        List<String> multiCharSep = seperators
                .stream()
                .filter(s -> s.length() > 1)
                .collect(Collectors.toList());

        String regex = "";

        if (!multiCharSep.isEmpty())
            regex = "|(" + join("|", multiCharSep) + ")";

        return regex;
    }
}
