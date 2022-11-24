package product;


import creature.*;

/**
 * Weapon product to fight against opponent
 */
public class Weapon extends Product implements Equipable{
    private final double damageValue;
    private final int requiredHands;

    /**
     * creates a new product
     * @param name          name of the product
     * @param level         level of the product
     * @param price         price of the product
     * @param description   description of the product
     * @param damage        damage produced by the weapon
     * @param requiredHands number of hands required to hold it
     */
    public Weapon(String name, int level, float price, String description, double damage, int requiredHands) {
        super(name, level, price, description);
        this.damageValue = damage;
        this.requiredHands = requiredHands;
    }

    /**
     * Getter for the damage
     * @return float value
     */
    public double getDamageValue() {
        return damageValue;
    }

    /**
     * Getter for number of hands required
     * @return int value
     */
    public int getRequiredHands() {
        return requiredHands;
    }

    /**
     * @see Equipable#isSafeToEquip(Hero)
     * @param hero hero trying to equip it
     * @return boolean
     */
    @Override
    public boolean isSafeToEquip(Hero hero) {
        return hero.getFreeHands() >= this.getRequiredHands();
    }

    /**
     * @see Equipable#equip(Hero)
     * @param hero hero who equip it
     * @return boolean
     */
    @Override
    public boolean equip(Hero hero) throws IllegalAccessException {
        if (isSafeToEquip(hero)){
            hero.equipWeapon(this);
            return true;
        }
        return false;
    }

    /**
     * @see Equipable#drop(Hero)
     * @param hero creature who want to remove
     */
    @Override
    public void drop(Hero hero) {
        hero.dropWeapon(this);
    }
}