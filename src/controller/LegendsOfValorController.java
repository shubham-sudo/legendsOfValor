package controller;

import creature.Creature;
import factory.CreaturesFactory;
import game.LegendsOfValor;
import map.BoardMap;
import map.lane.Lane;
import map.space.Space;
import player.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class LegendsOfValorController implements GameController {
    private final LegendsOfValor game;
    private final CreaturesFactory creaturesFactory;
    private int numberOfLanes = BoardMap.DEFAULT_LANES;

    public LegendsOfValorController(){
        game = new LegendsOfValor();
        creaturesFactory = new CreaturesFactory();
    }

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

                switch (num){
                    case 1:
                        displayCreatures(this.creaturesFactory.getPaladins());
                        player.addCreature(getCreature(this.creaturesFactory.getPaladins()));
                        break;
                    case 2:
                        displayCreatures(this.creaturesFactory.getSorcerers());
                        player.addCreature(getCreature(this.creaturesFactory.getSorcerers()));
                        break;
                    case 3:
                        displayCreatures(this.creaturesFactory.getWarriors());
                        player.addCreature(getCreature(this.creaturesFactory.getSorcerers()));
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

        for (int i = 0; i < game.getMap().getLaneSize(); i++){
            for (int j = 0; j < game.getMap().getNumberOfLanes(); j++){
                Lane lane = lanes[j];
                for (int k = 0; k < lane.getWidth(); k++){
                    Space space = lane.getSpace(i, k);  // TODO: (shubham) think if we can have border for every space
                    System.out.print(space.bgColor() + space.displayValue() + Space.ANSI_RESET);
                }
            }
            System.out.println();
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
    }
}
