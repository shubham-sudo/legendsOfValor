package PubSub;

import creature.Creature;
import game.Game;
import game.LegendsOfValor;
import map.space.Space;


/**
 * observe the game state if anyone is winning
 */
public class GameWinObserver implements GameObserver{

    /**
     * update the game state if anyone is winning
     * @param creature creature who played this move
     * @param space space her is currently on
     */
    private void updateGameWinFlag(Creature creature, Space space){
        Game game = LegendsOfValor.getGameInstance();
        game.setOver();
    }

    /**
     * Notify the observers
     * @param creature hero object itself
     * @param opponent opponent for this game or battle
     * @param space space object
     */
    @Override
    public void notifying(Creature creature, Creature opponent, Space space) {
        updateGameWinFlag(creature, space);
    }
}
