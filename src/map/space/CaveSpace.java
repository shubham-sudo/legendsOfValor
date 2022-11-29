package map.space;

import creature.Creature;


/**
 * Cave space where the agility of creatures increases
 */
public class CaveSpace extends SpecialSpace{
    private final double BONUS_AGILITY = 0.10;  // TODO: fetch this strength from config files
    private static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";  // TODO: fetch this from config files

    @Override
    public String bgColor() {
        return ANSI_YELLOW_BACKGROUND;
    }

    @Override
    protected void addBonus(Creature creature) {
        bonusMap.put(creature, creature.getAgility());
        creature.increaseAgility(creature.getAgility() * BONUS_AGILITY);
    }

    @Override
    protected void removeBonus(Creature creature) {
        creature.decreaseAgility(creature.getAgility() - bonusMap.get(creature));
        bonusMap.remove(creature);
    }
}
