package pl.swrobel.ecommerce.sales;

import java.math.BigDecimal;

public class SalesFacade {
    public void addToCart( String customerId, String productId) {}

    public void acceptOffer(AcceptOfferComand acceptOfferComand) {}

    public Offer getCurrentOffer(String customerId) {
        return new Offer();
    }


    public void makeReservationPaid(String reservatioId) {}

}
