package PubSub;

import creature.Hero;
import inventory.Inventory;

/**
 * An interface for all observer designs
 */
public interface GameObserver {

    /**
     * Notifying the observers with below attributes
     * @param inventory inventory of the hero
     * @param hero hero object itself
     */
    void notifying(Inventory inventory, Hero hero);
}
