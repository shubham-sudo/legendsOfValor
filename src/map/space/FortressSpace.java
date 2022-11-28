package map.space;

import PubSub.GameWinPublisher;
import creature.Creature;
import creature.Monster;


public class FortressSpace extends NormalSpace {
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";  // TODO: fetch this from config files

    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return (super.isSafeToOccupy(creature));
    }

    @Override
    public String bgColor() {
        return ANSI_CYAN_BACKGROUND;
    }

    @Override
    public void occupy(Creature creature) throws IllegalAccessException {
        super.occupy(creature);
        if (creature instanceof Monster){
            GameWinPublisher.getWinPublisherInstance().notifyObservers(creature, null,this);
        }
    }
}
