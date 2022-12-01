package product;

import creature.*;

/**
 * An interface for all consumable type of products
 */
public interface Consumable {

    /**
     * Check if the product is safe to consume
     * @param hero creature who want to consume
     * @return true if possible, false otherwise
     */
    boolean isSafeToConsume(Hero hero);

    /**
     * Consume a product after use
     * @param hero creature who used this product
     * @return boolean
     */
    boolean consume(Hero hero);
}
