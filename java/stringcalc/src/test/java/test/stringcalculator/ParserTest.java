package test.stringcalculator;

import domain.DefaultParser;
import domain.MultiCharacterCustomParser;
import domain.Parser;
import domain.SingleCharacterCustomParser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static domain.MultiCharacterCustomParser.*;
import static domain.SingleCharacterCustomParser.*;
import static org.fest.assertions.api.Assertions.assertThat;

public class ParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsIfNumbersIsNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("numbers");
        String numbers = null;
        Parser.create(numbers);
    }

    @Test
    public void returnsCorrectImplementationForGivenNumbers() {
        String numbers = "";
        Parser parser = Parser.create(numbers);
        assertThat(parser).isInstanceOf(DefaultParser.class);
    }

    @Test public void
    returnsCorrectImplementationForNumbersStartingWithSeperatorPrefixForSingleCharacterSeperators() {
        String numbers = SINGLE_CHAR_CUST_SEPERATOR_PREFIX;
        Parser parser = Parser.create(numbers);
        assertThat(parser).isInstanceOf(SingleCharacterCustomParser.class);
    }

    @Test public void
    returnsCorrectImplementationForNumbersStartingWithSeperatorPrefixForMultiCharacterSeperators() {
        String numbers = MULTI_CHAR_CUST_SEPERATOR_PREFIX;
        Parser parser = Parser.create(numbers);
        assertThat(parser).isInstanceOf(MultiCharacterCustomParser.class);
    }
}
