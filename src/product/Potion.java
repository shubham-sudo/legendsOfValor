package product;

import creature.*;

import java.util.Set;

/**
 * Potion products are healer to heal some attributes of the creature
 */
public class Potion extends  Product implements Consumable{
    private boolean consumed;
    private final double healValue;
    private final Set<CreatureAttributes> attributes;

    /**
     * Create a new potion product
     * @param name         name of the potion
     * @param level        level of the potion
     * @param price        cost of the potion
     * @param description  description of the product
     * @param healingValue healing value
     * @param attributes   attributes healed on use
     */
    public Potion(String name, int level, float price, String description, double healingValue, Set<CreatureAttributes> attributes) {
        super(name, level, price, description);
        this.consumed = false;
        this.healValue = healingValue;
        this.attributes = attributes;
    }

    /**
     * getter for the heal value
     * @return float
     */
    public double getHealValue() {
        return healValue;
    }

    /**
     * Getter for the attributes affected on use
     * @return set of attributes
     */
    public Set<CreatureAttributes> getAttributes() {
        return attributes;
    }

    /**
     * True if you can be consumed again
     * @see Consumable#isSafeToConsume(Hero)
     * @param hero creature who wants to consume
     * @return boolean
     */
    @Override
    public boolean isSafeToConsume(Hero hero) {
        return !consumed;
    }

    /**
     * Consume a product
     * @see Consumable#consume(Hero)
     * @param hero creature who used this product
     * @return boolean
     */
    @Override
    public boolean consume(Hero hero) {
        if (!isSafeToConsume(hero)){
            hero.usePotion(this);
            this.consumed = true;
            return true;
        }
        return false;
    }
}
