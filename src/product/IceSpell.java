package product;

import creature.AbstractCreature;

/**
 * Ice spell is a Spell which reduce the damage to the opponent
 */

public class IceSpell extends Spell{

    /**
     * Creates the new Ice spell
     * @param name given name
     * @param level level of spell
     * @param price cost of the purchasing
     * @param description given description
     * @param damageValue damage value for any attribute
     * @param requiredMana required mana to cast it
     */
    public IceSpell(String name, int level, float price, String description, float damageValue, float requiredMana) {
        super(name, level, price, description, damageValue, requiredMana);
    }

    /**
     * Apply ice spell effects on the opponent
     * @see Spell#applySpellEffects(AbstractCreature)
     * @param abstractCreature creature to hit spell on
     */
    @Override
    public void applySpellEffects(AbstractCreature abstractCreature) {
        System.out.println("Hitting Ice Spell on "+abstractCreature.getName());
        abstractCreature.reduceDamage(this.getDamageValue());
    }
}
