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

    @Override
    public int getHomeLane(){
        return this.homeLane;
    }

    @Override
    public void setHomeLane(int laneNumber){
        this.homeLane = laneNumber;
    }

    @Override
    public Position getCurrentPosition(){
        return this.currentPosition != null ? this.currentPosition : null;
    }

    @Override
    public void setCurrentPosition(int laneNumber, int row, int col){
        this.currentPosition.laneNumber = laneNumber;
        this.currentPosition.rowNumber = row;
        this.currentPosition.colNumber = col;
    }

    @Override
    public void decreaseAgility(double agility) {
        this.agility = (this.agility - agility) < 0 ? 0 : (this.agility - agility);
    }

    @Override
    public void decreaseDexterity(double dexterity){}

    @Override
    public void decreaseHealth(double health) {
        this.baseHealth = Math.max((this.baseHealth - health), 0);
    }

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

    @Override
    public void increaseAgility(double agility) {
        this.agility += agility;
    }

    @Override
    public void increaseDexterity(double dexterity){}

    @Override
    public void increaseHealth(double health) {
        this.baseHealth += health;
    }

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
