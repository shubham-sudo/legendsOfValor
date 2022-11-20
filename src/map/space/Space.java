package map.space;

import creature.Creature;


/**
 * Space interface for all type of spaces
 */
public interface Space {
    boolean isSafeToOccupy(Creature creature);
    void occupy(Creature creature) throws IllegalAccessException;
    void vacant();
}
