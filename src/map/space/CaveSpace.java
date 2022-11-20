package map.space;

import creature.Creature;


/**
 * Cave space where the agility of creatures increases
 */
public class CaveSpace extends SpecialSpace{
    private final int BONUS_AGILITY = 100;  // TODO: fetch this strength from config files
    private final String ANSI_CYAN = "\u001B[36m";  // TODO: fetch this from config files

    @Override
    public String bgColor() {
        return ANSI_CYAN;
    }

    @Override
    protected void addBonus(Creature creature) {
        creature.increaseAgility(BONUS_AGILITY);
    }

    @Override
    protected void removeBonus(Creature creature) {
        creature.decreaseAgility(BONUS_AGILITY);
    }
}
