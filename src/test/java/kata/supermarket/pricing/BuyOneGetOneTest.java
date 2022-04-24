package kata.supermarket.pricing;

import kata.supermarket.model.Item;
import kata.supermarket.model.UnitProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyOneGetOneTest {

    @DisplayName("Check for Buy One Get One offer when Item list contains...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void calculateBogoOnItemList(String description, List<Item> inputList, List<Item> expectedList){
        BuyOneGetOne buyOneGetOne = new BuyOneGetOne();
        List<Item> resultList = buyOneGetOne.calculateOffer(inputList);
        //Sorting done to avoid any error due to order of items
        resultList.sort(Comparator.comparing(Item::getProductId));
        expectedList.sort(Comparator.comparing(Item::getProductId));

        AtomicInteger i = new AtomicInteger(0);
        assertEquals(
                expectedList.stream()
                        .filter(item -> item.getProductId().equals(resultList.get(i.get()).getProductId()))
                        .filter(item -> item.getPrice().equals(resultList.get(i.getAndIncrement()).getPrice()))
                        .count(),
                resultList.size()
        );
    }

    static Stream<Arguments> calculateBogoOnItemList(){
        return Stream.of(
                multipleItemsSameProductWithEvenQuantity(),
                multipleItemsSameProductWithOddQuantity(),
                multipleItemsDifferentProduct(),
                aSingleItemOnBogo()
        );
    }

    private static Arguments aSingleItemOnBogo(){
        return Arguments.of("single item on BOGO", Collections.singletonList(aBoxOfLindt()),Collections.emptyList());
    }

    private static Arguments multipleItemsSameProductWithEvenQuantity(){
        return Arguments.of("multiple items of same product with even quantity",Arrays.asList(aBoxOfLindt(),aBoxOfLindt()),Collections.singletonList(aFreeBoxOfLindt()));
    }

    private static Arguments multipleItemsSameProductWithOddQuantity(){
        return Arguments.of("multiple items of same product with odd quantity",Arrays.asList(aBoxOfLindt(),aBoxOfLindt(),aBoxOfLindt()),Collections.singletonList(aFreeBoxOfLindt()));
    }

    private static Arguments multipleItemsDifferentProduct(){
        return Arguments.of("multiple items of different product",Arrays.asList(aBoxOfLindt(),aBottleOfWine(),aBoxOfLindt(),aBottleOfWine()),Arrays.asList(aFreeBoxOfLindt(),aFreeBottleOfWine()));
    }

    private static Item aBoxOfLindt(){
        return new UnitProduct(new BigDecimal("3.50"),"L1", Offers.BOGO).oneOf();
    }

    private static Item aFreeBoxOfLindt(){
        return new UnitProduct(new BigDecimal("-3.50"),"L1").oneOf();
    }

    private static Item aBottleOfWine(){
        return new UnitProduct(new BigDecimal("10.50"),"W1",Offers.BOGO).oneOf();
    }

    private static Item aFreeBottleOfWine(){
        return new UnitProduct(new BigDecimal("-10.50"),"W1").oneOf();
    }

}
