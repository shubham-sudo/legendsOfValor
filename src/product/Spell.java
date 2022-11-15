package product;

import creature.AbstractCreature;

/**
 * Spell are used to decrease few attributes of the opponent
 * This is the abstract class for different type of spells
 */
public abstract class Spell extends Product implements SpellEffects, Consumable {
    private boolean consumed;
    private final float damageValue;
    private final float requiredMana;

    /**
     * Creates a new spell object
     * @param name name of the spell
     * @param level level of the spell
     * @param price cost of the spell
     * @param description description of the product
     * @param damageValue damageValue for the product
     * @param requiredMana required mana cost to use this spell
     */
    public Spell(String name, int level, float price, String description, float damageValue, float requiredMana) {
        super(name, level, price, description);
        this.damageValue = damageValue;
        this.requiredMana = requiredMana;
        this.consumed = false;
    }

    /**
     * Getter for required mana for the spell
     * @return float value
     */
    public float getRequiredMana() {
        return this.requiredMana;
    }

    /**
     * Getter for the damage value of the spell
     * @return float value
     */
    public float getDamageValue() {
        return this.damageValue;
    }

    /**
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
     * @see Consumable#isConsumable(AbstractCreature)
     * @param abstractCreature creature who is trying to consume it
     * @return boolean
     */
    @Override
    public boolean isConsumable(AbstractCreature abstractCreature) {
        return !this.consumed && abstractCreature.getMana() > this.getRequiredMana();
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
                String.valueOf(this.getDamageValue()),
                String.valueOf(this.getRequiredMana())
        };
    }
}
