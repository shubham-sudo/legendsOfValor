package map.space;

import creature.Creature;


/**
 * Cave space where the agility of creatures increases
 */
public class CaveSpace extends SpecialSpace{
    private final int bonusAgility = 100;  // TODO: fetch this strength from config files

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return this.creature == null || !this.creature.equals(creature);
    }

    @Override
    protected void addBonus() {
        this.creature.increaseAgility(bonusAgility);
    }

    @Override
    protected void removeBonus() {
        this.creature.decreaseAgility(bonusAgility);
    }
}
