package test.stringcalculator;

import domain.StringCalculator;
import org.fest.assertions.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.*;

public class Test_Add {

    @Test
    public void returns_0_for_null() {
        StringCalculator sut = new StringCalculator();
        int actualSum = sut.Add(null);
        assertThat(actualSum).isZero();
    }

    @Test()
    @Ignore("Automatically will be green. First a failing test!")
    public void returns_0_for_empty_string() {
        StringCalculator sut = new StringCalculator();
        int actualSum = sut.Add("");
        assertThat(actualSum).isZero();
    }

    @Test
    public void returns_given_number_if_only_single_number_is_provided() {
        StringCalculator sut = new StringCalculator();
        int actualSum = sut.Add("1");
        assertThat(actualSum).isEqualTo(1);
    }

}
