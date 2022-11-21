//package game;
//
//import creature.Hero;
//import map.Map;
//import map.CurrentPosition;
//import map.Tile;
//import space.MarketSpace;
//
//import java.util.ArrayList;
//
///**
// * Child class of Game which defines the Legend Game
// */
//public class LegendsGame extends Game{
//    public static final int PARTY_MIN_SIZE = 3;
//    public static final int PARTY_MAX_SIZE = 9;
//    private ArrayList<Hero> party;
//    private Map map;
//    private final CurrentPosition currentPosition;
//
//    /**
//     * Creates a new object of legends game
//     */
//    public LegendsGame(){
//        this.map = new Map();
//        this.party = new ArrayList<>();
//        this.currentPosition = new CurrentPosition();
//    }
//
//    /**
//     * get map for this game
//     * @return Map object
//     */
//    public Map getMap() {
//        return map;
//    }
//
//    /**
//     * Set map for the game
//     * @param map map object
//     */
//    public void setMap(Map map) {
//        this.map = map;
//    }
//
//    /**
//     * Get the heroes party playing this game
//     * @return list of heroes
//     */
//    public ArrayList<Hero> getParty() {
//        return party;
//    }
//
//    /**
//     * Setter for the heroes party
//     * @param party ArrayList of party
//     */
//    public void setParty(ArrayList<Hero> party) {
//        this.party = party;
//    }
//
//    /**
//     * Current position of the player on the map
//     * @return current position object
//     */
//    public CurrentPosition getCurrentPosition() {
//        return currentPosition;
//    }
//
//    /**
//     * Setting new position of the player if valid
//     * @param x x coordinate on map
//     * @param y y coordinate on map
//     * @throws IllegalArgumentException
//     */
//    public void setCurrentPosition(int x, int y) throws IllegalArgumentException {
//        if (x < 0 || x >= this.map.getSize() || y < 0 || y >= this.map.getSize()){
//            throw new IllegalArgumentException();
//        }
//        this.currentPosition.setX(x);
//        this.currentPosition.setY(y);
//    }
//
//    /**
//     * validate the more of the player on board
//     * @param x requested x coordinate
//     * @param y requested y coordinate
//     * @return boolean
//     */
//    public boolean isValidMove(int x, int y){
//        return this.currentPosition.getY() + y >= 0 && this.currentPosition.getY() + y < this.map.getSize()
//                && this.currentPosition.getX() + x >= 0 && this.currentPosition.getX() + x < this.map.getSize()
//                && this.map.getTiles()[this.currentPosition.getX() + x][this.currentPosition.getY() + y].isAccessible();
//    }
//
//    /**
//     * Description about the game
//     * @return String value
//     */
//    public String overview() {
//        return "\n" + "******** " + this + " ********\n" +
//                "This is a magical game full of spells, monsters, and heroes.\n" +
//                "The monsters and heroes live in a fictional world. They do not get along and therefore fight each other.";
//    }
//
//    /**
//     * Make a move in the game post validation of the coordinates
//     * @param i x coordinate in the map
//     * @param j y coordinate in the map
//     */
//    public void makeMove(int i, int j) {
//        try{
//            this.map.getTiles()[this.getCurrentPosition().getX() + i][this.getCurrentPosition().getY() + j].occupy(this.party);
//            this.setCurrentPosition(this.getCurrentPosition().getX() + i, this.getCurrentPosition().getY() + j);
//        } catch (IllegalAccessException e){
//            System.out.println("INVALID MOVE: InAccessible Tile");
//        }
//    }
//
//    /**
//     * Quit the game
//     */
//    public void quit() {
//        System.out.println();
//        System.out.println("Hope you enjoyed the "+this+" game!!!");
//        System.out.println();
//    }
//
//    /**
//     * show info is for showing info of the player in the Game
//     */
//    public void showInfo(){
//        super.showInfo(this.party);
//    }
//
//    /**
//     * Enter into the market only if player is on market tile
//     */
//    public void enterMarket(){
//        Tile tile = null;
//        try{
//            tile = this.map.getTile(currentPosition.getX(), currentPosition.getY());
//        } catch (IllegalAccessException ie){
//            System.out.println();
//        }
//        if (tile != null && tile.getSpace() instanceof MarketSpace){
//            makeMove(0, 0);
//        } else {
//            System.out.println("Invalid move!!!");
//            System.out.println("No Market on current tile, Try again!!!");
//        }
//    }
//
//    /**
//     * To string method for LegendsGame
//     * @return String
//     */
//    @Override
//    public String toString() {
//        return this.getClass().getSimpleName();
//    }
//}
