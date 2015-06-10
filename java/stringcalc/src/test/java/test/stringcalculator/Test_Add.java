package test.stringcalculator;

import com.googlecode.zohhak.api.TestWith;
import com.googlecode.zohhak.api.runners.ZohhakRunner;
import domain.StringCalculator;
import org.fest.assertions.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.*;

@RunWith(ZohhakRunner.class)
public class Test_Add {

    @Test
    public void returns_0_for_null() {
        StringCalculator sut = new StringCalculator();
        int actualSum = sut.Add(null);
        assertThat(actualSum).isZero();
    }

    @Test()
    public void returns_0_for_empty_string() {
        StringCalculator sut = new StringCalculator();
        int actualSum = sut.Add("");
        assertThat(actualSum).isZero();
    }

    @TestWith({
        "1, 1",
        "10, 10"
    })
    public void returns_given_number_if_only_single_number_is_provided(String givenNumbers, int expectedSum) {
        StringCalculator sut = new StringCalculator();
        int actualSum = sut.Add(givenNumbers);
        assertThat(actualSum).isEqualTo(expectedSum);
    }

    @TestWith(value = {
        "1,2,3, | 6",
        "1\n5\n10 | 16"
    }, separator = "[\\|]")
    public void returns_sum_for_default_seperator_seperated_numbers(String givenNumbers, int expectedSum) {
        StringCalculator sut = new StringCalculator();
        int actualSum = sut.Add(givenNumbers);
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
        StringCalculator sut = new StringCalculator();
        int actualSum = sut.Add(givenNumbers);
        assertThat(actualSum).isEqualTo(expectedSum);
    }
}
