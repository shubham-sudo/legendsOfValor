package product;

import creature.*;


/**
 * An interface to spell effects for the different type of spells
 */
public interface SpellEffects {

    /**
     * Apply spell effects on creature
     * @param creature creature to apply spell effects
     */
    void applySpellEffects(Creature creature);
}
