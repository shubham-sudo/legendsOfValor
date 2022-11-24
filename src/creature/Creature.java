package creature;


/**
 * Interface for defining several function for every type of creature
 */
public interface Creature {
    void decreaseAgility(double agility);

    void decreaseDamage(double damage);

    void decreaseDefence(double defence);

    void decreaseDexterity(double dexterity);

    void decreaseHealth(double health);

    void decreaseStrength(double strength);

    String displayValue();

    void increaseAgility(double agility);

    void increaseDamage(double damage);

    void increaseDefence(double defence);

    void increaseDexterity(double dexterity);

    void increaseHealth(double health);

    void increaseStrength(double strength);

    boolean typeEquals(Creature creature);
}
