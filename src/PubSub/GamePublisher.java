package PubSub;

import creature.Hero;
import inventory.Inventory;

/**
 * An interface for all Game publishers
 */
public interface GamePublisher {

    /**
     * Register a new observer to this publisher
     * @param gameObserver observer object
     */
    void register(GameObserver gameObserver);

    /**
     * notify observer when required
     * @param inventory inventory object of hero
     * @param hero hero object
     */
    void notifyObservers(Inventory inventory, Hero hero);
}
