import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class CalcSumTest {

    private Calculator calculator;
    private String filePath;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        filePath = getClass().getResource("numbers.txt").getPath();
    }
    @Test
    void sumOfNumbers() throws IOException {
        int sum = calculator.calcSum(filePath);
        Assertions.assertEquals(10, sum);
    }

    @Test
    void multiplyOfNumbers() throws IOException {
        int multiply = calculator.calcMultiply(filePath);
        Assertions.assertEquals(24, multiply);
    }

    @Test
    void concatenateStrings() throws IOException {
        Assertions.assertEquals("1234", calculator.concatenate(filePath));
    }
}
