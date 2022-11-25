package map;

import creature.*;
import map.lane.*;
import map.space.*;

import java.util.NoSuchElementException;


public class Map {
    private static final int DEFAULT_LANES = 3;
    private static final int MAX_LANES = 6;
    private final int numberOfLanes;
    private final int laneSize;
    private final Lane[] lanes;

    public Map(int numberOfLanes, int laneSize){
        this.numberOfLanes = numberOfLanes;
        this.lanes = new Lane[numberOfLanes];
        this.laneSize = laneSize;
        initialize();
    }

    public Map(){
        this(DEFAULT_LANES, Lane.DEFAULT_LENGTH);
    }

    public int getNumberOfLanes() {
        return (2 * numberOfLanes) - 1;
    }

    public int getLaneSize() {
        return laneSize;
    }

    private void initialize(){
        for (int i = 0; i < numberOfLanes; i++){
            lanes[i] = new PassableLane(laneSize);
            lanes[i].initialize();
        }
    }

    public Lane getLane(int index) throws NoSuchElementException {
        if (index < 0 || index >= this.numberOfLanes){
            throw new NoSuchElementException("Invalid Lane!!!");
        }
        return lanes[index];
    }

    public Space getSpace(int laneIndex, int spaceRow, int spaceCol) throws NoSuchElementException{
        return getLane(laneIndex).getSpace(spaceRow, spaceCol);
    }

    public void occupySpace(int laneIndex, int spaceRow, int spaceCol, Creature creature) throws IllegalAccessException {
        Space space = getSpace(laneIndex, spaceRow, spaceCol);
        if (space.isSafeToOccupy(creature)){
            space.occupy(creature);
        } else {
            throw new IllegalAccessException("Invalid Access!!!");
        }
    }

    public Lane[] getMap(){
        Lane[] displayLanes = new Lane[(2 * numberOfLanes) - 1];
        int i = 0;
        int k = 0;
        while (i < (2 * (numberOfLanes-1))){
            displayLanes[i] = lanes[k];
            displayLanes[i+1] = new ImpassibleLane(laneSize);
            displayLanes[i+1].initialize();
            i += 2;
            k += 1;
        }
        displayLanes[i] = lanes[k];
        return displayLanes;
    }
}