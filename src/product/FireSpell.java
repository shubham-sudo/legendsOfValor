package product;

import creature.AbstractCreature;

/**
 * Fire spell is a Spell which reduce the defence of the opponent
 */
public class FireSpell extends Spell{

    /**
     * Creates the new Fire spell
     * @param name given name
     * @param level level of spell
     * @param price cost of the purchasing
     * @param description given description
     * @param damageValue damage value for any attribute
     * @param requiredMana required mana to cast it
     */
    public FireSpell(String name, int level, float price, String description, float damageValue, float requiredMana) {
        super(name, level, price, description, damageValue, requiredMana);
    }

    /**
     * Apply fire spell effects on the opponent
     * @see Spell#applySpellEffects(AbstractCreature)
     * @param abstractCreature creature to hit spell on
     */
    @Override
    public void applySpellEffects(AbstractCreature abstractCreature) {
        System.out.println("Hitting Fire Spell on "+abstractCreature.getName());
        abstractCreature.reduceDefence(this.getDamageValue());
    }
}
