package map.space;

import creature.Creature;

/**
 * Base class for all type of special spaces
 * @see BushSpace
 * @see CaveSpace
 * @see KoulouSpace
 */
public abstract class SpecialSpace implements Space {
    protected Creature creature = null;

    public abstract boolean isSafeToOccupy(Creature creature);

    protected abstract void addBonus();

    protected abstract void removeBonus();

    public void occupy(Creature creature) throws IllegalAccessException{
        if (isSafeToOccupy(creature)){
            this.creature = creature;
            addBonus();
        } else {
            throw new IllegalAccessException("Invalid Access!!!");
        }
    }

    public void vacant() {
        if (this.creature != null){
            removeBonus();
            this.creature = null;
        }
    }
}
