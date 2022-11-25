package inventory;

import product.*;
import utility.Utility;

import java.util.*;
import java.util.stream.Collectors;

/**
 * MarketInventory is the child class of Inventory for markets
 */
public class MarketInventory extends Inventory {

    /**
     * Shows the products present in the inventory of the market
     * @see Inventory#show()
     */
    @Override
    public ArrayList<Product> show() {
        return new ArrayList<Product>(products.values()){};
    }

    /**
     * Shows the specific products from the market
     * @param type type of the product
     */
    @Override
    public ArrayList<Product> show(ProductType type) {
        List<Product> products;

        switch (type){
            case ARMOR:
                products = this.products.values().stream().filter(product -> product instanceof Armor).collect(Collectors.toList());
                return new ArrayList<Product>(products){};
            case WEAPON:
                products = this.products.values().stream().filter(product -> product instanceof Weapon).collect(Collectors.toList());
                return new ArrayList<Product>(products){};
            case SPELL:
                products = this.products.values().stream().filter(product -> product instanceof Spell).collect(Collectors.toList());
                return new ArrayList<Product>(products){};
            case POTION:
                products = this.products.values().stream().filter(product -> product instanceof Potion).collect(Collectors.toList());
                return new ArrayList<Product>(products){};
            case ALL:
                return show();
        }
        return new ArrayList<Product>(){};
    }
}
