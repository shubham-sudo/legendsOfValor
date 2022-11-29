package map.lane;

import map.space.*;

import java.util.*;


/**
 * Passable lanes are composed of special and accessible plain spaces
 */
public class PassableLane extends Lane {
    private static final double EACH_SPECIAL_SPACES = 0.20;
    private static final double PLAIN_SPACE_PERCENT = 0.40;

    /**
     * Constructor for Passable lanes
     * @param length length of lane
     */
    public PassableLane(int length){
        super(length);
        this.width = Lane.DEFAULT_WIDTH;
        this.spaces = new Space[getLength()][width];
    }

    /**
     * Constructor with default length
     */
    public PassableLane(){
        this(Lane.DEFAULT_LENGTH);
    }

    /**
     * Build the lane with given arguments in the constructor
     *
     * @see Lane#buildLane()
     */
    @Override
    protected void buildLane() {
        int totalSpaces = (spaces.length - 2) * width;  // Minus 2 is for Nexus and Fortress
        int plainSpaces = (int) (totalSpaces * PLAIN_SPACE_PERCENT);  // plain spaces count
        int specialSpaces = totalSpaces - plainSpaces;  // special spaces count
        int eachSpecialSpace = specialSpaces / 3;
        int remainingSpace = specialSpaces - (eachSpecialSpace * 3);
        plainSpaces += remainingSpace;

        assert (plainSpaces + (eachSpecialSpace * 3)) == totalSpaces;  // check if calculation is right

        // Add Monster nexus to the spaces
        spaces[0] = new Space[width];
        for (int i = 0; i < width; i++){
            spaces[0][i] = new NexusSpace();
        }

        // Add Hero Fortress to the spaces
        spaces[getLength()-1] = new Space[width];
        for (int i = 0; i < width; i++){
            spaces[getLength()-1][i] = new FortressSpace();
        }

        // Create double than the required spaces
        ArrayList<Space> restSpaces = new ArrayList<>();
        for (int i = 0; i < eachSpecialSpace; i++){
            Collections.addAll(
                    restSpaces,
                    new BushSpace(),
                    new CaveSpace(),
                    new KoulouSpace(),
                    new BushSpace(),
                    new CaveSpace(),
                    new KoulouSpace());
        }

        // shuffle the spaces to make them random
        Collections.shuffle(restSpaces);

        // add to the lane
        int k = 0;
        for (int i = 1; i < getLength()-1; i++){
            for (int j = 0; j < width; j++){
                spaces[i][j] = restSpaces.get(k);
                k++;
            }
        }
    }
}
