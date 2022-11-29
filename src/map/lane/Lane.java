package map.lane;

import creature.Creature;
import creature.Hero;
import map.Position;
import map.space.Space;

import java.util.HashSet;
import java.util.NoSuchElementException;


/**
 * Base class for different types of lanes
 * @see ImpassibleLane
 * @see PassableLane
 */
public abstract class Lane {
    public static final int DEFAULT_LENGTH = 8;
    public static final int DEFAULT_WIDTH = 2;
    public static int ID = 0;
    private final int id;
    private final int length;
    protected int width;
    protected Space[][] spaces;
    private final HashSet<Position> positions;
    private final HashSet<Creature> creaturesInLane;

    /**
     * Lane constructor
     * @param length length of lane constructor
     */
    public Lane(int length){
        this.id = ID++;
        this.length = length;
        positions = new HashSet<>();
        creaturesInLane = new HashSet<>();
    }

    /**
     * Constructor
     */
    public Lane(){
        this(DEFAULT_LENGTH);
    }

    /**
     * Get Creature, if creature is present in nearby spaces of this lane
     * @param creature creature on the lane
     * @return Creature
     */
    public Creature getOpponentNearBy(Creature creature) {
        Creature opponent = null;
        int row = creature.getCurrentPosition().rowNumber;
        int col = creature.getCurrentPosition().colNumber;

        int[][] nearByIndex = new int[][]{
                {-1, -1},
                {-1, 0},
                {-1, +1},
                {0, -1},
                {0, 0},
                {0, +1},
                {+1, -1},
                {+1, 0},
                {+1, +1},
        };

        for (int[] byIndex : nearByIndex) {
            Space space;
            try {
                space = getSpace(row + byIndex[0], col + byIndex[1]);
            } catch (NoSuchElementException ne) {
                continue;
            }
            if (space.hasOpponent(creature)) {
                opponent = space.getOpponent(creature);
            }
        }
        return opponent;
    }

    /**
     * Get the space of the lane
     * @param row rowNumber
     * @param col colNumber
     * @return Space object
     * @throws NoSuchElementException If no such space exists
     */
    public Space getSpace(int row, int col) throws NoSuchElementException {
        if (row > -1 && row < this.length && col > -1 && col < this.width){
            return this.spaces[row][col];
        }
        throw new NoSuchElementException("Invalid index!");
    }

    /**
     * Check if it safe to teleport in this lane at particular space
     * @param creature creature who wants to teleport
     * @param laneNumber lane number
     * @param rowNumber row number
     * @param colNumber col number
     * @return boolean
     */
    public boolean isSafeToTeleport(Creature creature, int laneNumber, int rowNumber, int colNumber){
        if (laneNumber == creature.getCurrentPosition().laneNumber){
            return false;
        }
        boolean positionBelowOtherCreatures = true;
        for (Creature cr : creaturesInLane){
            Position currentPosition = cr.getCurrentPosition();
            if (currentPosition.rowNumber > rowNumber){
                positionBelowOtherCreatures = false;
                break;
            }
        }
        return positions.contains(new Position(laneNumber, rowNumber, colNumber)) && positionBelowOtherCreatures;
    }

    /**
     * Add position to teleport hashset to track
     * @param row row number
     * @param col col number
     */
    private void addTeleportPositions(int row, int col){
        for (int i = 0; i < spaces[row].length; i++){
            positions.add(new Position(id, row, i));
        }
    }

    /**
     * Check if opponent is nearby to the creature
     * @param creature creature object
     * @return boolean
     */
    public boolean isOpponentNearBy(Creature creature){
        int row = creature.getCurrentPosition().rowNumber;
        int col = creature.getCurrentPosition().colNumber;

        int[][] nearByIndex = new int[][]{
                {0, 0},
                {0, +1},
                {0, -1},
        };
        boolean opponentNearBy = false;

        for (int[] byIndex : nearByIndex) {
            Space space;
            try {
                space = getSpace(row + byIndex[0], col + byIndex[1]);
            } catch (NoSuchElementException ne) {
                continue;
            }
            if (space.hasOpponent(creature)) {
                opponentNearBy = true;
            }
        }
        return opponentNearBy;
    }

    /**
     * Check if it safe to occupy this space of lane
     * @param creature creature who want to occupy
     * @param spaceRow row number
     * @param spaceCol col number
     * @return boolean
     */
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

    /**
     * Occupy the space if it is safe to occupy
     * @param creature creature
     * @param spaceRow row number
     * @param spaceCol col number
     * @throws IllegalAccessException if not a valid operation
     */
    public void occupySpace(Creature creature, int spaceRow, int spaceCol) throws IllegalAccessException {
        if (isSafeToOccupy(creature, spaceRow, spaceCol)) {
            getSpace(spaceRow, spaceCol).occupy(creature);
            creature.setCurrentPosition(id, spaceRow, spaceCol);
            creaturesInLane.add(creature);
            if (creature instanceof Hero) {
                addTeleportPositions(spaceRow, spaceCol);
            }
        } else {
            throw new IllegalAccessException("Invalid Move!");
        }
    }

    /**
     * Vacant the space of a lane when creature move from that space
     * @param creature creature who was at the space
     * @param spaceRow row number
     * @param spaceCol col number
     */
    public void vacantSpace(Creature creature, int spaceRow, int spaceCol) {
        creaturesInLane.remove(creature);
        getSpace(spaceRow, spaceCol).vacant(creature);
    }

    /**
     * Set the space to a special or normal space object
     * @param row row number
     * @param col col number
     * @param space space object
     * @throws NoSuchElementException if unable to access to position
     */
    protected void setSpace(int row, int col, Space space) throws NoSuchElementException{
        if (row > -1 && row < this.length && col > -1 && col < this.width){
            this.spaces[row][col] = space;
        } else {
            throw new NoSuchElementException("Invalid index!");
        }
    }

    /**
     * Getter for length
     * @return integer
     */
    public int getLength() {
        return this.length;
    }

    /**
     * Getter for width
     * @return integer
     */
    public int getWidth() {
        return width;
    }

    /**
     * Initialize the lane and add spaces
     */
    public void initialize() {
        buildLane();
    }

    /**
     * Abstract method to build lane
     */
    protected abstract void buildLane();
}
