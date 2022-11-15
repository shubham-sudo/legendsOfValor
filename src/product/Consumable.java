package product;

import creature.AbstractCreature;

/**
 * An interface for all consumable type of products
 */
public interface Consumable {
    /**
     * Consume a product after use
     * @param abstractCreature creature who used this product
     * @return boolean
     */
    boolean consume(AbstractCreature abstractCreature);

    /**
     * Check if the product is already consumed
     * @param abstractCreature creature who is trying to consume it
     * @return boolean
     */
    boolean isConsumable(AbstractCreature abstractCreature);
}
