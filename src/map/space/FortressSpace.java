package map.space;

import PubSub.GameWinPublisher;
import creature.Creature;
import creature.Monster;

/**
 * Fortress space is just like the Nexus for monster, but this is representing the heroes nexus
 */
public class FortressSpace extends NormalSpace {
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";  // TODO: fetch this from config files

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
        return ANSI_CYAN_BACKGROUND;
    }

    /**
     * Occupy the space
     * @param creature creature who want to occupy
     * @throws IllegalAccessException error is not possible
     */
    @Override
    public void occupy(Creature creature) throws IllegalAccessException {
        super.occupy(creature);
        if (creature instanceof Monster){
            GameWinPublisher.getWinPublisherInstance().notifyObservers(creature, null,this);
        }
    }
}
