package product;

import creature.AbstractCreature;

/**
 * Armor product class which save with some damage
 */
public class Armor extends Product implements Wearable{
    private final float damageReductionValue;

    /**
     * create a new armor
     *
     * @param name name of the armor
     * @param level level of the armor
     * @param price cost to buy
     * @param description description of the product
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
                String.valueOf(this.damageReductionValue)
        };
    }

    /**
     * @see Wearable#isWearable(AbstractCreature)
     * @param abstractCreature creature trying to use it
     * @return boolean
     */
    @Override
    public boolean isWearable(AbstractCreature abstractCreature) {
        return !abstractCreature.isArmorOn();
    }

    /**
     * @see Wearable#wear(AbstractCreature)
     * @param abstractCreature creature who wear it
     * @return boolean
     */
    @Override
    public boolean wear(AbstractCreature abstractCreature) {
        if (!isWearable(abstractCreature)){
            return false;
        }
        abstractCreature.setArmorOn(this);
        return true;
    }

    /**
     * @see Wearable#remove(AbstractCreature)
     * @param abstractCreature creature who want to remove
     * @return boolean
     */
    @Override
    public boolean remove(AbstractCreature abstractCreature) {
        if (!abstractCreature.isArmorOn()){
            return false;
        }
        abstractCreature.removeArmor();
        return true;
    }
}
