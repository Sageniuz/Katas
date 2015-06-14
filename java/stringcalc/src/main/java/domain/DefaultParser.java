package domain;

public class DefaultParser extends Parser {

    public DefaultParser(String numbers) {
        super(numbers);
    }

    @Override
    protected String deleteSeperatorPrefixOfNumbers() {
        return numbers;
    }

    @Override
    protected String extractSeperatorOfNumbers() {
        return null;
    }

}
