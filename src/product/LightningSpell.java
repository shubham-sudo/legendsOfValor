package product;

import creature.*;


/**
 * Lightning spell is a Spell which reduce the agility of the opponent
 */

public class LightningSpell extends Spell{

    /**
     * Creates the new Lightning spell
     * @param name         given name
     * @param level        level of spell
     * @param price        cost of the purchasing
     * @param description  given description
     * @param damageValue  damage value for any attribute
     * @param requiredMana required mana to cast it
     */
    public LightningSpell(String name, int level, float price, String description, float damageValue, float requiredMana) {
        super(name, level, price, description, damageValue, requiredMana);
    }

    /**
     * Apply lightning spell effects on the opponent
     * @see Spell#applySpellEffects(Creature)
     * @param creature creature to hit spell on
     */
    @Override
    public void applySpellEffects(Creature creature) {
        creature.decreaseAgility(this.getDamageValue());
    }
}
