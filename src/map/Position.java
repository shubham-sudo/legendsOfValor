package map;

/**
 * Position is to represent the position of creature on board
 */
public class Position {
    public int laneNumber;
    public int rowNumber;
    public int colNumber;

    /**
     * Constructor
     * @param laneNumber lane number
     * @param rowNumber row number
     * @param colNumber col number
     */
    public Position(int laneNumber, int rowNumber, int colNumber){
        this.laneNumber = laneNumber;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
    }

    /**
     * Constructor
     */
    public Position(){
        this(-1, -1, -1);
    }

    /**
     * Hashcode to compare
     * @return integer
     */
    @Override
    public int hashCode() {
        return Integer.parseInt("1" + laneNumber + rowNumber + colNumber);
    }

    /**
     * compare the positions
     * @param o another object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) {
            return false;
        }
        Position other = (Position) o;

        return other.laneNumber == laneNumber && other.rowNumber == rowNumber && other.colNumber == colNumber;
    }
}
