package kata.supermarket.model;

import kata.supermarket.pricing.Offers;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final UnitProduct product;

    ItemByUnit(final UnitProduct product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return product.pricePerUnit();
    }

    public String getProductId(){
        return product.getProductId();
    }

    public Offers getProductOffer(){
        return product.getProductOffer();
    }
}
