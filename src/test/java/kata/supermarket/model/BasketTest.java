package kata.supermarket.model;

import kata.supermarket.model.Basket;
import kata.supermarket.model.Item;
import kata.supermarket.model.UnitProduct;
import kata.supermarket.model.WeighedProduct;
import kata.supermarket.pricing.Offers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValueWithoutOffer(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    @DisplayName("basket provides its total value,after applying offers, when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValueWithOffers(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        //calculate offer on items in basket
        basket.calculateOffer();
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValueWithoutOffer() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    static Stream<Arguments> basketProvidesTotalValueWithOffers(){
        return Stream.of(
                noItems(),
                multipleItemsOnBogoOfferPricedPerUnit(),
                multipleItemsOnDifferentOfferPricedPerUnit(),
                singleItemOnBogoOfferPricedPerUnit(),
                multipleItemsWithAndWithoutOfferPricedPerUnit(),
                mixOfItemsOfAllTypes()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new UnitProduct(new BigDecimal("0.49"),"M1").oneOf();
    }

    private static Item aPackOfDigestives() {
        return new UnitProduct(new BigDecimal("1.55"),"D1").oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"),"AS1");
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"),"PM1");
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    private static Arguments singleItemOnBogoOfferPricedPerUnit(){
        return Arguments.of("single items on BOGO priced per unit","3.50", Collections.singletonList(aBoxOfLindt()));
    }

    private static Arguments multipleItemsOnBogoOfferPricedPerUnit(){
        return Arguments.of("multiple items on BOGO priced per unit","14.00", Arrays.asList(aBoxOfLindt(),aBoxOfLindt(),aBottleOfWine(),aBottleOfWine()));
    }

    private static Arguments multipleItemsOnDifferentOfferPricedPerUnit(){
        return Arguments.of("multiple items on different offers priced per unit","8.50", Arrays.asList(aBoxOfLindt(),aBoxOfLindt(),aBoxOfLindt(),aPacketOfCheetos()));
    }

    private static Arguments multipleItemsWithAndWithoutOfferPricedPerUnit(){
        return Arguments.of("multiple items with and without offers priced per unit","5.49", Arrays.asList(aBoxOfLindt(),aBoxOfLindt(),aPintOfMilk(),aPacketOfCheetos()));
    }

    private static Arguments mixOfItemsOfAllTypes(){
        return Arguments.of("mix of different types of items with or without offers","8.74",Arrays.asList(aBoxOfLindt(),aPintOfMilk(),aBoxOfLindt(),twoFiftyGramsOfAmericanSweets(),aBoxOfLindt()));
    }


    private static Item aBoxOfLindt(){
        return new UnitProduct(new BigDecimal("3.50"),"L1", Offers.BOGO).oneOf();
    }

    private static Item aPacketOfCheetos(){
        return new UnitProduct(new BigDecimal("1.50"),"C1",Offers.HALFOFF).oneOf();
    }

    private static Item aBottleOfWine(){
        return new UnitProduct(new BigDecimal("10.50"),"W1",Offers.BOGO).oneOf();
    }
}