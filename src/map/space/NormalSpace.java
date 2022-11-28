package map.space;

import creature.Creature;
import creature.Hero;
import creature.Monster;


/**
 * Base class for all types of Normal spaces
 * @see NexusSpace
 * @see InaccessibleSpace
 * @see PlainSpace
 */
public abstract class NormalSpace implements Space{
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

    @Override
    public Creature getOpponent(Creature creature) {
        if (creature instanceof Hero && monster != null){
            return monster;
        } else if (creature instanceof Monster && hero != null){
            return hero;
        }
        return null;
    }

    @Override
    public boolean hasOpponent(Creature creature) {
        if (creature instanceof Hero && monster != null){
            return true;
        } else if (creature instanceof Monster && hero != null){
            return true;
        }
        return false;
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

    /**
     * Display creature on space or empty String
     * @see Space
     * @return String
     */
    @Override
    public String displayValue() {
        return Space.getValue(this.hero, this.monster);
    }

    @Override
    public void occupy(Creature creature) throws IllegalAccessException {
        if (isSafeToOccupy(creature)){
            this.creatureEnter(creature);
        } else {
            throw new IllegalAccessException("Invalid Access!!!");
        }
    }

    @Override
    public void vacant(Creature creature) {
        if ((this.hero != null && this.hero.equals(creature))
                || (this.monster != null && this.monster.equals(creature))){
            creatureExit(creature);
        }
    }
}
