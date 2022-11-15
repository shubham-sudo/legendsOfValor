package space;

import battle.Battle;
import creature.Hero;

import java.util.ArrayList;

/**
 * Common space on the tile
 */
public class CommonSpace implements Space {

    /**
     * check if this space is accessible
     * @see Space#isAccessible()
     * @return
     */
    @Override
    public boolean isAccessible() {
        return true;
    }

    /**
     * Occupy this space if accessible
     * @see Space#occupy(ArrayList)
     * @param party party to access this space
     */
    @Override
    public void occupy(ArrayList<Hero> party) {
        int diceValue = (int)(Math.random()*6+1);
        if (diceValue <= 2){
            Battle battle = new Battle(party);
            battle.run();
        }
    }

    /**
     * to string for this space
     * @return string
     */
    @Override
    public String toString() {
        return " ";
    }
}
