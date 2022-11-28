package PubSub;

import creature.Creature;
import map.space.Space;

import java.util.ArrayList;

public class GameWinPublisher implements GamePublisher{
    private final ArrayList<GameObserver> gameObservers;
    private static GamePublisher gamePublisher;

    private GameWinPublisher() {
        this.gameObservers = new ArrayList<>();
    }

    public static GamePublisher getWinPublisherInstance(){
        if (gamePublisher == null){
            gamePublisher = new GameWinPublisher();
        }
        return gamePublisher;
    }

    @Override
    public void register(GameObserver gameObserver) {
        this.gameObservers.add(gameObserver);
    }

    @Override
    public void notifyObservers(Creature creature, Space space) {
        for (GameObserver gameObserver : this.gameObservers){
            gameObserver.notifying(creature, space);
        }
    }
}
