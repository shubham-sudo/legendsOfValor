package map.space;

import creature.Creature;


/**
 * Bush space where the dexterity of creatures increases
 */
public class BushSpace extends SpecialSpace{
    private final int bonusDexterity = 100;  // TODO: fetch this strength from config files

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return this.creature == null || !this.creature.equals(creature);
    }

    @Override
    protected void addBonus() {
        this.creature.increaseDexterity(bonusDexterity);
    }

    @Override
    protected void removeBonus() {
        this.creature.decreaseDexterity(bonusDexterity);
    }
}
