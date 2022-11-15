package map;

/**
 * Current position is just to hold the position of the player in the map
 */
public class CurrentPosition {
    private int x;
    private int y;

    /**
     * creates a new position
     */
    public CurrentPosition(){
    }

    /**
     * getter for x coordinate
     * @return integer
     */
    public int getX() {
        return x;
    }

    /**
     * getter of y coordinate
     * @return integer
     */
    public int getY() {
        return y;
    }

    /**
     * Setter of x coordinate
     * @param x integer
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * setter of y coordinate
     * @param y integer
     */
    public void setY(int y){
        this.y = y;
    }
}
