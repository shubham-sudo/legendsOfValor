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
     * @param opponent opponent for this game or battle
     * @param space space object
     */
    void notifying(Creature creature, Creature opponent, Space space);
}
