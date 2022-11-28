package game;

import controller.MarketController;
import creature.Creature;
import creature.Hero;
import factory.*;
import factory.MapFactory;
import map.BoardMap;
import move.GameMove;
import move.Move;
import player.Player;
import utility.Utility;

public class LegendsOfValor extends Game{
    private static final MapFactory mapFactory = new MapFactory();
    private static final CreaturesFactory creaturesFactory = new CreaturesFactory();
    private static final MarketController marketController = new MarketController();
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

    public Move getAutoMove(Creature creature){
        // Running this for monster so, it is biased to move only in south direction
        // If monster is near to hero and also have valid down move than
        // there are 10 % chances monster moves downward and 90 % chances to attack

        Move downMove = new Move(creature, creature.getHomeLane(), GameMove.DOWN);
        downMove.rowNumber = creature.getCurrentPosition().rowNumber + 1;
        downMove.colNumber = creature.getCurrentPosition().colNumber;

        boolean isHeroNearBy = map.getLane(creature.getHomeLane()).isOpponentNearBy(creature);

        if (isHeroNearBy && map.isSafeToOccupy(downMove)){
            float possibility = Utility.rollDice();
            if (possibility > 10) {
                Move attackMove = new Move(creature, creature.getHomeLane(), GameMove.ATTACK);
                attackMove.rowNumber = creature.getCurrentPosition().rowNumber;
                attackMove.colNumber = creature.getCurrentPosition().colNumber;
                return attackMove;
            } else {
                return downMove;
            }
        } else if (map.isSafeToOccupy(downMove)){
            return downMove;
        } else {
            Move attackMove = new Move(creature, creature.getHomeLane(), GameMove.ATTACK);
            attackMove.rowNumber = creature.getCurrentPosition().rowNumber;
            attackMove.colNumber = creature.getCurrentPosition().colNumber;
            return attackMove;
        }
    }

    public boolean isSafeMove(Move move){
        if (move.gameMove == GameMove.MARKET && map.isMarket(move)) {
            return true;
        } else if (move.gameMove == GameMove.ATTACK
            || move.gameMove == GameMove.INFO) {
            return true;
        } else if (move.gameMove == GameMove.CAST || move.gameMove == GameMove.EQUIP
            || move.gameMove == GameMove.POTION) {
            // TODO (shubham) check if creature inventory has castable, equipable and potion objects
        } else if (map.isSafeToOccupy(move)) {
            return true;
        }
        return false;
    }

    private void openMarket(Creature creature) {
        marketController.setNewCustomer((Hero) creature);
        try {
            marketController.run();
        } catch (IllegalAccessException ie) {
            ie.printStackTrace();
        }
    }

    public void playMove(Move move){
        if (move.gameMove == GameMove.MARKET && map.isMarket(move)){
            openMarket(move.creature);
        }
        if (isSafeMove(move)){
            try {
                map.occupySpace(move.laneNumber, move.rowNumber, move.colNumber, move.creature);
            } catch (IllegalArgumentException | IllegalAccessException iae) {
                iae.printStackTrace();
            }
        }
    }
}
