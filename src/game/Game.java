package game;

import creature.Hero;
import utility.Utility;
import creature.Monster;
import creature.AbstractCreature;

import java.util.*;

/**
 * Abstract Game class for all the games
 */
public abstract class Game {

    /**
     * Showing info of the players playing the game
     * @param party is the List of Heroes
     */
    protected void showInfo(ArrayList<? extends AbstractCreature> party){
        Iterator<? extends AbstractCreature> it = party.iterator();
        int i = 1;

        while (it.hasNext()){
            AbstractCreature abstractCreature = it.next();
            if (i == 1){
                if (abstractCreature instanceof Hero){
                    System.out.println("    "+ Utility.paddedString(Hero.header));
                }
                else {
                    System.out.println("    "+Utility.paddedString(Monster.header));
                }
            }
            System.out.println("[" + i + "] " + Utility.paddedString(abstractCreature.seekInformation()));
            i++;
        }

        System.out.println();
    }
}
