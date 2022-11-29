package game;

import player.Player;

import java.util.*;

/**
 * Abstract Game class for all the games
 */
public abstract class Game {
    protected final Queue<Player> players;
    protected boolean over;

    /**
     * Set game to over, once someone wins
     */
    public abstract void setOver();

    /**
     * Constructor
     */
    public Game(){
        players = new ArrayDeque<>();
    }

    /**
     * Compute the next turn and return player
     * @return Player
     */
    public Player nextTurn(){
        Player player = null;
        if (!players.isEmpty()){
            player = players.remove();
            players.add(player);
        }
        return player;
    }
}
