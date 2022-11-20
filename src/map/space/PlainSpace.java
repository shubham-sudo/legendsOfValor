package map.space;

import creature.Creature;


/**
 * Plane space which is a normal space
 */
public class PlainSpace extends NormalSpace{

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return this.creature == null || !this.creature.equals(creature);
    }
}
