package map.space;

import creature.Creature;


/**
 * Bush space where the dexterity of creatures increases
 */
public class BushSpace extends SpecialSpace{
    private final double BONUS_DEXTERITY = 0.10;  // TODO: fetch this strength from config files
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";  // TODO: fetch this from config files

    @Override
    public String bgColor() {
        return ANSI_GREEN_BACKGROUND;
    }

    @Override
    protected void addBonus(Creature creature) {
        bonusMap.put(creature, creature.getDexterity());
        creature.increaseDexterity(creature.getDexterity() * BONUS_DEXTERITY);
    }

    @Override
    protected void removeBonus(Creature creature) {
        creature.decreaseDexterity(creature.getDexterity() - bonusMap.get(creature));
        bonusMap.remove(creature);
    }
}
