package creature;


import map.Position;

/**
 * Interface for defining several function for every type of creature
 */
public interface Creature {
    /**
     * Getter for home lane
     * @return integer lane id
     */
    int getHomeLane();

    /**
     * Getter for current health
     * @return double
     */
    double getHealth();

    /**
     * Getter for base health
     * @return double
     */
    double baseHealth();

    /**
     * Getter for mana
     * @return double
     */
    double getMana();

    /**
     * Getter for strength
     * @return double
     */
    double getStrength();

    /**
     * Getter for agility
     * @return double
     */
    double getAgility();

    /**
     * Getter for experience
     * @return double
     */
    double getExperience();

    /**
     * Getter for name
     * @return double
     */
    String getName();

    /**
     * Setter for home lane
     * @param laneNumber integer lane number
     */
    void setHomeLane(int laneNumber);

    /**
     * Getter for dodge
     * @return double
     */
    double getDodgeChance();

    /**
     * Getter for damage
     * @return double
     */
    double getDamage();

    /**
     * Getter for defence
     * @return double
     */
    double getDefence();

    /**
     * Getter for dexterity
     * @return double
     */
    double getDexterity();

    /**
     * Getter for position
     * @return Position
     */
    Position getCurrentPosition();

    /**
     * Setter for current Position
     * @param laneNumber lane number
     * @param row rowNumber
     * @param col colNumber
     */
    void setCurrentPosition(int laneNumber, int row, int col);

    /**
     * setter for id
     * @param id integer id
     */
    void setId(int id);   // TODO (shubham) this is temporary function .. fix the createCreature before remove

    /**
     * Decrease agility
     * @param agility double
     */
    void decreaseAgility(double agility);

    /**
     * Decrease damage / strength
     * @param damage double
     */
    void decreaseDamage(double damage);

    /**
     * Decrease defence
     * @param defence double
     */
    void decreaseDefence(double defence);

    /**
     * Decrease dexterity
     * @param dexterity double
     */
    void decreaseDexterity(double dexterity);

    /**
     * Decrease health
     * @param health double
     */
    void decreaseHealth(double health);

    /**
     * Decrease strength
     * @param strength double
     */
    void decreaseStrength(double strength);

    /**
     * Display valuer for creature
     * @return string
     */
    String displayValue();

    /**
     * getter for level
     * @return int
     */
    int getLevel();

    /**
     * getter for isAlive
     * @return boolean
     */
    boolean isAlive();

    /**
     * increase agility
     * @param agility double
     */
    void increaseAgility(double agility);

    /**
     * increase damage
     * @param damage double
     */
    void increaseDamage(double damage);

    /**
     * increase defence
     * @param defence double
     */
    void increaseDefence(double defence);

    /**
     * increase dexterity
     * @param dexterity double
     */
    void increaseDexterity(double dexterity);

    /**
     * increase health
     * @param health double
     */
    void increaseHealth(double health);

    /**
     * increase strength
     * @param strength double
     */
    void increaseStrength(double strength);

    /**
     * Check if type is equal
     * @param creature creature to check with
     * @return boolean
     */
    boolean typeEquals(Creature creature);
}
