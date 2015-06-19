package test.stringcalculator;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import domain.StringCalculator;
import domain.api.Calculator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static java.lang.String.format;
import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(ZohhakRunner.class)
public class Test_Add {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    Calculator sut = new StringCalculator();

    @Test
    public void returns_0_for_null() {
        int actualSum = sut.add(null);
        assertThat(actualSum).isZero();
    }

    @Test()
    public void returns_0_for_empty_string() {
        int actualSum = sut.add("");
        assertThat(actualSum).isZero();
    }

    @TestWith({
        "1, 1",
        "10, 10"
    })
    public void returns_given_number_if_only_single_number_is_provided(String givenNumbers, int expectedSum) {
        int actualSum = sut.add(givenNumbers);
        assertThat(actualSum).isEqualTo(expectedSum);
    }

    @Test
    public void throws_given_a_negative_number() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(format("Negative numbers are not allowed: %s", "-3"));
        sut.add("1,-3,5");
    }

    @TestWith(value = {
        "1,2,3, | 6",
        "1\n5\n10 | 16"
    }, separator = "[\\|]")
    public void returns_sum_for_default_seperator_seperated_numbers(String givenNumbers, int expectedSum) {
        int actualSum = sut.add(givenNumbers);
        assertThat(actualSum).isEqualTo(expectedSum);
    }

    @TestWith(value = {
        "//;1;2;3 | 6",
        "///1/2/3 | 6",
    }, separator = "[\\|]")
    public void returns_sum_for_single_character_custom_seperator_seperated_numbers(
        String givenNumbers,
        int expectedSum)
    {
        int actualSum = sut.add(givenNumbers);
        assertThat(actualSum).isEqualTo(expectedSum);
    }

    @TestWith(value = {
        "//[;;;]1;;;2;;;3 | 6",
        "//[??]1??2??3 | 6",
    }, separator = "[\\|]")
    public void returns_sum_for_multiple_character_custom_seperator_seperated_numbers(
        String givenNumbers,
        int expectedSum)
    {
        int actualSum = sut.add(givenNumbers);
        assertThat(actualSum).isEqualTo(expectedSum);
    }

}
