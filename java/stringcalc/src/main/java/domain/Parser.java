package domain;

import lombok.NonNull;
import lombok.val;

import java.util.List;
import java.util.stream.Stream;

import static domain.ParserForMultiCharCustomSeparator.MULTI_CHAR_CUST_SEPARATOR_PREFIX;
import static domain.ParserForSingleCharCustSeparator.SINGLE_CHAR_CUST_SEPARATOR_PREFIX;
import static java.lang.String.join;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public abstract class Parser {

    public static final String[] DEFAULT_SEPARATORS = new String[]{",", "\n"};
    public static final List<String> SEPARATORS = Stream.of(DEFAULT_SEPARATORS).collect(toList());
    protected String numbers;

    public static Parser createParserFor(@NonNull String numbers) {
        if (numbers.startsWith(MULTI_CHAR_CUST_SEPARATOR_PREFIX))
            return new ParserForMultiCharCustomSeparator(numbers);

        if (numbers.startsWith(SINGLE_CHAR_CUST_SEPARATOR_PREFIX))
            return new ParserForSingleCharCustSeparator(numbers);

        else return new ParserForDefaultSeparators(numbers);
    }

    protected Parser(String numbers) {
        this.numbers = numbers;
    }

    public List<String> getNumbers() {
        SEPARATORS.add(extractSeparatorOfNumbers());
        val numbersOnly = deleteSeparatorPrefixOfNumbers();
        return asList(numbersOnly.split(byAllSeparators()));
    }

    private static String byAllSeparators() {
        val regex = join("|", SEPARATORS
            .stream()
            .map(s -> "(" + s + ")")
            .collect(toList()));

        return regex
            .replace("?", "\\?")
            .replace("*", "\\*");
    }

    protected abstract String deleteSeparatorPrefixOfNumbers();

    protected abstract String extractSeparatorOfNumbers();

}
