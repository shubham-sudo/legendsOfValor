package game;

import player.Player;

import java.util.*;

/**
 * Abstract Game class for all the games
 */
public abstract class Game {
    protected final Queue<Player> players;
    protected boolean over;

    public abstract void setOver();

    public Game(){
        players = new ArrayDeque<>();
    }

    public Player nextTurn(){
        Player player = null;
        if (!players.isEmpty()){
            player = players.remove();
            players.add(player);
        }
        return player;
    }
}
