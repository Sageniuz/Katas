package domain;

import lombok.NonNull;
import lombok.val;

import java.util.List;
import java.util.stream.Stream;

import static domain.MultiCharacterCustomParser.MULTI_CHAR_CUST_SEPERATOR_PREFIX;
import static domain.SingleCharacterCustomParser.SINGLE_CHAR_CUST_SEPERATOR_PREFIX;
import static java.lang.String.join;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public abstract class Parser {

    public static final List<String> SEPERATORS = Stream.of(",", "\n").collect(toList());
    protected String numbers;

    public static Parser create(@NonNull String numbers) {
        if (numbers.startsWith(MULTI_CHAR_CUST_SEPERATOR_PREFIX))
            return new MultiCharacterCustomParser(numbers);

        if (numbers.startsWith(SINGLE_CHAR_CUST_SEPERATOR_PREFIX))
            return new SingleCharacterCustomParser(numbers);

        else return new DefaultParser(numbers);
    }

    protected Parser(String numbers) {
        this.numbers = numbers;
    }

    public List<String> getNumbers() {
        SEPERATORS.add(extractSeperatorOfNumbers());
        numbers = deleteSeperatorPrefixOfNumbers();
        return asList(numbers.split(byAllSeperators()));
    }

    private static String byAllSeperators() {
        val regex = join("|", SEPERATORS
            .stream()
            .map(s -> "(" + s + ")")
            .collect(toList()));

        return regex
                .replace("?", "\\?")
                .replace("*", "\\*");
    }

    protected abstract String deleteSeperatorPrefixOfNumbers();

    protected abstract String extractSeperatorOfNumbers();

}
