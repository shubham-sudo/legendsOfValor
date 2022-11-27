package move;

import creature.Creature;

public class Move {
    public final GameMove gameMove;
    public final Creature creature;
    public int laneNumber;
    public int rowNumber;
    public int colNumber;

    public Move(Creature creature, int laneNumber, GameMove gameMove){
        this.creature = creature;
        this.laneNumber = laneNumber;
        this.rowNumber = -1;
        this.colNumber = -1;
        this.gameMove = gameMove;
    }
}
