package space;

import creature.Hero;

import java.util.ArrayList;

/**
 * An interface for all types of space on tiles
 */
public interface Space {
    /**
     * Return true if this space is accessible
     * @return boolean
     */
    boolean isAccessible();

    /**
     * Occupy this space if accessible
     * @param party party to access this space
     */
    void occupy(ArrayList<Hero> party) throws IllegalAccessException;
}
