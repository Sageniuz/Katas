package domain;

public class StringCalculator {
    public int Add(String numbers) {
        if (numbers == null)
            return 0;
        return Integer.parseInt(numbers);
    }
}
