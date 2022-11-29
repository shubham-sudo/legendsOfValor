package PubSub;

import creature.Creature;
import map.space.Space;

import java.util.ArrayList;


/**
 * Publisher for one round of the battle
 */
public class BattleOneRoundPublisher implements GamePublisher{
    private final ArrayList<GameObserver> gameObservers;
    private static GamePublisher gamePublisher;

    /**
     * Constructor for battle one round publisher
     */
    private BattleOneRoundPublisher(){
        this.gameObservers = new ArrayList<>();
    }

    /**
     * get instance of the battle one round publisher
     * @return BattleOneRoundPublisher object
     */
    public static GamePublisher getBattleOneRoundPublisherInstance() {
        if (gamePublisher == null) {
            gamePublisher = new BattleOneRoundPublisher();
        }
        return gamePublisher;
    }

    /**
     * add new observer to the game
     * @param gameObserver observer object
     */
    @Override
    public void register(GameObserver gameObserver) {
        this.gameObservers.add(gameObserver);
    }

    /**
     * Notify observers
     * @param creature hero object
     * @param opponent opponent for this game or battle
     * @param space space object
     */
    @Override
    public void notifyObservers(Creature creature, Creature opponent, Space space) {
        for (GameObserver gameObserver : this.gameObservers) {
            gameObserver.notifying(creature, opponent, space);
        }
    }
}
