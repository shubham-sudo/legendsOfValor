package controller;

import map.Map;
import map.lane.Lane;
import map.space.Space;

public class LegendsOfValorController implements GameController {

    private final Map map;

    public LegendsOfValorController(){
        this.map = new Map();
        this.map.initialize();
    }


    /**
     * display the map in some format using this function
     */
    private void displayMap(){
        Lane[] lanes = map.getMap();

        for (int i = 0; i < map.getLaneSize(); i++){
            for (int j = 0; j < map.getNumberOfLanes(); j++){
                Lane lane = lanes[j];
                for (int k = 0; k < lane.getWidth(); k++){
                    Space space = lane.getSpace(i, k);  // TODO: (shubham) think if we can have border for every space
                    System.out.print(space.bgColor() + space.displayValue() + Space.ANSI_RESET);
                }
            }
            System.out.println();
        }
    }

    @Override
    public void run() {
        displayMap();
    }
}
