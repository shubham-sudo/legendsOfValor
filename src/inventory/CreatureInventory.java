package inventory;

import product.*;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Child class of the Inventory to define different function for the creature
 */
public class CreatureInventory extends Inventory {
    /**
     * This reduces the count of product if consumable and
     * keep the count as one for non-consumable
     * @see Inventory#addProduct(Product)
     * @param product product
     */
    @Override
    public void addProduct(Product product) {
        super.addProduct(product);
        // TODO: (shubham) notify that the inventory is updated
    }

    /**
     * Remove product from the Creature inventory
     * @param product product to remove
     */
    @Override
    public void removeProduct(Product product) {
        super.removeProduct(product);
        // TODO: (shubham) notify that the inventory is updated
    }

    /**
     * Shows the creature inventory in the console
     */
    @Override
    public ArrayList<Product> show() {
        return new ArrayList<Product>(products.values()){};
    }

    /**
     * Shows the specific type of product from the inventory
     * @param type type of the product
     */
    @Override
    public ArrayList<Product> show(ProductType type){
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
