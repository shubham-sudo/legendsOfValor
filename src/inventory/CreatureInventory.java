package inventory;

import product.*;


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
}
