package player;

import creature.Creature;

import java.util.ArrayList;
import java.util.Iterator;

public class Player implements Iterable<Creature>{
    private final ArrayList<Creature> creatures;
    private final String name;

    public Player(String name){
        this.name = name;
        this.creatures = new ArrayList<>();
    }

    public int size(){
        return this.creatures.size();
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public int getMaxLevel(){
        int maxLevel = 1;

        for (Creature creature : creatures){
            if (creature.getLevel() > maxLevel){
                maxLevel = creature.getLevel();
            }
        }
        return maxLevel;
    }

    public String getName() {
        return name;
    }

    public void addCreature(Creature creature){
        creatures.add(creature);
    }

    @Override
    public Iterator<Creature> iterator() {
        return creatures.iterator();
    }

    public void removeCreature(Creature creature){
        creatures.remove(creature);
    }
}
