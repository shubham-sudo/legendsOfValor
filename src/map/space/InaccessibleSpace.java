package map.space;

import creature.Creature;


/**
 * Inaccessible space which is used for walls between lanes
 */
public class InaccessibleSpace extends NormalSpace{
    @Override
    public boolean isSafeToOccupy(Creature creature) {
        return false;
    }
}
