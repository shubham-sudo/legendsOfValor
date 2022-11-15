package product;

import creature.AbstractCreature;

/**
 * An interface for all wearable products
 */
public interface Wearable {
    /**
     * check if the product can be used
     * @param abstractCreature creature trying to use it
     * @return true if usable, false otherwise
     */
    boolean isWearable(AbstractCreature abstractCreature);

    /**
     * wear the product post validation
     * @param abstractCreature creature who wear it
     * @return true wore it, false otherwise
     */
    boolean wear(AbstractCreature abstractCreature);

    /**
     * Remove the product if needed
     * @param abstractCreature creature who want to remove
     * @return true if possible, false otherwise
     */
    boolean remove(AbstractCreature abstractCreature);
}
