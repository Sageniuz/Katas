package test.stringcalculator;

import domain.Parser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
}
