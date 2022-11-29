package product;

import creature.*;


/**
 * An interface to spell effects for the different type of spells
 */
public interface SpellEffects {

    /**
     * Compute the total damage for a spell
     * @param baseDamage base damage for spell
     * @param dexterity dexterity of creature
     * @return double
     */
    default double getSpellDamage(double baseDamage, double dexterity){
        return baseDamage + (dexterity/10000) * baseDamage;
    }

    /**
     * Apply spell effects on creature
     * @param creature creature who is hitting
     * @param opponent creature to apply damage
     */
    void applySpellEffects(Creature creature, Creature opponent);
}
