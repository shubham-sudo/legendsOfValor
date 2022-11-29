package controller;

import creature.*;
import factory.CreaturesFactory;
import game.LegendsOfValor;
import map.BoardMap;
import map.Position;
import map.lane.Lane;
import map.space.Space;
import move.GameMove;
import move.Move;
import player.Player;
import utility.StandardOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Controller for all the legends of valor game moves and interaction with the user
 */
public class LegendsOfValorController implements GameController {
    private final LegendsOfValor game;
    private Player playerTurn;
    private final CreaturesFactory creaturesFactory;
    private int numberOfLanes = BoardMap.DEFAULT_LANES;

    /**
     * Creates an object of the controller
     */
    public LegendsOfValorController(){
        game = LegendsOfValor.getGameInstance();
        creaturesFactory = new CreaturesFactory();
    }

    /**
     * Add Heroes to the player
     * @param player player whose playing
     */
    private void addHeroes(Player player){
        for (int i = 0; i < numberOfLanes; i++){
            int num;
            do {
                System.out.println();
                System.out.println("Enter Number for the type of Hero!");
                System.out.println("[1] Paladins\n[2] Sorcerers\n[3] Warriors");
                num = getIntFromUser();
                if (num < 1 || num > 3){
                    System.out.println("Please enter a valid number");
                    System.out.println("Try again!!!");
                    continue;
                }

                Creature creature;

                switch (num){
                    case 1:
                        displayCreatures(this.creaturesFactory.getPaladins());
                        creature = getCreature(this.creaturesFactory.getPaladins()); // TODO: (shubham) remove this once factory is fixed
                        creature.setId(i+1);
                        player.addCreature(creature);
                        break;
                    case 2:
                        displayCreatures(this.creaturesFactory.getSorcerers());
                        creature = getCreature(this.creaturesFactory.getSorcerers()); // TODO: (shubham) remove this once factory is fixed
                        creature.setId(i+1);
                        player.addCreature(creature);
                        break;
                    case 3:
                        displayCreatures(this.creaturesFactory.getWarriors());
                        creature = getCreature(this.creaturesFactory.getWarriors()); // TODO: (shubham) remove this once factory is fixed
                        creature.setId(i+1);
                        player.addCreature(creature);
                        break;
                    default:
                        break;
                }

            } while(num < 1 || num > 3);
        }
        System.out.println("Heroes are all-set to move on map!");
        getEnter();
    }

    /**
     * helper function to get the Creature value from user
     * @param creatures static list of heroes
     * @return index value chosen by hero
     */
    private Creature getCreature(ArrayList<? extends Creature> creatures) {
        int index = 0;
        do {
            System.out.println();
            index = getIntFromUser();
            if (index-1 < 0 || index > creatures.size()){
                System.out.println("INVALID, Please try again!!!");
            }
        } while (index-1 < 0 || index > creatures.size());
        return creatures.get(index-1);
    }

    /**
     * Get Player and Heroes from the user
     */
    private Player getPlayer(){
        System.out.println();
        System.out.println("Enter Player Name");
        Player player = new Player(getStringFromUser());
        addHeroes(player);
        return player;
    }

    /**
     * Check for map size and confirm from the user
     */
    private void boardSize(){
        System.out.println("Default # of Lanes are ["+ BoardMap.DEFAULT_LANES +"],\nDo you want to change it? [YES/yes/y/NO/no/n]");
        boolean wantToChange = getUserYesOrNo();

        if (wantToChange){
            do {
                numberOfLanes = getIntFromUser();
                if (numberOfLanes < BoardMap.DEFAULT_LANES || numberOfLanes > BoardMap.MAX_LANES) {
                    System.out.println("Please enter a valid # of lanes ["+ BoardMap.DEFAULT_LANES+" - "+ BoardMap.MAX_LANES +"]");
                    System.out.println("Try again!");
                }
            } while (numberOfLanes < BoardMap.DEFAULT_LANES || numberOfLanes > BoardMap.MAX_LANES);
        }
    }

