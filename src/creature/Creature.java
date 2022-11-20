package creature;

import product.Armor;
import product.Weapon;

/**
 * Interface for defining several function for every type of creature
 */
public interface Creature {
    /**
     * Getter for the mana
     * @return integer
     */
    float getMana();

    /**
     * Return true if any armor is on
     * @return boolean
     */
    boolean isArmorOn();

    /**
     * Put on some armor
     * @param armor Product
     */
    void setArmorOn(Armor armor);

    /**
     * Remove armor if you want to replace with another
     */
    void removeArmor();

    /**
     * Getter for getting number of free hands
     * @return integer
     */
    int getFreeHands();

    /**
     * get number of busy hands
     * @return integer
     */
    int getBusyHands();

    /**
     * Notify all the observers for any change for this creature
     */
    void notifyObservers();

    /**
     * reduce mana when cast a spell in attack
     * @param mana mana to reduce
     */
    void reduceMana(float mana);

    /**
     * Reduce damage when armor is on
     * @param damage number of damage to reduce
     */
    void reduceDamage(float damage);

    /**
     * Reduce defence if removed any armor
     * @param defence number
     */
    void reduceDefence(float defence);

    /**
     * reduce agility when attacked with specific spell
     * @param agility float number
     */
    void reduceAgility(float agility);

    /**
     * Improve defence if armor is on
     * @param defence float number
     */
    void improveDefence(float defence);

    /**
     * try to equip weapon if you have some free hands
     * @param weapon product
     * @return true if equipped
     */
    boolean tryEquipWeapon(Weapon weapon);

    /**
     * try to remove weapon if you want to replace
     * @param weapon product
     * @return true if possible
     */
    boolean removeWeapon(Weapon weapon);

    /**
     * improve damage when had some specific potion
     * @param damage number of points to increase
     */
    void improveDamage(float damage);

    void increaseStrength(int bonusStrength);

    void decreaseStrength(int bonusStrength);

    void increaseAgility(int bonusAgility);

    void decreaseAgility(int bonusAgility);

    void increaseDexterity(int bonusDexterity);

    void decreaseDexterity(int bonusDexterity);

    String displayValue();

    boolean typeEquals(Creature creature);
}
