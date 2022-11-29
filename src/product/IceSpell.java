package product;

import creature.*;


/**
 * Ice spell is a Spell which reduce the damage to the opponent
 */

public class IceSpell extends Spell{
    private static final String affectedAttribute = "Damage";

    /**
     * Creates the new Ice spell
     * @param name         given name
     * @param level        level of spell
     * @param price        cost of the purchasing
     * @param description  given description
     * @param damageValue  damage value for any attribute
     * @param requiredMana required mana to cast it
     */
    public IceSpell(String name, int level, float price, String description, float damageValue, float requiredMana) {
        super(name, level, price, description, damageValue, requiredMana);
    }

    /**
     * Getter for the affected attribute
     * @return string
     */
    @Override
    public String affectedAttribute() {
        return affectedAttribute;
    }

    /**
     * Apply ice spell effects on the opponent
     * @see Spell#applySpellEffects(Creature, Creature)
     * @param creature creature to hit spell on
     */
    @Override
    public void applySpellEffects(Creature creature, Creature opponent) {
        creature.decreaseDamage(getSpellDamage(this.getDamageValue(), creature.getDexterity()));
    }
}
