package map;

public class Position {
    public int laneNumber;
    public int rowNumber;
    public int colNumber;

    public Position(int laneNumber, int rowNumber, int colNumber){
        this.laneNumber = laneNumber;
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
    }

    public Position(){
        this(-1, -1, -1);
    }

    @Override
    public int hashCode() {
        return Integer.parseInt("1" + laneNumber + rowNumber + colNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) {
            return false;
        }
        Position other = (Position) o;

        return other.laneNumber == laneNumber && other.rowNumber == rowNumber && other.colNumber == colNumber;
    }
}
