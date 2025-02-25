package chap03;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

    // 쉬운 것부터 테스트하자.
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 4, 1)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 5, 5))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 6, 5)
        );
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 2, 29)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 6, 30)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2023, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2023, 2, 28)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2019, 3, 31));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);

        Assertions.assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
