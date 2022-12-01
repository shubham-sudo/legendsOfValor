package map.space;

import creature.Creature;


/**
 * Koulou space where the strength of creatures increases
 */
public class KoulouSpace extends SpecialSpace{
    private final double BONUS_STRENGTH = 0.10;
    private static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";

    /**
     * Return the background color for this particular space
     *
     * @see Space#bgColor()
     * @return String
     */
    @Override
    public String bgColor() {
        return ANSI_PURPLE_BACKGROUND;
    }

    /**
     * Add bonus to the attributes of the creature
     *
     * @see SpecialSpace#addBonus(Creature)
     * @param creature creature object
     */

    @Override
    protected void addBonus(Creature creature) {
        bonusMap.put(creature, creature.getStrength());
        creature.increaseStrength(creature.getStrength() * BONUS_STRENGTH);
    }

    /**
     * Removes the extra powers when creature leaves the space
     * 
     * @see SpecialSpace#removeBonus(Creature)
     * @param creature creature object
     */
    @Override
    protected void removeBonus(Creature creature) {
        creature.decreaseStrength(creature.getStrength() - bonusMap.get(creature));
        bonusMap.remove(creature);
    }
}
