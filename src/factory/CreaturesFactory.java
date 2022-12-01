package factory;

import creature.*;

import java.util.ArrayList;

/**
 * This factory class is used by the client to get the heroes and monsters
 */
public class CreaturesFactory {
    private static final CreatureFactory creatureFactory = new CreatureFactory();

    /**
     * getPaladins pull all the paladins and return
     * @return ArrayList of heroes
     */
    public ArrayList<Hero> getPaladins(){
        return creatureFactory.getPaladins();
    }

    /**
     * getWarriors pull all the warriors and return
     * @return ArrayList of heroes
     */
    public ArrayList<Hero> getWarriors(){
        return creatureFactory.getWarriors();
    }

    /**
     * getSorcerers pull all the sorcerers and return
     * @return ArrayList of heroes
     */
    public ArrayList<Hero> getSorcerers(){
        return creatureFactory.getSorcerers();
    }

    /**
     * createMonster create few monster randomly and return
     * @return ArrayList of monsters
     */
    public ArrayList<Monster> createMonsters(int size, int level){
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < size; i++){
            monsters.add(creatureFactory.createMonster(level));
        }
        return monsters;
    }
}
