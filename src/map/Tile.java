package map;

import creature.Hero;
import space.Space;

import java.util.ArrayList;

/**
 * Each Tile object of the map
 */
public class Tile {
    private final Space space;

    /**
     * Creates a new tile object with the given space of it
     * @param space space to place on this particular tile
     */
    public Tile(Space space){
        this.space = space;
    }

    /**
     * Check if the tile is inaccessible
     * @return true if inaccessible, false otherwise
     */
    public boolean isAccessible(){
        return this.space.isAccessible();
    }

    /**
     * Get space placed on this tile
     * @return space
     */
    public Space getSpace() {
        return space;
    }

    /**
     * Try to occupy this tile
     * @param party player trying to move on this tile
     * @throws IllegalAccessException
     */
    public void occupy(ArrayList<Hero> party) throws IllegalAccessException{
        if (space.isAccessible()){
            space.occupy(party);
        }
        else {
            throw new IllegalAccessException();
        }
    }

    /**
     * display value for this particular space
     * @return string value
     */
    public String displayValue(){
        return space.toString();
    }
}
