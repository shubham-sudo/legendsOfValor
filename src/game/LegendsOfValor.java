package game;

import creature.Creature;
import factory.*;
import factory.MapFactory;
import map.BoardMap;
import move.GameMove;
import move.Move;
import player.Player;

public class LegendsOfValor extends Game{
    private static final MapFactory mapFactory = new MapFactory();
    private static final CreaturesFactory creaturesFactory = new CreaturesFactory();
    private BoardMap map;
    private int maxLevel;
    private boolean over;

    public LegendsOfValor(){
        over = false;
    }

    public BoardMap getMap() {
        if (map == null){
            initializeBoard();
        }
        return map;
    }

    public boolean notOver(){
        return !over;
    }

    public void initializeBoard(){
        this.map = new BoardMap();
    }

    public void initializeBoard(int numberOfLanes, int laneSize){
        this.map = new BoardMap(numberOfLanes, laneSize);
    }

    public void reinitializeBoard(){
        int numberOfLanes = this.map.getNumberOfLanes();
        int laneSize = this.map.getLaneSize();
        this.map = new BoardMap(numberOfLanes, laneSize);
    }

    public void addPlayers(Player player) throws IllegalArgumentException, IllegalAccessException {
        if (player.size() != this.map.getPlayableLanes()){
            throw new IllegalArgumentException("Insufficient number of Creatures");
        }
        this.maxLevel = player.getMaxLevel();
        this.map.sendHeroesOnMap(player.getCreatures());
        players.add(player);
    }

    public void addMonsters() throws IllegalAccessException {
        Player monsterPlayer = new Player("Computer Player");

        for (int i = 0; i < this.map.getPlayableLanes(); i++){
            Creature creature = creaturesFactory.createMonsters(1, maxLevel).get(0);
            monsterPlayer.addCreature(creature);
        }

        this.map.sendMonstersOnMap(monsterPlayer.getCreatures());
        players.add(monsterPlayer);
    }

    public boolean isSafeMove(Move move){
        if (move.gameMove == GameMove.ATTACK
            || move.gameMove == GameMove.INFO || move.gameMove == GameMove.MARKET) {
            return true;
        } else if (move.gameMove == GameMove.CAST || move.gameMove == GameMove.EQUIP
            || move.gameMove == GameMove.POTION) {
            // TODO (shubham) check if creature inventory has castable, equipable and potion objects
        } else if (map.isSafeToOccupy(move)) {
            return true;  // TODO (shubham): check if the move is safe
        }
        return false;
    }

    public void playMove(Move move){
        if (isSafeMove(move)){
            try {
                map.occupySpace(move.laneNumber, move.rowNumber, move.colNumber, move.creature);
            } catch (IllegalArgumentException | IllegalAccessException iae) {
                iae.printStackTrace();
            }
        }
    }
}
