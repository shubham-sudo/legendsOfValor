package map.space;

import creature.Creature;


/**
 * Inaccessible space which is used for walls between lanes
 */
public class InaccessibleSpace extends NormalSpace{
    private final String ANSI_BLACK = "\u001B[30m";  // TODO: fetch this from config files

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return false;
    }

    @Override
    public String bgColor() {
        return ANSI_BLACK;
    }
}
