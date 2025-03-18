package pl.swrobel.CreditCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloTest {

    @Test
    void myFirstTest() {
        int a =2;
        int b =3;

        int result = a + b;
        assert result == 5;
    }

    @Test
    void myFailingTest() {
        int a =2;
        int b =3;

        int result = a + b;
        assert result == 6;
    }

    @Test
    void moreRedableAssertion() {
        assertTrue(false,"your condition is not true");
    }
}
