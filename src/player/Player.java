package player;

import creature.Creature;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Player for the game
 * For our case it is a list of creatures
 */
public class Player implements Iterable<Creature>{
    private final ArrayList<Creature> creatures;
    private final String name;

    /**
     * Constructor
     * @param name name of the player
     */
    public Player(String name){
        this.name = name;
        this.creatures = new ArrayList<>();
    }

    /**
     * Size of list of creature we have in this player
     * @return int
     */
    public int size(){
        return this.creatures.size();
    }

    /**
     * Getter for list of creatures
     * @return array list
     */
    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    /**
     * get the max level of the players
     * @return integer
     */
    public int getMaxLevel(){
        int maxLevel = 1;

        for (Creature creature : creatures){
            if (creature.getLevel() > maxLevel){
                maxLevel = creature.getLevel();
            }
        }
        return maxLevel;
    }

    /**
     * Get the name of the player
     * @return string
     */
    public String getName() {
        return name;
    }

    /**
     * Add new creature to this player
     * @param creature creature to be added
     */
    public void addCreature(Creature creature){
        creatures.add(creature);
    }

    /**
     * iterator to iterate over the creatures
     * @return iterator over creature
     */
    @Override
    public Iterator<Creature> iterator() {
        return creatures.iterator();
    }

    /**
     * Remove creature from the list of creatures
     * @param creature creature to be removed
     */
    public void removeCreature(Creature creature){
        creatures.remove(creature);
    }
}
