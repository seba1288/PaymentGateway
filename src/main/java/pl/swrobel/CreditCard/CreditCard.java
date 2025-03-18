package pl.swrobel.CreditCard;

import java.math.BigDecimal;

public class CreditCard {

    public static final int CREDIT_THRESHOLD = 100;
    private final String cardNumber;
    private BigDecimal creditLimit;
    private BigDecimal balance;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNumber() {
        return cardNumber;
    }

    public void assignCredit(BigDecimal credit) {

        if (CreditCantBelowThreshold(credit)) {
            throw new CreditBelowThresholdException();
        }
        if (isCreditAlreadyAssigned() != null) {
            throw new creditCantBeAssignTwiceExecption();
        }

        this.creditLimit = credit;
        this.balance = credit;
    }

    private BigDecimal isCreditAlreadyAssigned() {
        return creditLimit;
    }

    private static boolean CreditCantBelowThreshold(BigDecimal credit) {
        return BigDecimal.valueOf(CREDIT_THRESHOLD).compareTo(credit) > 0;
    }

    public void withdraw(BigDecimal money) {
        if (canAfford(money)) {
            throw new NotEnoughtMoneyException();
        }
        this.balance = balance.subtract(money);
    }

    private boolean canAfford(BigDecimal money) {
        return this.balance.subtract(money).compareTo(BigDecimal.ZERO) > 0;
    }

    public BigDecimal getBalance() {
        return balance;
    }


}
