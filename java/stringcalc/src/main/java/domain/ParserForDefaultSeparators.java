package domain;

public class ParserForDefaultSeparators extends Parser {

    public ParserForDefaultSeparators(String numbers) {
        super(numbers);
    }

    @Override
    protected String deleteSeparatorPrefixOfNumbers() {
        return numbers;
    }

    @Override
    protected String extractSeparatorOfNumbers() {
        return null;
    }

}
