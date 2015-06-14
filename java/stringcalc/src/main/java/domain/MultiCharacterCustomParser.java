package domain;

public class MultiCharacterCustomParser extends Parser {

    public static final String MULTI_CHAR_CUST_SEPERATOR_PREFIX = "//[";

    public MultiCharacterCustomParser(String numbers) {
        super(numbers);
    }

    @Override
    protected String deleteSeperatorPrefixOfNumbers() {
        return numbers.split("\\]")[1];
    }

    @Override
    protected String extractSeperatorOfNumbers() {
        return numbers.split("\\[")[1].split("\\]")[0];
    }

}
