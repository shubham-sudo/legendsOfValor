package map.space;

import creature.Creature;


/**
 * Cave space where the agility of creatures increases
 */
public class CaveSpace extends SpecialSpace{
    private final double BONUS_AGILITY = 0.10;
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";

    /**
     * Return the background color for this particular space
     *
     * @see Space#bgColor()
     * @return String
     */
    @Override
    public String bgColor() {
        return ANSI_YELLOW_BACKGROUND;
    }

    /**
     * Add bonus to the attributes of the creature
     *
     * @see SpecialSpace#addBonus(Creature)
     * @param creature creature object
     */

    @Override
    protected void addBonus(Creature creature) {
        bonusMap.put(creature, creature.getAgility());
        creature.increaseAgility(creature.getAgility() * BONUS_AGILITY);
    }

    /**
     * Removes the extra powers when creature leaves the space
     *
     * @see SpecialSpace#removeBonus(Creature)
     * @param creature creature object
     */
    @Override
    protected void removeBonus(Creature creature) {
        creature.decreaseAgility(creature.getAgility() - bonusMap.get(creature));
        bonusMap.remove(creature);
    }
}
