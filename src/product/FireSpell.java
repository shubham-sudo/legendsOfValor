package product;

import creature.*;


/**
 * Fire spell is a Spell which reduce the defence of the opponent
 */
public class FireSpell extends Spell{

    /**
     * Creates the new Fire spell
     * @param name         given name
     * @param level        level of spell
     * @param price        cost of the purchasing
     * @param description  given description
     * @param damageValue  damage value for any attribute
     * @param requiredMana required mana to cast it
     */
    public FireSpell(String name, int level, float price, String description, float damageValue, float requiredMana) {
        super(name, level, price, description, damageValue, requiredMana);
    }

    /**
     * Apply fire spell effects on the opponent
     * @see Spell#applySpellEffects(Creature)
     * @param creature creature to hit spell on
     */
    @Override
    public void applySpellEffects(Creature creature) {
        creature.decreaseDefence(this.getDamageValue());
    }
}
