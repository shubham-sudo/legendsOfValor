package PubSub;

import creature.Creature;
import map.space.Space;

/**
 * An interface for all observer designs
 */
public interface GameObserver {

    /**
     * Notifying the observers with below attributes
     * @param creature hero object itself
     */
    void notifying(Creature creature, Space space);
}
