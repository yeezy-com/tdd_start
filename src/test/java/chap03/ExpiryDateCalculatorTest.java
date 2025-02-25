package chap03;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

    // 쉬운 것부터 테스트하자.
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        LocalDate billingDate = LocalDate.of(2024, 3, 1);
        int payAmount = 10_000;

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        Assertions.assertEquals(LocalDate.of(2024, 4, 1), expiryDate);

        LocalDate billingDate2 = LocalDate.of(2024, 5, 5);
        int payAmount2 = 10_000;

        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
        LocalDate expiryDate2 = cal.calculateExpiryDate(billingDate2, payAmount);

        Assertions.assertEquals(LocalDate.of(2024, 6, 5), expiryDate2);
    }
}
