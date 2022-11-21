//package controller;
//
//import creature.AbstractCreature;
//import creature.Hero;
//import map.*;
//import factory.*;
//import game.LegendsGame;
//import utility.Utility;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//
///**
// * LegendsGameController implements the GameController and control the whole game of Legends of Valor
// */
//public class LegendsGameController implements GameController{
//    private int defaultMapSize = Map.MIN_SIZE;
//    private final LegendsGame legendsGame;
//    private final MapFactory mapFactory;
//    private final CreaturesFactory creaturesFactory;
//
//    /**
//     * Creates a new object of Legends game controller
//     */
//    public LegendsGameController(){
//        this.mapFactory = new MapFactory();
//        this.creaturesFactory = new CreaturesFactory();
//        this.legendsGame = new LegendsGame();
//    }
//
//    /**
//     * Check for map size and confirm from the user
//     */
//    private void mapSize(){
//        System.out.println("Default Board size is ["+ Map.MIN_SIZE +"],\nDo you want to change it? [YES/yes/y/NO/no/n]");
//        boolean wantToChange = getUserYesOrNo();
//
//        if (wantToChange){
//            do {
//                defaultMapSize = getIntFromUser();
//                if (defaultMapSize < Map.MIN_SIZE || defaultMapSize > Map.MAX_SIZE) {
//                    System.out.println("Please enter a valid Map size ["+ Map.MIN_SIZE+" - "+ Map.MAX_SIZE+"]");
//                    System.out.println("Try again!");
//                }
//            } while (defaultMapSize < Map.MIN_SIZE || defaultMapSize > Map.MAX_SIZE);
//        }
//    }
//
//    /**
//     * check for the party size and type of heros user want to add to play
//     */
//    private void partySize(){
//        System.out.println("Default party size is ["+ LegendsGame.PARTY_MIN_SIZE +"],\nDo you want to change it? [YES/yes/y/NO/no/n]");
//        boolean wantToChange = getUserYesOrNo();
//        int newPartySize = LegendsGame.PARTY_MIN_SIZE;
//
//        if (wantToChange){
//            do {
//                newPartySize = getIntFromUser();
//                if (newPartySize < LegendsGame.PARTY_MIN_SIZE || newPartySize > LegendsGame.PARTY_MAX_SIZE){
//                    System.out.println("Please enter a valid Party size ["+ LegendsGame.PARTY_MIN_SIZE+" - "+ LegendsGame.PARTY_MAX_SIZE+"]");
//                    System.out.println("Try again!");
//                }
//            } while (newPartySize < LegendsGame.PARTY_MIN_SIZE || newPartySize > LegendsGame.PARTY_MAX_SIZE);
//        }
//
//        ArrayList<Hero> party = new ArrayList<>();
//
//        for (int i = 0; i < newPartySize; i++){
//            int num = 0;
//            do {
//                System.out.println();
//                System.out.println("Enter Number for the type of Hero!");
//                System.out.println("[1] Paladins\n[2] Sorcerers\n[3] Warriors");
//                num = getIntFromUser();
//                if (num < 1 || num > 3){
//                    System.out.println("Please enter a valid number");
//                    System.out.println("Try again!!!");
//                    continue;
//                }
//
//                ArrayList<Hero> heroes = null;
//                int index = 0;
//
//                switch (num){
//                    case 1:
//                        heroes = this.creaturesFactory.getPaladins();
//                        showCreatures(heroes);
//                        index = getIndex(heroes);
//                        party.add(heroes.get(index-1));
//                        break;
//                    case 2:
//                        heroes = this.creaturesFactory.getSorcerers();
//                        showCreatures(heroes);
//                        index = getIndex(heroes);
//                        party.add(heroes.get(index-1));
//                        break;
//                    case 3:
//                        heroes = this.creaturesFactory.getWarriors();
//                        showCreatures(heroes);
//                        index = getIndex(heroes);
//                        party.add(heroes.get(index-1));
//                        break;
//                    default:
//                        break;
//                }
//
//            } while(num < 1 || num > 3);
//        }
//        this.legendsGame.setParty(party);
//        System.out.println();
//        System.out.println("\t\t\t\t ******** Chosen Heroes are ********");
//        showCreatures(party);
//        System.out.println("Party is all-set to move on map !");
//    }
//
//    /**
//     * helper function to get the index based value from user
//     * @param heroes static list of heroes
//     * @return index value chosen by hero
//     */
//    private int getIndex(ArrayList<Hero> heroes) {
//        int index;
//        do {
//            System.out.println();
//            index = getIntFromUser();
//            if (index-1 < 0 || index > heroes.size()){
//                System.out.println("INVALID, Please try again!!!");
//            }
//        } while (index-1 < 0 || index > heroes.size());
//        return index;
//    }
//
//    /**
//     * show creatures list to chose from
//     * @param creatures creatures to print for user
//     */
//    private void showCreatures(ArrayList<? extends AbstractCreature> creatures){
//        Iterator<? extends AbstractCreature> it = creatures.iterator();
//        int i = 1;
//
//        while (it.hasNext()){
//            AbstractCreature abstractCreature = it.next();
//            if (i == 1){
//                System.out.println("    "+ Utility.paddedString(Hero.header));
//            }
//            System.out.println("[" + i + "] " + Utility.paddedString(abstractCreature.seekInformation()));
//            i++;
//        }
//
//        System.out.println();
//    }
//
//    /**
//     * display the map in some format using this function
//     */
//    private void displayMap(){
//        Map map = this.legendsGame.getMap();
//        Tile[][] tiles = map.getTiles();
//        StringBuilder stringBuilder = new StringBuilder();
//        int currentX = this.legendsGame.getCurrentPosition().getX();
//        int currentY = this.legendsGame.getCurrentPosition().getY();
//
//        for (int row = 0; row < tiles.length; row++){
//            stringBuilder.append("\t\t\t\t").append(new String(new char[tiles[row].length]).replace("\0", "------"));
//            stringBuilder.append("\n\t\t\t\t");
//            for (int col = 0; col < tiles[row].length; col++){
//                if (row == currentX && col == currentY){
//                    stringBuilder.append("| ").append(tiles[row][col].displayValue()).append("P*").append(" ");
//                } else {
//                    stringBuilder.append("|  ").append(tiles[row][col].displayValue()).append("  ");
//                }
//            }
//            stringBuilder.append("|\n");
//        }
//
//        stringBuilder.append("\t\t\t\t").append(new String(new char[map.getSize()]).replace("\0", "------"));
//        System.out.println(stringBuilder);
//        System.out.println();
//        System.out.println("M --> Represents the Market Space\n" +
//                "X --> Represents the Inaccessible Space\n" +
//                "  --> Represents the Common Space\n" +
//                "P* --> Represents Player position\n");
//    }
//
//    /**
//     * does the starting tasks of battle
//     */
//    @Override
//    public void run() {
//        System.out.println(this.legendsGame.overview());
//        System.out.println();
//        mapSize();
//        System.out.println();
//        partySize();
//        getEnter();
//        System.out.println();
//        driveGame();
//    }
//
//    /**
//     * check if the give move for the board is valid
//     * @param move given move
//     * @return true or false
//     */
//    private boolean isValidMove(char move){
//        boolean isValid = false;
//        if (move == 'w' && this.legendsGame.isValidMove(-1, 0)){
//            isValid = true;
//        } else if (move == 'a' && this.legendsGame.isValidMove(0, -1)) {
//            isValid = true;
//        } else if (move == 's' && this.legendsGame.isValidMove(1, 0)) {
//            isValid = true;
//        } else if (move == 'd' && this.legendsGame.isValidMove(0, 1)) {
//            isValid = true;
//        } else if (move == 'q' || move == 'i' || move == 'm'){
//            isValid = true;
//        }
//        return isValid;
//    }
//
//    /**
//     * make a move on the map
//     * @param move using this move
//     */
//    private void makeMove(char move){
//        if (!isValidMove(move)){
//            System.out.println("INVALID MOVE, Please enter a valid move!");
//            System.out.println("Try again!");
//            return;
//        }
//
//        switch (move){
//            case 'w':
//                this.legendsGame.makeMove(-1, 0);
//                break;
//            case 'a':
//                this.legendsGame.makeMove(0, -1);
//                break;
//            case 's':
//                this.legendsGame.makeMove(1, 0);
//                break;
//            case 'd':
//                this.legendsGame.makeMove(0, 1);
//                break;
//            case 'q':
//                this.legendsGame.quit();
//                break;
//            case 'i':
//                this.legendsGame.showInfo();
//                break;
//            case 'm':
//                this.legendsGame.enterMarket();
//                break;
//            default:
//                break;
//        }
//    }
//
//    /**
//     * drive the whole game until it finish
//     */
//    private void driveGame(){
//        boolean wantToReplay;
//        do {
//            char move;
//            boolean wasValidMove = false;
//            this.legendsGame.setMap(this.mapFactory.createMap(this.defaultMapSize));
//            System.out.println();
//            this.legendsGame.setCurrentPosition(this.legendsGame.getMap().getSize()-1, 0);
//            displayMap();
//            do {
//                if (wasValidMove) {
//                    System.out.println();
//                    displayMap();
//                    System.out.println();
//                }
//                System.out.println("Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]");
//                move = getCharFromUser();
//                wasValidMove = isValidMove(move);
//                makeMove(move);
//            } while (move != 'q');
//            System.out.println("Do you want to REPLAY!!! [YES/yes/y/NO/no/n]");
//            wantToReplay = getUserYesOrNo();
//        } while (wantToReplay);
//    }
//}
