package product;

import creature.AbstractCreature;

/**
 * Lightning spell is a Spell which reduce the agility of the opponent
 */

public class LightningSpell extends Spell{

    /**
     * Creates the new Lightning spell
     * @param name given name
     * @param level level of spell
     * @param price cost of the purchasing
     * @param description given description
     * @param damageValue damage value for any attribute
     * @param requiredMana required mana to cast it
     */
    public LightningSpell(String name, int level, float price, String description, float damageValue, float requiredMana) {
        super(name, level, price, description, damageValue, requiredMana);
    }

    /**
     * Apply lightning spell effects on the opponent
     * @see Spell#applySpellEffects(AbstractCreature)
     * @param abstractCreature creature to hit spell on
     */
    @Override
    public void applySpellEffects(AbstractCreature abstractCreature) {
        System.out.println("Hitting Lightning Spell on "+abstractCreature.getName());
        abstractCreature.reduceAgility(this.getDamageValue());
    }
}
