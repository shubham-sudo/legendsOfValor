package map.space;

import creature.Creature;


/**
 * Base class for all types of Normal spaces
 * @see NexusSpace
 * @see InaccessibleSpace
 * @see PlainSpace
 */
public abstract class NormalSpace implements Space{
    protected Creature creature = null;

    @Override
    public abstract boolean isSafeToOccupy(Creature creature);

    @Override
    public void occupy(Creature creature) throws IllegalAccessException {
        if (isSafeToOccupy(creature)){
            this.creature = creature;
        } else {
            throw new IllegalAccessException("Invalid Access!!!");
        }
    }

    @Override
    public void vacant() {
        if (this.creature != null) {
            this.creature = null;
        }
    }
}
