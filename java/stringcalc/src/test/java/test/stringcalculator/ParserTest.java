package test.stringcalculator;

import domain.ParserForDefaultSeparators;
import domain.ParserForMultiCharCustomSeparator;
import domain.Parser;
import domain.ParserForSingleCharCustSeparator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static domain.ParserForMultiCharCustomSeparator.*;
import static domain.ParserForSingleCharCustSeparator.*;
import static org.fest.assertions.api.Assertions.assertThat;

public class ParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsIfNumbersIsNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("numbers");
        String numbers = null;
        Parser.createParserFor(numbers);
    }

    @Test
    public void returnsCorrectImplementationForGivenNumbers() {
        String numbers = "";
        Parser parser = Parser.createParserFor(numbers);
        assertThat(parser).isInstanceOf(ParserForDefaultSeparators.class);
    }

    @Test public void
    returnsCorrectImplementationForNumbersStartingWithSeparatorPrefixForSingleCharacterSeparators() {
        String numbers = SINGLE_CHAR_CUST_SEPARATOR_PREFIX;
        Parser parser = Parser.createParserFor(numbers);
        assertThat(parser).isInstanceOf(ParserForSingleCharCustSeparator.class);
    }

    @Test public void
    returnsCorrectImplementationForNumbersStartingWithSeparatorPrefixForMultiCharacterSeparators() {
        String numbers = MULTI_CHAR_CUST_SEPARATOR_PREFIX;
        Parser parser = Parser.createParserFor(numbers);
        assertThat(parser).isInstanceOf(ParserForMultiCharCustomSeparator.class);
    }
}
