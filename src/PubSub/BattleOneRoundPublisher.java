package PubSub;

import creature.Creature;
import map.space.Space;

import java.util.ArrayList;

public class BattleOneRoundPublisher implements GamePublisher{
    private final ArrayList<GameObserver> gameObservers;
    private static GamePublisher gamePublisher;

    private BattleOneRoundPublisher(){
        this.gameObservers = new ArrayList<>();
    }

    public static GamePublisher getBattleOneRoundPublisherInstance() {
        if (gamePublisher == null) {
            gamePublisher = new BattleOneRoundPublisher();
        }
        return gamePublisher;
    }

    @Override
    public void register(GameObserver gameObserver) {
        this.gameObservers.add(gameObserver);
    }

    @Override
    public void notifyObservers(Creature creature, Creature opponent, Space space) {
        for (GameObserver gameObserver : this.gameObservers) {
            gameObserver.notifying(creature, opponent, space);
        }
    }
}
