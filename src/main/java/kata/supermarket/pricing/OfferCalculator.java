package kata.supermarket.pricing;

import kata.supermarket.model.Item;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OfferCalculator {

    public void calculateOffer(List<Item> basketItemsList){

        if(!CollectionUtils.isEmpty(basketItemsList)) {

            List<Item> bogoInputList = basketItemsList
                                        .stream()
                                        .filter(item -> Offers.BOGO.equals(item.getProductOffer())).collect(Collectors.toList());
            List<Item> bogoItemList = bogoInputList.isEmpty()
                                    ? Collections.emptyList()
                                    : new BuyOneGetOne().calculateOffer(bogoInputList);
            //Similarly, get a list of new items to be added for other offers and add them to the existing list
            basketItemsList.addAll(bogoItemList);
        }

    }
}
