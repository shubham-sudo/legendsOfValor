package product;

import creature.*;


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
     * @param name         name of the spell
     * @param level        level of the spell
     * @param price        cost of the spell
     * @param description  description of the product
     * @param damageValue  damageValue for the product
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
     * Get affected attribute of Creature
     */
    public abstract String affectedAttribute();

    /**
     * Getter for the damage value of the spell
     * @return float value
     */
    public float getDamageValue() {
        return this.damageValue;
    }

    /**
     * @see Consumable#isSafeToConsume(Hero)
     * @param hero creature who is trying to consume it
     * @return boolean
     */
    @Override
    public boolean isSafeToConsume(Hero hero) {
        return !this.consumed && hero.getMana() >= this.getRequiredMana();
    }

    /**
     * @see Consumable#consume(Hero)
     * @param hero creature who used this product
     * @return boolean
     */
    @Override
    public boolean consume(Hero hero) {
        if (isSafeToConsume(hero)){
            hero.decreaseMana(this.requiredMana);
            this.consumed = true;
            hero.inventory().removeProduct(this);
            return true;
        }
        return false;
    }
}