    /**
     * display the map in some format using this function
     */
    private void displayMap(){
        Lane[] lanes = game.getMap().map();
        HashMap<String, String> spaceRepresentations = new HashMap<>();

        System.out.println();
        for (int i = 0; i < game.getMap().getLaneSize(); i++){
            System.out.print(i+1 + "\t");
            for (int j = 0; j < game.getMap().getNumberOfLanes(); j++){

                Lane lane = lanes[j];
                for (int k = 0; k < lane.getWidth(); k++){
                    Space space = lane.getSpace(i, k);  // TODO: (shubham) think if we can have border for every space
                    if (!spaceRepresentations.containsKey(space.getClass().getSimpleName())){
                        spaceRepresentations.put(space.getClass().getSimpleName(), space.bgColor());
                    }
                    System.out.print(space.bgColor() + space.displayValue() + Space.ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.print(" \t");
        System.out.println(String.join("", game.getMap().getLaneLabels()));
        System.out.println("\nEach lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left");
        System.out.println("Board colors represents the respective spaces");
        for (String str : spaceRepresentations.keySet()){
            System.out.print(spaceRepresentations.get(str) + "   " + Space.ANSI_RESET);
            System.out.println("\t\t" + str);
        }

    }

    /**
     * Display the data creatures to get the index from the user and create new Creature
     * @param creatures list of creatures
     */
    private void displayCreatures(ArrayList<? extends Creature> creatures){
        Iterator<? extends Creature> it = creatures.iterator();
        int i = 1;
        boolean flag = true;

        while (it.hasNext()){
            Creature creature = it.next();
            StandardOutput.showCreature(creature, flag, i);
            flag = false;
            i++;
        }

        System.out.println();
    }

    /**
     * Get the game move from the player
     * @param creature creature for the move is
     * @return GameMove object
     */
    private GameMove getGameMove(Creature creature){
        System.out.println();
        System.out.println("Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT, \n[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT");
        System.out.println("Enter move for " + creature.displayValue());
        GameMove move;

        while (true) {
            try {
                move = GameMove.vOf(getCharFromUser());
                break;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
        return move;
    }

    /**
     * Get the teleport position the user is trying to move on
     * @param creature creature for the which the move is
     * @return Position object
     */
    private Position getTeleportPosition(Creature creature){
        Position teleportPosition = new Position();
        System.out.println();
        System.out.println("Enter details to teleport Hero - " + creature.displayValue());
        System.out.println("Enter lane number to be teleported e.g. - 1");
        teleportPosition.laneNumber = getIntFromUser() - 1;
        System.out.println("Enter row [1 - N] number to be teleported in Lane - " + teleportPosition.laneNumber);
        teleportPosition.rowNumber = getIntFromUser() - 1;
        System.out.println("Enter col [1 - N] number to be teleported in Lane - " + teleportPosition.laneNumber);
        teleportPosition.colNumber = getIntFromUser() - 1;
        return teleportPosition;
    }

    /**
     * Computer the move by GameMove and make a Move object to call game.playMove
     * @param gameMove GameMove of the creature given by User
     * @param creature creature whose move is this
     * @return Move object
     */
    private Move getMove(GameMove gameMove, Creature creature){
        Move move = new Move(creature, creature.getHomeLane(), gameMove);
        Position position = creature.getCurrentPosition();
        move.laneNumber = position.laneNumber;
        move.rowNumber = position.rowNumber;
        move.colNumber = position.colNumber;
        switch (gameMove){
            case UP:
                move.rowNumber = position.rowNumber - 1;
                break;
            case DOWN:
                move.rowNumber = position.rowNumber + 1;
                break;
            case LEFT:
                move.colNumber = position.colNumber - 1;
                break;
            case RIGHT:
                move.colNumber = position.colNumber + 1;
                break;
            case RECALL:
                move.laneNumber = creature.getHomeLane();
                move.rowNumber = game.getMap().getLaneSize()-1;
                move.colNumber = 1;
                // TODO (shubham): hard coded for time being, This will always goes to second col of lane
                break;
            case TELEPORT:
                // Ask user for the lane number, row and column
                Position teleportPosition = getTeleportPosition(creature);
                move.laneNumber = teleportPosition.laneNumber;
                move.rowNumber = teleportPosition.rowNumber;
                move.colNumber = teleportPosition.colNumber;
                break;
            case ATTACK:
            case CAST:
            case EQUIP:
            case DROP:
            case POTION:
            case MARKET:
            case INFO:
                break;
        }
        return move;
    }

    /**
     * Human-readable errors to show in console
     * @param move Move object the player played
     */
    private void humanReadableMessage(Move move){
        System.out.println("Invalid Move, Try again!!!");
        switch (move.gameMove) {
            case RECALL:
                System.out.println("The Space is already Occupied, Please move the creature first!");
                break;
            case TELEPORT:
                System.out.println("Teleport is only possible at lane rows already explored!");
                break;
            case LEFT:
            case RIGHT:
                System.out.println("Cannot move on the Inaccessible space!");
                break;
            case UP:
            case DOWN:
                System.out.println("Cannot move UP/DOWN when another creature near by!");
                break;
            case ATTACK:
                if (move.creature != null && !move.creature.isAlive()) {
                    System.out.println("Fainted heroes can't attack or cast");
                } else {
                    System.out.println("Opponent is far away, Save you moves!");
                }
                break;
            case CAST:
                if (move.creature != null && !move.creature.isAlive()) {
                    System.out.println("Fainted heroes can't attack or cast");
                } else {
                    System.out.println("You don't have any castable in your inventory OR");
                    System.out.println("Opponent is far away, Save your products!");
                }
                break;
            case EQUIP:
                System.out.println("You don't have any equipable product in your inventory");
                break;
            case DROP:
                System.out.println("You haven't equipped anything yet!");
            case POTION:
                System.out.println("You don't have any healable product in your inventory");
                break;
            case MARKET:
                System.out.println("You should be on your Nexus to access the market!");
                break;
        }
    }

    /**
     * Check if user want to really exit in between or
     * want to switch map
     */
    private void checkExit(){
        System.out.println();
        System.out.println("Thank you for playing!!!");
        System.out.println("Do you wanna give it another shot ?[Yes/No]\nMay be with different map");
        boolean anotherShot = getUserYesOrNo();
        if (!anotherShot) {
            game.setOver();
        } else {
            game.restartGame();
            game.nextTurn();  // skipping monster turn
        }
        // return anotherShot;
    }

    /**
     * Drive the game
     */
    private void driveGame(){
        boolean exit = false;
        System.out.println("\n######## Game Started ########");
        while (game.notOver()){
            playerTurn = game.nextTurn();
            game.increaseRound();
            exit = false;

            // one round
            for (Creature creature : playerTurn){
                if (creature instanceof Hero){
                    while (true) {
                        GameMove gameMove = getGameMove(creature);
                        if (gameMove == GameMove.EXIT) {
                            checkExit();
                            exit = true;
                            break;
                        }
                        Move move = getMove(gameMove, creature);
                        if (game.isSafeMove(move)){
                            game.playMove(move);
                            StandardOutput.showCreature(creature, true, 1);
                            getEnter();
                            break;
                        }
                        humanReadableMessage(move);
                    }
                } else {
                    System.out.println();
                    System.out.println("Monster " + creature.displayValue() + " playing his move");
                    Move move = game.getAutoMove(creature);
                    game.playMove(move);
                    getEnter();
                }
                displayMap();
                if (!game.notOver() || exit) {
                    break;
                }
            }
            game.removeFaintedMonsters();
        }
        if (!exit){
            System.out.println("\n######## Congratulations!!!, " + playerTurn.getName() + " Won this Game ########");
        }
    }

    /**
     * Shows the overview of the Game
     */
    private void overview(){
        System.out.println();
        System.out.println(
                "\t\tLegends of Valor isa MOBA (multiplayer online battle arena)-like game. \n" +
                "\t\tThe player will control a team of 3 heroes who will attempt to fight their way \n" +
                "\t\tthrough to the monsters’ Nexus.The heroes win if any of them reach the monsters’ Nexus. \n" +
                "\t\tThe heroes lose if any monster reaches the heroes’ Nexus.\n"
        );
    }

    /**
     * run the game by taking necessary steps
     */
    @Override
    public void run() {
        overview();
        boardSize();
        game.initializeBoard(numberOfLanes, Lane.DEFAULT_LENGTH);
        try {
            game.addPlayers(getPlayer());
            game.spawnMonsters();
        } catch (IllegalAccessException | IllegalArgumentException iae) {
            iae.printStackTrace();
        }
        displayMap();
        driveGame();
    }
}
