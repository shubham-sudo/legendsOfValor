package map.space;

import creature.Creature;


/**
 * Plane space which is a normal space
 */
public class PlainSpace extends NormalSpace{
    private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";  // TODO: fetch this from config files

    @Override
    public String bgColor() {
        return ANSI_BLUE_BACKGROUND;
    }
}
