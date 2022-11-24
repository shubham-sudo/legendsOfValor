package product;

import creature.*;

/**
 * Armor product class which save with some damage
 */
public class Armor extends Product implements Equipable{
    private final float damageReductionValue;

    /**
     * create a new armor
     * @param name            name of the armor
     * @param level           level of the armor
     * @param price           cost to buy
     * @param description     description of the product
     * @param damageReduction saves from the damage
     */
    public Armor(String name, int level, float price, String description, float damageReduction) {
        super(name, level, price, description);
        this.damageReductionValue = damageReduction;
    }

    /**
     * getter for the damage reduction
     * @return float
     */
    public float getDamageReductionValue() {
        return damageReductionValue;
    }

    /**
     * @see Equipable#isSafeToEquip(Hero)
     * @param hero creature trying to use it
     * @return boolean
     */
    @Override
    public boolean isSafeToEquip(Hero hero) {
        return hero.getArmor() == null;
    }

    /**
     * @see Equipable#equip(Hero)
     * @param hero creature who wear it
     * @return boolean
     */
    @Override
    public boolean equip(Hero hero) {
        if (isSafeToEquip(hero)){
            hero.equipArmor(this);
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
        hero.dropArmor(this);
    }
}
