package product;

import creature.*;

import java.util.Set;

/**
 * Potion products are healer to heal some attributes of the creature
 */
public class Potion extends  Product implements Consumable{
    private boolean consumed;
    private final float healValue;
    private final Set<CreatureAttributes> attributesAffected;

    /**
     * Create a new potion product
     * @param name name of the potion
     * @param level level of the potion
     * @param price cost of the potion
     * @param description description of the product
     * @param healingValue healing value
     * @param attributesAffected attributes healed on use
     */
    public Potion(String name, int level, float price, String description, float healingValue, Set<CreatureAttributes> attributesAffected) {
        super(name, level, price, description);
        this.consumed = false;
        this.healValue = healingValue;
        this.attributesAffected = attributesAffected;
    }

    /**
     * getter for the heal value
     * @return float
     */
    public float getHealValue() {
        return healValue;
    }

    /**
     * Getter for the attributes affected on use
     * @return set of attributes
     */
    public Set<CreatureAttributes> getAttributesAffected() {
        return attributesAffected;
    }

    /**
     * @see Product#getInfo()
     * @return string array
     */
    @Override
    public String[] getInfo() {
        return new String[]{
                this.name,
                String.valueOf(this.level),
                String.valueOf(this.price),
                this.productCode,
                this.getClass().getSimpleName(),
                String.valueOf(this.healValue),
                CreatureAttributes.flatAttributesAffected(this.attributesAffected)
        };
    }

    /**
     * Consume a product after use
     * @see Consumable#consume(AbstractCreature)
     * @param abstractCreature creature who used this product
     * @return boolean
     */
    @Override
    public boolean consume(AbstractCreature abstractCreature) {
        if (!isConsumable(abstractCreature)){
            return false;
        }
        this.consumed = true;
        abstractCreature.notifyObservers();
        return true;
    }

    /**
     * True if you can be consumed again
     * @see Consumable#isConsumable(AbstractCreature)
     * @param abstractCreature creature who is trying to consume it
     * @return boolean
     */
    @Override
    public boolean isConsumable(AbstractCreature abstractCreature) {
        return !consumed;
    }
}
