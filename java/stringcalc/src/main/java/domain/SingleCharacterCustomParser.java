package domain;

public class SingleCharacterCustomParser extends Parser {

    public static final String SINGLE_CHAR_CUST_SEPERATOR_PREFIX = "//";

    public SingleCharacterCustomParser(String numbers) {
        super(numbers);
    }

    @Override
    protected String deleteSeperatorPrefixOfNumbers() {
        return numbers.substring(3);
    }

    @Override
    protected String extractSeperatorOfNumbers() {
        return String.valueOf(numbers.charAt(2));
    }

}
