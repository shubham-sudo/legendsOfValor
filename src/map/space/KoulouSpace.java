package map.space;

import creature.Creature;


/**
 * Koulou space where the strength of creatures increases
 */
public class KoulouSpace extends SpecialSpace{
    private final int bonusStrength = 100;  // TODO: fetch this strength from config files

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return this.creature == null || !this.creature.equals(creature);
    }

    @Override
    protected void addBonus() {
        this.creature.increaseStrength(bonusStrength);
    }

    @Override
    protected void removeBonus() {
        this.creature.decreaseStrength(bonusStrength);
    }
}
