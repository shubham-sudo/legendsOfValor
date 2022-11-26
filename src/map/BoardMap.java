package map;

import creature.*;
import map.lane.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class BoardMap {
    public static final int DEFAULT_LANES = 3;
    public static final int MAX_LANES = 6;
    private final int numberOfLanes;
    private final int laneSize;
    private final Lane[] lanes;

    public BoardMap(int numberOfLanes, int laneSize){
        this.numberOfLanes = numberOfLanes;
        this.lanes = new Lane[numberOfLanes];
        this.laneSize = laneSize;
        initialize();
    }

    public BoardMap(){
        this(DEFAULT_LANES, Lane.DEFAULT_LENGTH);
    }

    public int getPlayableLanes(){
        return numberOfLanes;
    }

    public int getNumberOfLanes() {
        return (2 * numberOfLanes) - 1;
    }

    public int getLaneSize() {
        return laneSize;
    }

    public void sendHeroesOnMap(ArrayList<Creature> creatures) throws IllegalAccessException {
        for (int i = 0; i < numberOfLanes; i++){
            Lane lane = lanes[i];
            lane.occupySpace(creatures.get(i), lane.getLength()-1, lane.getWidth()-1);
        }
    }

    public void sendMonstersOnMap(ArrayList<Creature> creatures) throws IllegalAccessException {
        for (int i = 0; i < numberOfLanes; i++) {
            Lane lane = lanes[i];
            lane.occupySpace(creatures.get(i), 0, 0);
        }
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

    public void occupySpace(int laneIndex, int spaceRow, int spaceCol, Creature creature) throws IllegalAccessException {
        getLane(laneIndex).occupySpace(creature, spaceRow, spaceCol);
    }

    public Lane[] map(){
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