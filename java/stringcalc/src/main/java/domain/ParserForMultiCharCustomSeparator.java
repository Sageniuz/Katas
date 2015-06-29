package domain;

public class ParserForMultiCharCustomSeparator extends Parser {

    public static final String MULTI_CHAR_CUST_SEPARATOR_PREFIX = "//[";

    public ParserForMultiCharCustomSeparator(String numbers) {
        super(numbers);
    }

    @Override
    protected String deleteSeparatorPrefixOfNumbers() {
        return numbers.split("\\]")[1];
    }

    @Override
    protected String extractSeparatorOfNumbers() {
        return numbers.split("\\[")[1].split("\\]")[0];
    }

}
