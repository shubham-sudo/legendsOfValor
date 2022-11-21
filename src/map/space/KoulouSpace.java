package map.space;

import creature.Creature;


/**
 * Koulou space where the strength of creatures increases
 */
public class KoulouSpace extends SpecialSpace{
    private final double BONUS_STRENGTH = 0.10;  // TODO: fetch this strength from config files
    private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";  // TODO: fetch this from config files

    @Override
    public String bgColor() {
        return ANSI_PURPLE_BACKGROUND;
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
