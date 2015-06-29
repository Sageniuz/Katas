package domain;

public class ParserForSingleCharCustSeparator extends Parser {

    public static final String SINGLE_CHAR_CUST_SEPARATOR_PREFIX = "//";

    public ParserForSingleCharCustSeparator(String numbers) {
        super(numbers);
    }

    @Override
    protected String deleteSeparatorPrefixOfNumbers() {
        return numbers.substring(3);
    }

    @Override
    protected String extractSeparatorOfNumbers() {
        return String.valueOf(numbers.charAt(2));
    }

}
