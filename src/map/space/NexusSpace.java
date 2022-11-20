package map.space;

import creature.Creature;


/**
 * Nexus space which is a home for Creature
 */
public class NexusSpace extends NormalSpace{

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return (this.creature == null || !this.creature.equals(creature));  // TODO: add lane check here for which you also need to maintain the lane for every space
    }
}
