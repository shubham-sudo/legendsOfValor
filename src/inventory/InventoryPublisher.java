package inventory;

import PubSub.GameObserver;
import PubSub.GamePublisher;
import creature.Hero;

import java.util.ArrayList;

/**
 * A publisher for sending signal to the observers of the inventory to update
 */
public class InventoryPublisher implements GamePublisher {
    private final ArrayList<GameObserver> gameObservers;
    private static InventoryPublisher inventoryPublisher;

    /**
     * Create a new inventory publisher object
     */
    private InventoryPublisher(){
        this.gameObservers = new ArrayList<>();
    }

    /**
     * Only one instance is required throughout the game
     * @return InventoryPublisher object
     */
    public static InventoryPublisher getInstance(){
        if (inventoryPublisher == null){
            inventoryPublisher = new InventoryPublisher();
        }
        return inventoryPublisher;
    }

    /**
     * Register a new observer to this publisher
     * @param gameObserver object of GameObserver
     */
    @Override
    public void register(GameObserver gameObserver) {
        this.gameObservers.add(gameObserver);
    }

    /**
     * Notify all the observer of the inventory
     * @param inventory inventory of hero
     * @param hero hero object
     */
    @Override
    public void notifyObservers(Inventory inventory, Hero hero) {
        for (GameObserver gameObserver : gameObservers){
            gameObserver.notifying(inventory, hero);
        }
    }
}
