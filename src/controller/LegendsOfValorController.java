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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class LegendsOfValorController implements GameController {
    private final LegendsOfValor game;
    private final CreaturesFactory creaturesFactory;
    private int numberOfLanes = BoardMap.DEFAULT_LANES;

    public LegendsOfValorController(){
        game = new LegendsOfValor();
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
     * Get Player and Heroes
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

        for (int i = 0; i < game.getMap().getLaneSize(); i++){
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
        System.out.println(String.join("", game.getMap().getLaneLabels()));
        System.out.println("\nBoard colors represents the respective spaces");
        for (String str : spaceRepresentations.keySet()){
            System.out.print(spaceRepresentations.get(str) + "   " + Space.ANSI_RESET);
            System.out.println("\t\t" + str);
        }

    }

    private void displayCreatures(ArrayList<? extends Creature> creatures){
        Iterator<? extends Creature> it = creatures.iterator();
        int i = 1;

        while (it.hasNext()){
            Creature creature = it.next();
            System.out.println(creature);
//            if (i == 1){
//                System.out.println("    "+ Utility.paddedString(Hero.header));
//            }
//            System.out.println("[" + i + "] " + Utility.paddedString(creature.seekInformation()));
            i++;
        }

        System.out.println();
    }

    private GameMove getGameMove(Creature creature){
        System.out.println();
        System.out.println("Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT");
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

    private Position getTeleportPosition(Creature creature){
        Position teleportPosition = new Position();
        System.out.println();
        System.out.println("Enter details to teleport Hero - " + creature.displayValue());
        System.out.println("Enter lane number to be teleported e.g. - 1");
        teleportPosition.laneNumber = getIntFromUser();
        System.out.println("Enter row number to be teleported in Lane - " + teleportPosition.laneNumber);
        teleportPosition.rowNumber = getIntFromUser();
        System.out.println("Enter col number to be teleported in Lane - " + teleportPosition.laneNumber);
        teleportPosition.colNumber = getIntFromUser();
        return teleportPosition;
    }

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
                move.rowNumber = 0;
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
            case POTION:
            case MARKET:
            case INFO:
                move.laneNumber = position.laneNumber;
                move.rowNumber = position.rowNumber;
                move.colNumber = position.colNumber;
        }
        return move;
    }

    private void driveGame(){
        // TODO: (shubham) while next turn ...
        //  iterate over creatures
        //  after every move check if the game can be played more or someone wins.
        System.out.println("\n######## Game Started ########");
        while (game.notOver()){
            Player player = game.nextTurn();

            // one round
            for (Creature creature : player){
                if (creature instanceof Hero){
                    while (true) {
                        GameMove gameMove = getGameMove(creature);
                        Move move = getMove(gameMove, creature);
                        if (game.isSafeMove(move)){
                            game.playMove(move);
                            break;
                        }
                        System.out.println("Invalid Move, Try again!!!");
                    }
                } else {
                    System.out.println("Monster " + creature.displayValue() + " playing his move");
                    // TODO: (shubham) if monster get automatic move should be biased to attack
                    //      if hero is near to the next move
                }
                displayMap();
            }

            // TODO: (shubham) check if any hero or monster died and revive them and place at home
        }
    }

    @Override
    public void run() {
        boardSize();
        game.initializeBoard(numberOfLanes, Lane.DEFAULT_LENGTH);
        try {
            game.addPlayers(getPlayer());
            game.addMonsters();
        } catch (IllegalAccessException | IllegalArgumentException iae) {
            iae.printStackTrace();
        }
        displayMap();
        driveGame();
    }
}
