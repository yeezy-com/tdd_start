package chap03;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

    // 쉬운 것부터 테스트하자.
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                LocalDate.of(2024, 3, 1),
                10_000,
                LocalDate.of(2024, 4, 1)
        );
        assertExpiryDate(
                LocalDate.of(2024, 5, 5),
                10_000,
                LocalDate.of(2024, 6, 5)
        );
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        Assertions.assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
