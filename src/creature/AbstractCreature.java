package creature;


import battle.CreatureBattleMove;

/**
 * Abstract class for all type of creatures with also implements Creature interface for basic operations
 */
public abstract class AbstractCreature implements Creature {
    protected final String name;
    protected int level;
    protected int health;
    protected int currentHealth;
    private boolean alive;

    /**
     * Creates and object of AbstractCreature class
     * @param name name of the creature
     * @param health starting health of the creature
     */
    public AbstractCreature(String name, int health){
        this.name = name;
        this.alive = true;
        this.level = 1;
        this.health = health;
        this.currentHealth = health;
    }

    /**
     * getter for alive
     * @return boolean
     */
    public boolean isAlive(){
        return this.alive;
    }

    /**
     * if health of creature is 0 call setDead
     */
    public void setDead(){
        this.alive = false;
    }

    /**
     * to make creature alive again
     */
    public void energize(){
        this.alive = true;
    }

    /**
     * getter for name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * getter for level
     * @return int
     */
    public int getLevel() {
        return level;
    }

    /**
     * setting or updating the health
     * @param hp integer
     */
    public void setHp(int hp) {
        this.health = hp * this.level;
    }

    /**
     * setter for level
     * @param level integer
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * setter for current health
     * @param currentHp integer
     */
    public void setCurrentHp(int currentHp) {
        this.currentHealth = currentHp;
    }

    /**
     * return information in table format to print on console
     * @return String[]
     */
    public abstract String[] seekInformation();

    /**
     * get the impact of attack from this creature
     * @param creatureBattleMove based on the ATTACK OR CAST
     * @return integer
     */
    public abstract int getAttackImpact(CreatureBattleMove creatureBattleMove);

    /**
     * Update the impact of attack from opponent to the health of this creature
     * @param damage integer
     */
    public abstract void updateAttackImpact(int damage);

    /**
     * Level up favoured skills more while leveling up creature
     */
    public abstract void levelUpFavouredSkills();

    /**
     * level up creature with this
     */
    public void levelUp(){
        this.level++;
        this.health += this.level * 100;
        this.levelUpFavouredSkills();
    }

    /**
     * Regain is to regain some exp and health at end of every battle
     */
    public abstract void regain();

    /**
     * Equals method for comparison of same type objects
     * @param o other object
     * @return boolean true if same
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AbstractCreature)){
            return false;
        }
        AbstractCreature other = (AbstractCreature) o;
        return other.name.equals(this.name);
    }

}
