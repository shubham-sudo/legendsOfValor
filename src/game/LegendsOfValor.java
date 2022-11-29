package game;

import PubSub.GameWinObserver;
import PubSub.GameWinPublisher;
import controller.BattleController;
import controller.MarketController;
import creature.Creature;
import creature.Hero;
import factory.*;
import factory.MapFactory;
import inventory.Inventory;
import map.BoardMap;
import map.lane.Lane;
import move.GameMove;
import move.Move;
import player.Player;
import utility.StandardOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LegendsOfValor extends Game{
    private static LegendsOfValor legendsOfValor;
    private static final MapFactory mapFactory = new MapFactory();  // TODO: (Shubham) have to use map factory for maps
    private static final CreaturesFactory creaturesFactory = new CreaturesFactory();
    private static final MarketController marketController = new MarketController();
    private static final BattleController battleController = BattleController.getBattleControllerInstance();
    private static final String MONSTER_TEAM_NAME = "Pokemon's Team";
    private BoardMap map;
    private int maxLevel;
    private double rounds;

    @Override
    public void setOver() {
        over = true;
    }

    private LegendsOfValor(){
        over = false;
        GameWinPublisher.getWinPublisherInstance().register(new GameWinObserver());
    }

    public static LegendsOfValor getGameInstance() {
        if (legendsOfValor == null) {
            legendsOfValor = new LegendsOfValor();
        }
        return legendsOfValor;
    }

    public BoardMap getMap() {
        if (map == null){
            initializeBoard();
        }
        return map;
    }

    public double rounds() {
        return rounds;
    }

    public void increaseRound() {
        rounds += 0.5;
    }

    public void resetRound(){
        rounds = 0;
    }

    public boolean notOver(){
        return !over;
    }

    public void initializeBoard(){
        Lane.ID = 0;
        this.map = new BoardMap();
    }

    public void initializeBoard(int numberOfLanes, int laneSize){
        Lane.ID = 0;
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

    public void spawnMonsters() throws IllegalAccessException {
        Player newPlayer = new Player(MONSTER_TEAM_NAME);
        for (int i = 0; i < getMap().getPlayableLanes(); i++){
            Creature creature = creaturesFactory.createMonsters(1, maxLevel).get(0);
            newPlayer.addCreature(creature);
        }
        getMap().sendMonstersOnMap(newPlayer.getCreatures());
        players.add(newPlayer);
    }

    public void addMonsters() throws IllegalAccessException {
        Player monsterPlayer = new Player(MONSTER_TEAM_NAME);

        for (int i = 0; i < this.map.getPlayableLanes(); i++){
            Creature creature = creaturesFactory.createMonsters(1, maxLevel).get(0);
            monsterPlayer.addCreature(creature);
        }

        this.map.sendMonstersOnMap(monsterPlayer.getCreatures());
        players.add(monsterPlayer);
    }

    public Move getAutoMove(Creature creature){
        // Running this for monster so, it is biased to move only in south direction
        // TODO: (shubham) monster can move in all directions

        Move downMove = new Move(creature, creature.getHomeLane(), GameMove.DOWN);
        downMove.rowNumber = creature.getCurrentPosition().rowNumber + 1;
        downMove.colNumber = creature.getCurrentPosition().colNumber;
        boolean isHeroNearBy = map.getLane(creature.getHomeLane()).isOpponentNearBy(creature);

        if (map.isSafeToOccupy(downMove) && !isHeroNearBy){
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
        } else if (move.gameMove == GameMove.INFO) {
            return true;
        } else if (move.gameMove == GameMove.ATTACK) {
            return map.getLane(move.laneNumber).getOpponentNearBy(move.creature) != null;
        } else if (move.gameMove == GameMove.EQUIP || move.gameMove == GameMove.POTION
                || move.gameMove == GameMove.CAST  || move.gameMove == GameMove.DROP) {
            Inventory inventory = ((Hero) move.creature).inventory();
            switch (move.gameMove) {
                case CAST:
                    return inventory.hasCastable() && map.getLane(move.laneNumber).isOpponentNearBy(move.creature);
                case POTION:
                    return inventory.hasHealer();
                case EQUIP:
                    return inventory.hasEquipable();
                case DROP:
                    return ((Hero) move.creature).getArmor() != null || ((Hero) move.creature).getInHandWeapons().size() > 0;
            }
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

    public void restartGame(){
        initializeBoard(BoardMap.DEFAULT_LANES, Lane.DEFAULT_LENGTH);
        Player player = nextTurn();
        try {
            if (player.getName().equals(MONSTER_TEAM_NAME)) {
                map.sendMonstersOnMap(player.getCreatures());
                map.sendHeroesOnMap(nextTurn().getCreatures());
            } else {
                map.sendHeroesOnMap(player.getCreatures());
                map.sendMonstersOnMap(nextTurn().getCreatures());
            }
        } catch (IllegalAccessException | IllegalArgumentException ie) {
            // ie.printStackTrace();
        }
    }

    public void playMove(Move move){
        Creature opponent = map.getLane(move.laneNumber).getOpponentNearBy(move.creature);
        if (move.gameMove == GameMove.INFO) {
            try {
                Stream<Player> allMonsterPlayer = players.stream().filter(player1 -> player1.getName().equals(MONSTER_TEAM_NAME));
                boolean flag = true;
                for (Player monsterPlayer : allMonsterPlayer.collect(Collectors.toList())){
                    for (int i = 0; i < monsterPlayer.getCreatures().size(); i++) {
                        StandardOutput.showCreature(monsterPlayer.getCreatures().get(i), flag, i+1);
                        flag = false;
                    }
                }
            } catch (Exception e) {
                // Passing since no monster found
            }
        } else if (move.gameMove == GameMove.MARKET && map.isMarket(move)){
            openMarket(move.creature);
        } else if (move.gameMove == GameMove.ATTACK && opponent != null) {
            battleController.attack(move.creature, opponent);
        } else if (move.gameMove == GameMove.CAST && opponent != null) {
            battleController.cast(move.creature, opponent);
        } else if (move.gameMove == GameMove.EQUIP) {
            battleController.doEquip(move.creature);
        } else if (move.gameMove == GameMove.POTION) {
            battleController.heal(move.creature);
        } else if (move.gameMove == GameMove.DROP) {
            battleController.dropEquipable(move.creature);
        } else if (isSafeMove(move)){
            try {
                map.occupySpace(move.laneNumber, move.rowNumber, move.colNumber, move.creature);
            } catch (IllegalArgumentException | IllegalAccessException iae) {
                iae.printStackTrace();
            }
        }
        maxLevel = Math.max(maxLevel, move.creature.getLevel());
    }
}
