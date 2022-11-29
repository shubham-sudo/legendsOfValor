package map.space;

import PubSub.GameWinPublisher;
import creature.Creature;
import creature.Hero;


/**
 * Nexus space which is a home for Creature
 */
public class NexusSpace extends NormalSpace{
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";

    /**
     * Is safe to occupy this space by any creature
     * @param creature creature object
     * @return boolean
     */
    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return (super.isSafeToOccupy(creature));
    }

    /**
     * Return the background color for this particular space
     *
     * @see Space#bgColor()
     * @return String
     */
    @Override
    public String bgColor() {
        return ANSI_RED_BACKGROUND;
    }

    /**
     * Occupy the space
     * @param creature creature who want to occupy
     * @throws IllegalAccessException error is not possible
     */
    @Override
    public void occupy(Creature creature) throws IllegalAccessException {
        super.occupy(creature);
        if (creature instanceof Hero){
            GameWinPublisher.getWinPublisherInstance().notifyObservers(creature, null, this);
        }
    }
}
