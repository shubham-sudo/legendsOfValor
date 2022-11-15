package product;


import creature.AbstractCreature;

/**
 * Weapon product to fight against opponent
 */
public class Weapon extends Product implements Wearable{
    private final float damageValue;
    private final int requiredHands;

    /**
     * Creates a new weapon
     * @param name name of weapon
     * @param level level of weapon
     * @param price cost of the weapon
     * @param description description of the weapon
     * @param damage damage produced by the weapon
     * @param requiredHand number of hands required to hold it
     */
    public Weapon(String name, int level, float price, String description, float damage, int requiredHand) {
        super(name, level, price, description);
        this.damageValue = damage;
        this.requiredHands = requiredHand;
    }

    /**
     * Getter for the damage
     * @return float value
     */
    public float getDamageValue() {
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
                String.valueOf(this.damageValue)
        };
    }

    /**
     * @see Wearable#isWearable(AbstractCreature)
     * @param abstractCreature creature trying to use it
     * @return boolean
     */
    @Override
    public boolean isWearable(AbstractCreature abstractCreature) {
        return abstractCreature.getFreeHands() >= this.getRequiredHands();
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
        return abstractCreature.tryEquipWeapon(this);
    }

    /**
     * @see Wearable#remove(AbstractCreature)
     * @param abstractCreature creature who want to remove
     * @return boolean
     */
    @Override
    public boolean remove(AbstractCreature abstractCreature) {
        if (abstractCreature.getBusyHands() < this.getRequiredHands()){
            return false;
        }
        return abstractCreature.removeWeapon(this);
    }
}