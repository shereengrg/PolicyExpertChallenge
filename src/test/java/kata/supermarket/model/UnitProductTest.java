package kata.supermarket.model;

import kata.supermarket.pricing.Offers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitProductTest {

    @Test
    void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new UnitProduct(price,"AC1").oneOf().getPrice());
    }

    @Test
    void singleItemHasExpectedProductIdFromProduct(){
        final String productId = "C1";
        assertEquals(productId, new UnitProduct(new BigDecimal("2.50"),productId).oneOf().getProductId());
    }

    @Test
    void singleItemHasExpectedOffersFromProduct(){
        assertEquals(Offers.BOGO,new UnitProduct(new BigDecimal("2.50"),"C1",Offers.BOGO).oneOf().getProductOffer());
    }
}