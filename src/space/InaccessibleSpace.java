package space;

import creature.Hero;

import java.util.ArrayList;

/**
 * Inaccessible space on map (maybe wall)
 */
public class InaccessibleSpace implements Space {

    /**
     * Return true if this space is accessible
     * @see Space#isAccessible()
     * @return boolean
     */
    @Override
    public boolean isAccessible() {
        return false;
    }

    /**
     * Occupy this space if accessible
     * @see Space#occupy(ArrayList)
     * @param party party to access this space
     */
    @Override
    public void occupy(ArrayList<Hero> party) throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * to string for this space
     * @return string
     */
    @Override
    public String toString() {
        return "X";
    }
}
