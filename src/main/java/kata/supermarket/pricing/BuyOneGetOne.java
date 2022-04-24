package kata.supermarket.pricing;

import kata.supermarket.model.Item;
import kata.supermarket.model.UnitProduct;

import java.math.BigDecimal;
import java.util.*;


public class BuyOneGetOne {

    //Adds the discount on items applicable for Buy One Get One offer
    public List<Item> calculateOffer(List<Item> itemList) {

            List<Item> resultList = new ArrayList<>();
            Map<Item, Integer> itemMap = new HashMap<>();
            int i, itemCount = 1;

            //Sort to get the same products together
            itemList.sort(Comparator.comparing(Item::getProductId));
            for (i = 1; i < itemList.size(); i++) {
                if (itemList.get(i).getProductId().equals(itemList.get(i - 1).getProductId())) {
                    if (Objects.equals(itemList.get(i).getPrice(), itemList.get(i - 1).getPrice())) {
                        itemCount++;
                    }
                }
                else if (!itemList.get(i).getProductId().equals(itemList.get(i - 1).getProductId())) {
                    //Map will hold the count of each unique product
                    itemMap.put(itemList.get(i - 1), itemCount);
                    itemCount = 1;
                }
            }
            //To include the case, when all items in itemList have same productId
            if (itemCount > 1)
                itemMap.put(itemList.get(i - 1), itemCount);
            //Iterate through the map and update resultList to include discounts
            for (Map.Entry<Item, Integer> item : itemMap.entrySet()) {
                int itemToBeAddedCount = item.getValue() % 2 == 0 ? item.getValue() / 2 : (item.getValue() - 1) / 2;
                while (itemToBeAddedCount > 0) {
                    BigDecimal negativePrice = item.getKey().getPrice().multiply(BigDecimal.valueOf(-1));
                    resultList.add(new UnitProduct(negativePrice, item.getKey().getProductId()).oneOf());
                    itemToBeAddedCount--;
                }
            }
            //returns just the discount to be added to items in basket
            return resultList;
    }


}
