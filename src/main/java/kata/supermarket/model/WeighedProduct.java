package kata.supermarket.model;

import kata.supermarket.pricing.Offers;

import java.math.BigDecimal;

public class WeighedProduct extends Product{

    private final BigDecimal pricePerKilo;

    public WeighedProduct(final BigDecimal pricePerKilo,String productId) {
        super(productId);
        this.pricePerKilo = pricePerKilo;
    }

    public WeighedProduct(final BigDecimal pricePerKilo, String productId, Offers offer) {
        super(productId,offer);
        this.pricePerKilo = pricePerKilo;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
