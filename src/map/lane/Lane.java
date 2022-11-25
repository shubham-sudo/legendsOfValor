package map.lane;

import map.space.Space;

import java.util.NoSuchElementException;


/**
 * Base class for different types of lanes
 * @see ImpassibleLane
 * @see PassableLane
 */
public abstract class Lane {
    public static final int DEFAULT_LENGTH = 8;
    private static int ID = 0;
    private final int id;
    private final int length;
    protected int width;
    protected Space[][] spaces;

    public Lane(int length){
        this.id = ++ID;
        this.length = length;
        // TODO: (shubham) maintain a list of all creatures in this lane and their current position
        //  lane should give all the valid moves for a particular creature
        //  this should also include all the teleport moves also.
    }

    public Lane(){
        this(DEFAULT_LENGTH);
    }

    public Space[][] getLane() {
        return spaces;
    }

    public int getId() {
        return id;
    }

    public Space getSpace(int row, int col) throws NoSuchElementException {
        if (row > -1 && row < this.length && col > -1 && col < this.width){
            return this.spaces[row][col];
        }
        throw new NoSuchElementException("Invalid index!");
    }

    protected void setSpace(int row, int col, Space space) throws NoSuchElementException{
        if (row > -1 && row < this.length && col > -1 && col < this.width){
            this.spaces[row][col] = space;
        } else {
            throw new NoSuchElementException("Invalid index!");
        }
    }

    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return width;
    }

    public void initialize() {
        buildLane();
    }

    protected abstract void buildLane();
}
