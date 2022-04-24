package kata.supermarket.model;

import kata.supermarket.pricing.Offers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeighedProductTest {

    @ParameterizedTest
    @MethodSource
    void itemFromWeighedProductHasExpectedUnitPrice(String pricePerKilo, String weightInKilos, String expectedPrice) {
        final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal(pricePerKilo),"AC1");
        final Item weighedItem = weighedProduct.weighing(new BigDecimal(weightInKilos));
        assertEquals(new BigDecimal(expectedPrice), weighedItem.getPrice());
    }

    @Test
    void itemFromWeighedProductHasExpectedProductId(){
        String productId = "AC1";
        final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal("1.30"),"AC1");
        final Item weighedItem = weighedProduct.weighing(new BigDecimal("0.33"));
        assertEquals(productId, weighedItem.getProductId());
    }

    @Test
    void itemFromWeighedProductHasExpectedOffer(){
        final WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal("1.30"),"MC1", Offers.HALFOFF);
        final Item weighedItem = weighedProduct.weighing(new BigDecimal("0.33"));
        assertEquals(Offers.HALFOFF,weighedItem.getProductOffer());
    }

    static Stream<Arguments> itemFromWeighedProductHasExpectedUnitPrice() {
        return Stream.of(
                Arguments.of("100.00", "1.00", "100.00"),
                Arguments.of("100.00", "0.33333", "33.33"),
                Arguments.of("100.00", "0.33335", "33.34"),
                Arguments.of("100.00", "0", "0.00")
        );
    }

}