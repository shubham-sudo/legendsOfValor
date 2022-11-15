package inventory;

import PubSub.GameObserver;
import creature.Hero;
import product.Consumable;
import product.Product;

import java.util.ArrayList;

/**
 * This is the observer which cleans the inventory if any product is no more usable
 */
public class InventoryObserver implements GameObserver {

    /**
     * Update the inventory of the player after every attack
     * @see GameObserver#notifying(Inventory, Hero)
     * @param inventory inventory of the hero
     * @param hero hero itself
     */
    @Override
    public void notifying(Inventory inventory, Hero hero) {
        updateInventory(inventory, hero);
    }

    /**
     * Update inventory based on the consumable products
     * @param inventory inventory of the hero
     * @param hero hero itself
     */
    public void updateInventory(Inventory inventory, Hero hero) {
        ArrayList<Product> productsToRemove = new ArrayList<>();
        for (Product product : inventory.products.values()){
            if (product instanceof Consumable){
                if (!((Consumable) product).isConsumable(hero)){
                    productsToRemove.add(product);
                }
            }
        }
        for (Product product : productsToRemove){
            inventory.removeProduct(product);
        }
    }
}
