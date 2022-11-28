package map;

import creature.*;
import map.lane.*;
import map.space.FortressSpace;
import map.space.Space;
import move.GameMove;
import move.Move;

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

    public String[] getLaneLabels(){
        String[] strings = new String[getNumberOfLanes()];
        int laneNumber = 1;
        for (int i = 0; i < getNumberOfLanes(); i++){
            if (i % 2 == 0){
                // Passable lane
                char[] label = new String(new char[Space.EMPTY_SPACE.length() * PassableLane.DEFAULT_WIDTH]).replace("\0", " ").toCharArray();
                int start = label.length / 2;
                label[start-1] = 'L';
                label[start] = '-';
                label[start+1] = (char) (laneNumber + '0');
                strings[i] = String.valueOf(label);
                laneNumber++;
            } else {
                // Impassable Lane
                strings[i] = Space.EMPTY_SPACE;
            }
        }
        return strings;
    }

    public void sendHeroesOnMap(ArrayList<Creature> creatures) throws IllegalAccessException {
        for (int i = 0; i < numberOfLanes; i++){
            Lane lane = lanes[i];
            lane.occupySpace(creatures.get(i), lane.getLength()-1, lane.getWidth()-1);
            creatures.get(i).setHomeLane(i);
            creatures.get(i).setCurrentPosition(i, lane.getLength()-1, lane.getWidth()-1);
        }
    }

    public void sendMonstersOnMap(ArrayList<Creature> creatures) throws IllegalAccessException {
        for (int i = 0; i < numberOfLanes; i++) {
            Lane lane = lanes[i];
            lane.occupySpace(creatures.get(i), 0, 0);
            creatures.get(i).setHomeLane(i);
            creatures.get(i).setCurrentPosition(i, 0, 0);
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

    public boolean isSafeToOccupy(Move move){
        if (move.laneNumber >= getPlayableLanes()){
            return false;
        }
        Lane lane = getLane(move.laneNumber);
        if (move.gameMove == GameMove.UP && !lane.isOpponentNearBy(move.creature)
                && lane.isSafeToOccupy(move.creature, move.rowNumber, move.colNumber)){
            return true;
        }
        if ((move.gameMove == GameMove.RECALL || move.gameMove == GameMove.DOWN) && lane.isSafeToOccupy(move.creature, move.rowNumber, move.colNumber)){
            return true;
        } else if (move.gameMove == GameMove.LEFT || move.gameMove == GameMove.RIGHT){
            return lane.isSafeToOccupy(move.creature, move.rowNumber, move.colNumber);
        } else if (move.gameMove == GameMove.TELEPORT){
            return lane.isSafeToTeleport(move.creature, move.laneNumber, move.rowNumber, move.colNumber);
        }
        return false;
    }

    public void occupySpace(int laneIndex, int spaceRow, int spaceCol, Creature creature) throws IllegalAccessException {
        Position position = creature.getCurrentPosition();
        Lane lane = getLane(position.laneNumber);
        lane.vacantSpace(creature, position.rowNumber, position.colNumber);
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

    public boolean isMarket(Move move){
        return getLane(move.laneNumber).getSpace(move.rowNumber, move.colNumber) instanceof FortressSpace;
    }
}