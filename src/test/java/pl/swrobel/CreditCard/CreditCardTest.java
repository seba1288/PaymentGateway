package pl.swrobel.CreditCard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTest {
    @Test
    void creditCardIsIdentifiedWithNumber() {

        var number = "1234-56789";

        var card = new CreditCard(number);

        assertEquals(
                "1234-56789",card.getNumber()
        );
    }

    @Test
    void citAllowsToAssignCreditCardLimit() {
        var card = new CreditCard("1234-4567");

        card.assignCredit(BigDecimal.valueOf(1500));
        assertEquals(
                BigDecimal.valueOf(1500),
                card.getBalance()
        );
    }

    @Test
    void creditLimitCantBeLowerThanCertainThreshold() {
        var card = new CreditCard("1234-4567");
        try {
            card.assignCredit(BigDecimal.valueOf(90));
            fail("execption should be thrown");

        }   catch (CreditBelowThresholdException e) {
            assertTrue(true);
        }

    }

    @Test
    void creditLimitCantBeLowerThanCertainThresholdV2() {
        var card = new CreditCard("1234-4567");
        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCredit(BigDecimal.valueOf(99))
        );
        assertDoesNotThrow(
                () -> card.assignCredit(BigDecimal.valueOf(100))
        );

    }

    @Test
    void creditCantBeAssignTwice() {
        var card = new CreditCard("1234-4567");
        card.assignCredit(BigDecimal.valueOf(1500));
        assertThrows(
                creditCantBeAssignTwiceExecption.class,
                () -> card.assignCredit(BigDecimal.valueOf(2000))
        );
    }

    @Test
    void iAmAbleToWithdrawnSomeMoney() {
        var card = new CreditCard("1234-4567");
        card.assignCredit(BigDecimal.valueOf(1500));

        card.withdraw(BigDecimal.valueOf(100));

        assertEquals(
                BigDecimal.valueOf(1400),
                card.getBalance()
        );

    }

    @Test
    void iAmAbleToWithdrawnSomeMoneyV2() {
        var card = new CreditCard("1234-4567");
        card.assignCredit(BigDecimal.valueOf(1500));

        card.withdraw(BigDecimal.valueOf(100));
        card.withdraw(BigDecimal.valueOf(100));
        card.withdraw(BigDecimal.valueOf(100));

        assertEquals(
                BigDecimal.valueOf(1200),
                card.getBalance()
        );

    }
}
