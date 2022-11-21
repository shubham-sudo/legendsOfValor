package map.space;

import creature.Creature;


/**
 * Nexus space which is a home for Creature
 */
public class NexusSpace extends NormalSpace{
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return (super.isSafeToOccupy(creature));  // TODO: add lane check here for which you also need to maintain the lane for every space
    }

    @Override
    public String bgColor() {
        return ANSI_RED_BACKGROUND;
    }

    @Override
    public void occupy(Creature creature) throws IllegalAccessException {
        super.occupy(creature);
        // TODO:(shubham) end the game if creature is a Hero
    }
}
