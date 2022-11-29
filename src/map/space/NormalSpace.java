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

    /**
     * Is this space is safe to occupy by monster
     * @param creature creature
     * @return boolean
     */
    private boolean isSafeForMonster(Creature creature){
        if (!(creature instanceof Monster)){
            return true;
        }
        return this.monster == null || !this.monster.typeEquals(creature);
    }

    /**
     * Is this space is safe to occupy by the hero
     * @param creature creature
     * @return boolean
     */
    private boolean isSafeForHero(Creature creature){
        if (!(creature instanceof Hero)){
            return true;
        }
        return this.hero == null || !this.hero.typeEquals(creature);
    }

    /**
     * Is safe to occupy this space by any creature
     * @param creature creature object
     * @return boolean
     */
    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return isSafeForMonster(creature) && isSafeForHero(creature);
    }

    /**
     * Get opponent if present nearby
     * @param creature creature object to check opponent
     * @return creature object or null
     */
    @Override
    public Creature getOpponent(Creature creature) {
        if (creature instanceof Hero && monster != null){
            return monster;
        } else if (creature instanceof Monster && hero != null){
            return hero;
        }
        return null;
    }

    /**
     * Check if opponent is nearby
     * @param creature creature object
     * @return Creature object
     */
    @Override
    public boolean hasOpponent(Creature creature) {
        if (creature instanceof Hero && monster != null){
            return true;
        } else if (creature instanceof Monster && hero != null){
            return true;
        }
        return false;
    }

    /**
     * set monster or hero variable when creature enters
     * @param creature creature object
     */
    private void creatureEnter(Creature creature){
        if (creature instanceof Monster){
            this.monster = creature;
        } else {
            this.hero = creature;
        }
    }

    /**
     * When creature exit remove the power creature got from this space
     * @param creature creature who leaves
     */
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

    /**
     * Occupy the space
     * @param creature creature who want to occupy
     * @throws IllegalAccessException error is not possible
     */
    @Override
    public void occupy(Creature creature) throws IllegalAccessException {
        if (isSafeToOccupy(creature)){
            this.creatureEnter(creature);
        } else {
            throw new IllegalAccessException("Invalid Access!!!");
        }
    }

    /**
     * Vacant will remove extra powers creature got
     * @param creature creature who is moving
     */
    @Override
    public void vacant(Creature creature) {
        if ((this.hero != null && this.hero.equals(creature))
                || (this.monster != null && this.monster.equals(creature))){
            creatureExit(creature);
        }
    }
}
