package map.space;

import PubSub.GameWinPublisher;
import creature.Creature;
import creature.Hero;


/**
 * Nexus space which is a home for Creature
 */
public class NexusSpace extends NormalSpace{
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return (super.isSafeToOccupy(creature));
    }

    @Override
    public String bgColor() {
        return ANSI_RED_BACKGROUND;
    }

    @Override
    public void occupy(Creature creature) throws IllegalAccessException {
        super.occupy(creature);
        if (creature instanceof Hero){
            GameWinPublisher.getWinPublisherInstance().notifyObservers(creature, this);
        }
    }
}
