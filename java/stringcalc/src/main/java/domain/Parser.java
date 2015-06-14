package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static domain.MultiCharacterCustomParser.*;
import static domain.SingleCharacterCustomParser.*;
import static java.lang.String.join;
import static java.util.Arrays.asList;

public abstract class Parser {

    public static final List<String> DEFAULT_SEPERATORS = asList(",", "\n");
    protected String numbers;

    public static Parser create(String numbers) {
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
        ArrayList<String> seperators = new ArrayList<>(DEFAULT_SEPERATORS);
        seperators.add(extractSeperatorOfNumbers());
        numbers = deleteSeperatorPrefixOfNumbers();
        return asList(numbers.split(byRegex(seperators)));
    }

    private static String byRegex(List<String> seperators) {
        String regex = join("|", seperators
            .stream()
            .map(s -> "(" + s + ")")
            .collect(Collectors.toList()));

        return regex
                .replace("?", "\\?")
                .replace("*", "\\*");
    }

    protected abstract String deleteSeperatorPrefixOfNumbers();

    protected abstract String extractSeperatorOfNumbers();

}
