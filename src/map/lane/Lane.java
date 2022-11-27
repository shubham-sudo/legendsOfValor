package map.lane;

import creature.Creature;
import map.Position;
import map.space.Space;

import java.util.HashMap;
import java.util.NoSuchElementException;


/**
 * Base class for different types of lanes
 * @see ImpassibleLane
 * @see PassableLane
 */
public abstract class Lane {
    public static final int DEFAULT_LENGTH = 8;
    public static final int DEFAULT_WIDTH = 2;
    private static int ID = 0;
    private final int id;
    private final int length;
    protected int width;
    protected Space[][] spaces;
    protected HashMap<Creature, Position> creatures;

    public Lane(int length){
        this.id = ID++;
        this.length = length;
        this.creatures = new HashMap<>();
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

    public boolean isOpponentNearBy(Creature creature, int row, int col){
        int[][] nearByIndex = new int[][]{
                {-1, -1},
                {-1, 0},
                {-1, +1},
                {0, +1},
                {+1, +1},
                {+1, 0},
                {+1, -1},
                {0, -1},
        };
        boolean opponentNearBy = false;

        for (int i = 0; i < nearByIndex.length; i++){
            Space space;
            try {
                space = getSpace(row + nearByIndex[i][0], col + nearByIndex[i][1]);
            } catch (NoSuchElementException nsee){
//                nsee.printStackTrace();  // TODO: (shubham) remove this line
                continue;
            }
            if (space.hasOpponent(creature)){
                opponentNearBy = true;
            }
        }
        return opponentNearBy;
    }

    public boolean isSafeToOccupy(Creature creature, int spaceRow, int spaceCol) {
        try {
            if (getSpace(spaceRow, spaceCol).isSafeToOccupy(creature)) {
                return true;
            }
        } catch (NoSuchElementException nsee){
            return false;
        }
        return false;
    }

    public void occupySpace(Creature creature, int spaceRow, int spaceCol) throws IllegalAccessException {
        if (isSafeToOccupy(creature, spaceRow, spaceCol)) {
            getSpace(spaceRow, spaceCol).occupy(creature);
            creature.setCurrentPosition(id, spaceRow, spaceCol);
        } else {
            throw new IllegalAccessException("Invalid Move!");
        }
    }

    public void vacantSpace(Creature creature, int spaceRow, int spaceCol) {
        getSpace(spaceRow, spaceCol).vacant(creature);
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
