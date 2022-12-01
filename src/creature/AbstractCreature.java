package creature;


import map.Position;

/**
 * Abstract class for all type of creatures with also implements Creature interface for basic operations
 */
public abstract class AbstractCreature implements Creature {
    protected final String name;
    protected int level;
    protected double baseHealth;
    protected double strength;
    protected double agility;
    private boolean alive;
    private int homeLane;
    private final Position currentPosition;

    /**
     * Creates and object of AbstractCreature class
     * @param name name of the creature
     * @param baseHealth starting health of the creature
     */
    public AbstractCreature(String name, double baseHealth, double strength, double agility){
        this.name = name;
        this.alive = true;
        this.level = 1;
        this.baseHealth = baseHealth;
        this.strength = strength;
        this.agility = agility;
        this.currentPosition = new Position();
    }

    /**
     * Getter for current health
     *
     * @see Creature#getHealth()
     * @return double
     */
    @Override
    public double getHealth() {
        return this.baseHealth;
    }

    /**
     * Getter for strength
     * @return double
     */
    @Override
    public double getStrength() {
        return strength;
    }

    /**
     * Getter for agility
     * @return double
     */
    @Override
    public double getAgility() {
        return agility;
    }

    /**
     * Getter for mana
     * @return double
     */
    @Override
    public double getMana() {
        return 0;
    }

    /**
     * Getter for experience
     * @return double
     */
    @Override
    public double getExperience() {
        return 0;
    }

    /**
     * Getter for base health
     * @return double
     */
    @Override
    public double baseHealth(){
        return this.baseHealth;
    }

    /**
     * Getter for home lane
     * @return integer lane id
     */
    @Override
    public int getHomeLane(){
        return this.homeLane;
    }

    /**
     * Setter for home lane
     * @param laneNumber integer lane number
     */
    @Override
    public void setHomeLane(int laneNumber){
        this.homeLane = laneNumber;
    }

    /**
     * Getter for dodge
     * @return double
     */
    @Override
    public double getDodgeChance() {
        return this.agility;
    }

    /**
     * Getter for dexterity
     * @return double
     */
    @Override
    public double getDexterity(){
        return 0;
    }

    /**
     * Getter for position
     * @return Position
     */
    @Override
    public Position getCurrentPosition(){
        return this.currentPosition != null ? this.currentPosition : null;
    }

    /**
     * Setter for current Position
     * @param laneNumber lane number
     * @param row rowNumber
     * @param col colNumber
     */
    @Override
    public void setCurrentPosition(int laneNumber, int row, int col){
        this.currentPosition.laneNumber = laneNumber;
        this.currentPosition.rowNumber = row;
        this.currentPosition.colNumber = col;
    }

    /**
     * Decrease agility
     * @param agility double
     */
    @Override
    public void decreaseAgility(double agility) {
        this.agility = (this.agility - agility) < 0 ? 0 : (this.agility - agility);
    }

    /**
     * Decrease dexterity
     * @param dexterity double
     */
    @Override
    public void decreaseDexterity(double dexterity){}

    /**
     * Decrease health
     * @param health double
     */
    @Override
    public void decreaseHealth(double health) {
        this.baseHealth = Math.max((this.baseHealth - health), 0);
        if (this.baseHealth <= 0){
            setDead();
        }
    }

    /**
     * Decrease strength
     * @param strength double
     */
    @Override
    public void decreaseStrength(double strength) {
        this.strength = (this.strength - strength) < 0 ? 0 : (this.strength - strength);
    }

    /**
     * getter for level
     * @return int
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * getter for name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * increase agility
     * @param agility double
     */
    @Override
    public void increaseAgility(double agility) {
        this.agility += agility;
    }

    /**
     * increase dexterity
     * @param dexterity double
     */
    @Override
    public void increaseDexterity(double dexterity){}

    /**
     * increase health
     * @param health double
     */
    @Override
    public void increaseHealth(double health) {
        this.baseHealth += health;
    }

    /**
     * increase strength
     * @param strength double
     */
    @Override
    public void increaseStrength(double strength) {
        this.strength += strength;
    }

    /**
     * getter for alive
     * @return boolean
     */
    @Override
    public boolean isAlive(){
        return this.alive;
    }

    /**
     * Leveling up a creature
     */
    protected void levelUp(){
        this.level++;
        this.baseHealth += this.level * 100;
    }

    /**
     * Revive creature
     */
    protected void revive(){
        this.alive = true;
    }

    /**
     * set creature dead
     */
    public void setDead(){
        this.alive = false;
    }
}
