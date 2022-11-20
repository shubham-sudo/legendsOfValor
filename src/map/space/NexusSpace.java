package map.space;

import creature.Creature;


/**
 * Nexus space which is a home for Creature
 */
public class NexusSpace extends NormalSpace{
    private final String ANSI_RED = "\u001B[31m";  // TODO: fetch this from config files

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return (super.isSafeToOccupy(creature));  // TODO: add lane check here for which you also need to maintain the lane for every space
    }

    @Override
    public String bgColor() {
        return ANSI_RED;
    }

    @Override
    public void occupy(Creature creature) throws IllegalAccessException {
        super.occupy(creature);
        // TODO:(shubham) create market and show options to creature ..
        //  put a check for hero, although monster is managed by computer so it will never ask for market
        //  also need a nexus controller to get input from user and also generate the monster after few rounds
    }
}
