package test.stringcalculator;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import domain.StringCalculator;
import domain.api.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(ZohhakRunner.class)
public class Test_Add {

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
