package kata.supermarket.model;

import kata.supermarket.pricing.Offers;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal getPrice() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getProductId(){
        return product.getProductId();
    }

    public Offers getProductOffer(){
        return product.getProductOffer();
    }
}
