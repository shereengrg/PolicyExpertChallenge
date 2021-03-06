package kata.supermarket.model;

import kata.supermarket.pricing.Offers;

//Holds common features shared by all types of products
public abstract class Product {

    private final String productId;
    private Offers offer;

    public Product(String productId){
        this.productId = productId;
    }

    public Product(String productId, Offers offer){
        this.productId = productId;
        this.offer = offer;
    }

    String getProductId(){
        return productId;
    }

    Offers getProductOffer(){
        return offer;
    }
}
