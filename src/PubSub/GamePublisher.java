package PubSub;

import creature.Creature;
import map.space.Space;

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
     * @param creature hero object
     */
    void notifyObservers(Creature creature, Space space);
}
