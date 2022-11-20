package map.space;

import creature.Creature;


/**
 * Plane space which is a normal space
 */
public class PlainSpace extends NormalSpace{
    private final String ANSI_YELLOW = "\u001B[33m";  // TODO: fetch this from config files

    @Override
    public String bgColor() {
        return ANSI_YELLOW;
    }
}
