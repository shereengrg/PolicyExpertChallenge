package kata.supermarket.model;

import kata.supermarket.pricing.Offers;

import java.math.BigDecimal;

public interface Item {
    BigDecimal getPrice();
    String getProductId();
    Offers getProductOffer();
}
