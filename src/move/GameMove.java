package move;

/**
 * Game Moves
 * UP, DOWN, RIGHT, LEFT, MARKET, ATTACK, CAST, POTION, EQUIP, INFO
 */
public enum GameMove {
    UP('u'),
    DOWN('d'),
    RIGHT('r'),
    LEFT('l'),
    MARKET('m'),
    ATTACK('a'),
    CAST('c'),
    POTION('p'),
    EQUIP('e'),
    DROP('w'),
    INFO('i'),
    RECALL('b'),
    TELEPORT('t'),
    EXIT('q');

    private final char move;
    GameMove(char move){
        this.move = move;
    }

    /**
     * Check the string and return Game move
     * @param move Move
     * @return Game Move
     * @throws IllegalArgumentException
     */
    public static GameMove vOf(char move) throws IllegalArgumentException{
        for (GameMove gameMove : GameMove.values()){
            if (gameMove.equals(move)){
                return gameMove;
            }
        }
        throw new IllegalArgumentException("Invalid Move, Please try again!");
    }

    public boolean equals(char move){
        return move == this.move;
    }
}
