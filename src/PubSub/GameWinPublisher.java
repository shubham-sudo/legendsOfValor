package PubSub;

import creature.Creature;
import map.space.Space;

import java.util.ArrayList;


/**
 * Publish events for change in move of any creature for nexus
 */
public class GameWinPublisher implements GamePublisher{
    private final ArrayList<GameObserver> gameObservers;
    private static GamePublisher gamePublisher;

    /**
     * Constructor
     */
    private GameWinPublisher() {
        this.gameObservers = new ArrayList<>();
    }

    /**
     * Get instance of the GamePublisher
     * @return GameWinPublisher object
     */
    public static GamePublisher getWinPublisherInstance(){
        if (gamePublisher == null){
            gamePublisher = new GameWinPublisher();
        }
        return gamePublisher;
    }

    /**
     * Register a new game observer
     * @param gameObserver observer object
     */
    @Override
    public void register(GameObserver gameObserver) {
        this.gameObservers.add(gameObserver);
    }

    /**
     * notify observers
     * @param creature hero object
     * @param opponent opponent for this game or battle
     * @param space space object
     */
    @Override
    public void notifyObservers(Creature creature, Creature opponent, Space space) {
        for (GameObserver gameObserver : this.gameObservers){
            gameObserver.notifying(creature, opponent, space);
        }
    }
}
