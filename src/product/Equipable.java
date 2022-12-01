package product;

import creature.Hero;

/**
 * An interface for all wearable products
 */
public interface Equipable {

    /**
     * Check if hero can equip this product
     * @param hero hero trying to equip
     * @return true if you can equip, false otherwise
     */
    boolean isSafeToEquip(Hero hero);

    /**
     * equip the product post validation
     * @param hero creature who equip it
     * @return true wore it, false otherwise
     */
    boolean equip(Hero hero) throws IllegalAccessException;

    /**
     * Remove the product if needed
     * @param hero creature who want to remove
     */
    void drop(Hero hero) throws IllegalAccessException;
}
