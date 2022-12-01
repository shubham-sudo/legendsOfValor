package market;

import product.*;
import inventory.*;

import java.util.*;


/**
 * Market is to do purchase and sell
 */
public class Market {
    private final String name;
    private final Inventory inventory;

    /**
     * Creates the new market for the map tiles
     * @param name using this name
     */
    public Market(String name){
        this.name = name;
        this.inventory = new MarketInventory();
    }

    /**
     * getter for the name of market
     * @return string
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the market inventory
     * @return Inventory object
     */
    public Inventory inventory() {
        return inventory;
    }

    /**
     * Add new products to the market
     * @param products products
     */
    public void addProducts(List<Product> products){
        this.inventory.addProducts(products);
    }

    /**
     * Add new products to the market
     * @param product products
     */
    public void addProduct(Product product){
        this.inventory.addProduct(product);
    }

    /**
     * To string for market
     * @return string
     */
    @Override
    public String toString() {
        return this.name;
    }
}
