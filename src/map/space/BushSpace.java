package map.space;

import creature.Creature;


/**
 * Bush space where the dexterity of creatures increases
 */
public class BushSpace extends SpecialSpace{
    private final double BONUS_DEXTERITY = 0.10;
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    /**
     * Return the background color for this particular space
     *
     * @see Space#bgColor()
     * @return String
     */
    @Override
    public String bgColor() {
        return ANSI_GREEN_BACKGROUND;
    }

    /**
     * Add bonus to the attributes of the creature
     *
     * @see SpecialSpace#addBonus(Creature)
     * @param creature creature object
     */
    @Override
    protected void addBonus(Creature creature) {
        bonusMap.put(creature, creature.getDexterity());
        creature.increaseDexterity(creature.getDexterity() * BONUS_DEXTERITY);
    }

    /**
     * Removes the extra powers when creature leaves the space
     *
     * @see SpecialSpace#removeBonus(Creature)
     * @param creature creature object
     */
    @Override
    protected void removeBonus(Creature creature) {
        creature.decreaseDexterity(creature.getDexterity() - bonusMap.get(creature));
        bonusMap.remove(creature);
    }
}
