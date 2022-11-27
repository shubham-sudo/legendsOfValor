package move;

import creature.Creature;

public class Move {
    public final GameMove gameMove;
    public final Creature creature;
    public final int laneNumber;
    public final int rowNumber;
    public final int colNumber;

    public Move(Creature creature, int lane, int row, int col, GameMove gameMove){
        this.creature = creature;
        this.laneNumber = lane;
        this.rowNumber = row;
        this.colNumber = col;
        this.gameMove = gameMove;
    }
}
