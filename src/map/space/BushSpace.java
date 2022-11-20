package map.space;

import creature.Creature;


/**
 * Bush space where the dexterity of creatures increases
 */
public class BushSpace extends SpecialSpace{
    private final int BONUS_DEXTERITY = 100;  // TODO: fetch this strength from config files
    private final String ANSI_GREEN = "\u001B[32m";  // TODO: fetch this from config files

    @Override
    public String bgColor() {
        return ANSI_GREEN;
    }

    @Override
    protected void addBonus(Creature creature) {
        creature.increaseDexterity(BONUS_DEXTERITY);
    }

    @Override
    protected void removeBonus(Creature creature) {
        creature.decreaseDexterity(BONUS_DEXTERITY);
    }
}
