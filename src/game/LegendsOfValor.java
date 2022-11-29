package game;

import PubSub.GameWinObserver;
import PubSub.GameWinPublisher;
import controller.BattleController;
import controller.MarketController;
import creature.Creature;
import creature.Hero;
import creature.Monster;
import factory.*;
import factory.MapFactory;
import inventory.Inventory;
import map.BoardMap;
import map.lane.Lane;
import move.GameMove;
import move.Move;
import player.Player;
import utility.StandardOutput;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Legends of valor game is played by this class
 * It manages all the operation which can happen in this game
 * It computes the safest moves and also generate move for computer player
 */
public class LegendsOfValor extends Game{
    private static LegendsOfValor legendsOfValor;
    private static final MapFactory mapFactory = new MapFactory();  // TODO: (Shubham) have to use map factory for maps
    private static final CreaturesFactory creaturesFactory = new CreaturesFactory();
    private static final MarketController marketController = new MarketController();
    private static final BattleController battleController = BattleController.getBattleControllerInstance();
    private static final String MONSTER_TEAM_NAME = "Pokemon's Team";
    private final HashSet<Creature> faintedCreatures;
    private BoardMap map;
    private int maxLevel;
    private double rounds;

    /**
     * Set game to over
     */
    @Override
    public void setOver() {
        over = true;
    }

    /**
     * Constructor for Legends of Valor
     */
    private LegendsOfValor(){
        over = false;
        faintedCreatures = new HashSet<>();
        GameWinPublisher.getWinPublisherInstance().register(new GameWinObserver());
    }

    /**
     * Get game instance
     * @return LegendsOfValor
     */
    public static LegendsOfValor getGameInstance() {
        if (legendsOfValor == null) {
            legendsOfValor = new LegendsOfValor();
        }
        return legendsOfValor;
    }

    /**
     * Get map of the game
     * @return BoardMap
     */
    public BoardMap getMap() {
        if (map == null){
            initializeBoard();
        }
        return map;
    }

    /**
     * Getter for number of rounds
     * @return double
     */
    public double rounds() {
        return rounds;
    }

    /**
     * Increase the round
     */
    public void increaseRound() {
        rounds += 0.5;
    }

    /**
     * Reset the round to 0
     */
    public void resetRound(){
        rounds = 0;
    }

    /**
     * check if the game is not over
     * @return boolean
     */
    public boolean notOver(){
        return !over;
    }

    /**
     * Initialize the new board
     */
    public void initializeBoard(){
        Lane.ID = 0;
        this.map = new BoardMap();
    }

    /**
     * Initialize the board with different configurations
     * @param numberOfLanes number of lanes
     * @param laneSize one lane size
     */
    public void initializeBoard(int numberOfLanes, int laneSize){
        Lane.ID = 0;
        this.map = new BoardMap(numberOfLanes, laneSize);
    }

    /**
     * Reinitialize the board if required
     */
    public void reinitializeBoard(){
        int numberOfLanes = this.map.getNumberOfLanes();
        int laneSize = this.map.getLaneSize();
        this.map = new BoardMap(numberOfLanes, laneSize);
    }

    /**
     * Add player to the game
     * @param player player object
     * @throws IllegalArgumentException  Should be equal to the number of lanes
     * @throws IllegalAccessException should not access the same space on board
     */
    public void addPlayers(Player player) throws IllegalArgumentException, IllegalAccessException {
        if (player.size() != this.map.getPlayableLanes()){
            throw new IllegalArgumentException("Insufficient number of Creatures");
        }
        this.maxLevel = player.getMaxLevel();
        this.map.sendHeroesOnMap(player.getCreatures());
        players.add(player);
    }

    /**
     * Spawn monster to the game and create a new player for them
     * @throws IllegalAccessException should not access the same space on board
     */
    public void spawnMonsters() throws IllegalAccessException {
        Player newPlayer = new Player(MONSTER_TEAM_NAME);

        for (int i = 0; i < this.map.getPlayableLanes(); i++){
            Creature creature = creaturesFactory.createMonsters(1, maxLevel).get(0);
            newPlayer.addCreature(creature);
        }
        this.map.sendMonstersOnMap(newPlayer.getCreatures());
        players.add(newPlayer);
    }

    /**
     * Get auto move for the Monster creature
     * @param creature creature
     * @return Move object
     */
    public Move getAutoMove(Creature creature){
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

    /**
     * Check if the move is safe on board
     * @param move Move object
     * @return boolean
     */
    public boolean isSafeMove(Move move){
        if (move.gameMove == GameMove.MARKET && map.isMarket(move)) {
            return true;
        } else if (move.gameMove == GameMove.INFO) {
            return true;
        } else if (move.gameMove == GameMove.ATTACK) {
            return map.getLane(move.laneNumber).getOpponentNearBy(move.creature) != null && move.creature.isAlive();
        } else if (move.gameMove == GameMove.EQUIP || move.gameMove == GameMove.POTION
                || move.gameMove == GameMove.CAST  || move.gameMove == GameMove.DROP) {
            Inventory inventory = ((Hero) move.creature).inventory();
            switch (move.gameMove) {
                case CAST:
                    return inventory.hasCastable() && map.getLane(move.laneNumber).isOpponentNearBy(move.creature)
                            && move.creature.isAlive() && battleController.canCast(move.creature);
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

    /**
     * Open the market for the creature
     * @param creature creature who wants to enter in market
     */
    private void openMarket(Creature creature) {
        marketController.setNewCustomer((Hero) creature);
        try {
            marketController.run();
        } catch (IllegalAccessException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Restart game if required
     */
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

    /**
     * Remove fainted monsters from the board & players list
     */
    public void removeFaintedMonsters() {
        Stream<Player> allMonsterPlayer = players.stream().filter(player1 -> player1.getName().equals(MONSTER_TEAM_NAME));
        List<Player> allMonsterPlayers = allMonsterPlayer.collect(Collectors.toList());

        for (Creature fainted : faintedCreatures) {
            for (Player monsters : allMonsterPlayers) {
                monsters.removeCreature(fainted);
            }
        }
    }

    /**
     * Play the move on the board
     * @param move Move object
     */
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
        if (opponent instanceof Monster && !opponent.isAlive()) {
            faintedCreatures.add(opponent);
        }
        maxLevel = Math.max(maxLevel, move.creature.getLevel());
    }
}
