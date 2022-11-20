package map.space;

import creature.Creature;


/**
 * Koulou space where the strength of creatures increases
 */
public class KoulouSpace extends SpecialSpace{
    private final int BONUS_STRENGTH = 100;  // TODO: fetch this strength from config files
    private final String ANSI_BLUE = "\u001B[34m";  // TODO: fetch this from config files

    @Override
    public String bgColor() {
        return ANSI_BLUE;
    }

    @Override
    protected void addBonus(Creature creature) {
        creature.increaseStrength(BONUS_STRENGTH);
    }

    @Override
    protected void removeBonus(Creature creature) {
        creature.decreaseStrength(BONUS_STRENGTH);
    }
}
