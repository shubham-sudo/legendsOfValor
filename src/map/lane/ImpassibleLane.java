package map.lane;

import map.space.InaccessibleSpace;


/**
 * Impassible lanes are like walls which are composed of Inaccessible Spaces
 * This can be further used to remove an inaccessible space from wall
 * for an upgraded creature.
 */
public class ImpassibleLane extends Lane {

    public ImpassibleLane(int length){
        super(length);
        this.width = 1;
        this.spaces = new InaccessibleSpace[getLength()][width];
    }

    public ImpassibleLane(){
        this(Lane.DEFAULT_LENGTH);
    }

    protected void buildLane(){
        for (int i = 0; i < getLength(); i++) {
            for (int j = 0; j < getWidth(); j++){
                setSpace(i, j, new InaccessibleSpace());
            }
        }
    }
}
