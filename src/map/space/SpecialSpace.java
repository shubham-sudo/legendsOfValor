package map.space;

import creature.Creature;
import creature.Hero;
import creature.Monster;

/**
 * Base class for all type of special spaces
 * @see BushSpace
 * @see CaveSpace
 * @see KoulouSpace
 */
public abstract class SpecialSpace implements Space {
    protected Creature hero = null;
    protected Creature monster = null;

    private boolean isSafeForMonster(Creature creature){
        if (!(creature instanceof Monster)){
            return true;
        }
        return this.monster == null || !this.monster.typeEquals(creature);
    }

    private boolean isSafeForHero(Creature creature){
        if (!(creature instanceof Hero)){
            return true;
        }
        return this.hero == null || !this.hero.typeEquals(creature);
    }

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return isSafeForMonster(creature) && isSafeForHero(creature);
    }

    /**
     * Display creature on space or empty String
     * @see Space
     * @return String
     */
    @Override
    public String displayValue() {
        return Space.getValue(this.hero, this.monster);
    }

    private void creatureEnter(Creature creature){
        if (creature instanceof Monster){
            this.monster = creature;
        } else {
            this.hero = creature;
        }
    }

    private void creatureExit(Creature creature){
        if (creature instanceof Monster){
            this.monster = null;
        } else {
            this.hero = null;
        }
    }

    protected abstract void addBonus(Creature creature);

    protected abstract void removeBonus(Creature creature);

    @Override
    public void occupy(Creature creature) throws IllegalAccessException{
        if (isSafeToOccupy(creature)){
            creatureEnter(creature);
            addBonus(creature);
        } else {
            throw new IllegalAccessException("Invalid Access!!!");
        }
    }

    @Override
    public void vacant(Creature creature) {
        if (this.hero.equals(creature) || this.monster.equals(creature)){
            removeBonus(creature);
            creatureExit(creature);
        }
    }
}
