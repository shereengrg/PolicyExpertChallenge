package kata.supermarket.model;

import kata.supermarket.pricing.Offers;

import java.math.BigDecimal;

public class UnitProduct extends Product{

    private final BigDecimal pricePerUnit;

    public UnitProduct(final BigDecimal pricePerUnit,String productId) {
        super(productId);
        this.pricePerUnit = pricePerUnit;
    }

    public UnitProduct(final BigDecimal pricePerUnit, final String productId, Offers offer) {
        super(productId,offer);
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
