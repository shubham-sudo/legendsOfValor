package map;

import space.Space;

/**
 * Map of the game where player can move on different tiles
 */
public class Map {
    public static final int MIN_SIZE = 8;
    public static final int MAX_SIZE = 12;
    private final int size;
    private final Tile[][] tiles;

    /**
     * creates a new map for the game
     * @param size given size of map
     */
    public Map(int size){
        if (size < 4 || size > 12){
            throw new IllegalArgumentException("ERROR: Size should be between 4 & 12");
        }
        this.size = size;
        this.tiles = new Tile[this.size][this.size];
    }

    /**
     * creates a map with default size
     */
    public Map(){
        this(MIN_SIZE);
    }

    /**
     * getter for the size of map
     * @return integer
     */
    public int getSize() {
        return size;
    }

    /**
     * setter for the tile of the map
     * @param i x coordinate
     * @param j y coordinate
     * @param space space to place on map
     */
    public void setTile(int i, int j, Space space){
        this.tiles[i][j] = new Tile(space);
    }

    /**
     * get total number of tiles on map
     * @return total size of map
     */
    public int getTotalTiles() {
        return size * size;
    }

    /**
     * get all tiles as an array
     * @return array of tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * get a specific tile of the map
     * @param row x coordinate
     * @param col y coordinate
     * @return tile object
     * @throws IllegalAccessException
     */
    public Tile getTile(int row, int col) throws IllegalAccessException{
        if (row < 0 || row >= this.getSize() || col < 0 || col >= this.getSize()){
            throw new IllegalAccessException();
        }
        return this.tiles[row][col];
    }
}
