package PubSub;

import creature.Creature;
import game.Game;
import game.LegendsOfValor;
import map.space.Space;

public class GameWinObserver implements GameObserver{

    private void updateGameWinFlag(Creature creature, Space space){
        Game game = LegendsOfValor.getGameInstance();
        game.setOver();
    }

    @Override
    public void notifying(Creature creature, Space space) {
        updateGameWinFlag(creature, space);
    }
}
