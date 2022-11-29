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

    public Lane(int length){
        this.id = ID++;
        this.length = length;
        positions = new HashSet<>();
        creaturesInLane = new HashSet<>();
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

    public Space getSpace(int row, int col) throws NoSuchElementException {
        if (row > -1 && row < this.length && col > -1 && col < this.width){
            return this.spaces[row][col];
        }
        throw new NoSuchElementException("Invalid index!");
    }

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

    private void addTeleportPositions(int row, int col){
        for (int i = 0; i < spaces[row].length; i++){
            positions.add(new Position(id, row, i));
        }
    }

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
            creaturesInLane.add(creature);
            if (creature instanceof Hero) {
                addTeleportPositions(spaceRow, spaceCol);
            }
        } else {
            throw new IllegalAccessException("Invalid Move!");
        }
    }

    public void vacantSpace(Creature creature, int spaceRow, int spaceCol) {
        creaturesInLane.remove(creature);
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
