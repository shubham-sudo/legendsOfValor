package factory;

import creature.*;
import utility.CreatureDataClass;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Factory class for creating creatures of different types
 * This class uses DataClass a data source for creating creatures
 */
public class CreatureFactory {
    private static final ProductFactory productFactory = new ProductFactory();

    /**
     * GetPaladins will return all paladins we have in dataclass
     * @return ArrayList of heroes
     */
    public ArrayList<Hero> getPaladins(){
        List<CreatureDataClass.HeroCreature> heroCreatures = CreatureDataClass.heroPaladins();
        ArrayList<Hero> paladinHeros = new ArrayList<>();
        for (CreatureDataClass.HeroCreature heroCreature : heroCreatures){
            Hero hero = new Paladins(heroCreature.name, heroCreature.hp, heroCreature.money);
            hero.setHp(heroCreature.hp);
            hero.setCurrentHp(heroCreature.hp);
            hero.setMp(heroCreature.mp);
            hero.setStrength(heroCreature.strength);
            hero.setDexterity(heroCreature.dexterity);
            hero.setAgility(heroCreature.agility);
            hero.setExperience(heroCreature.experience);
            paladinHeros.add(hero);
        }
        return paladinHeros;
    }

    /**
     * GetSorcerers will return all sorcerers we have in dataclass
     * @return ArrayList of heroes
     */
    public ArrayList<Hero> getSorcerers(){
        List<CreatureDataClass.HeroCreature> heroCreatures = CreatureDataClass.heroSorcerers();
        ArrayList<Hero> sorcerersHeros = new ArrayList<>();
        for (CreatureDataClass.HeroCreature heroCreature : heroCreatures){
            Hero hero = new Sorcerers(heroCreature.name, heroCreature.hp, heroCreature.money);
            hero.setHp(heroCreature.hp);
            hero.setCurrentHp(heroCreature.hp);
            hero.setMp(heroCreature.mp);
            hero.setStrength(heroCreature.strength);
            hero.setDexterity(heroCreature.dexterity);
            hero.setAgility(heroCreature.agility);
            hero.setExperience(heroCreature.experience);
            sorcerersHeros.add(hero);
        }
        return sorcerersHeros;
    }

    /**
     * getWarriors will return all warriors we have in dataclass
     * @return ArrayList of heroes
     */
    public ArrayList<Hero> getWarriors(){
        List<CreatureDataClass.HeroCreature> heroCreatures = CreatureDataClass.heroWarriors();
        ArrayList<Hero> warriorsHeros = new ArrayList<>();
        for (CreatureDataClass.HeroCreature heroCreature : heroCreatures){
            Hero hero = new Warriors(heroCreature.name, heroCreature.hp, heroCreature.money);
            hero.setHp(heroCreature.hp);
            hero.setCurrentHp(heroCreature.hp);
            hero.setMp(heroCreature.mp);
            hero.setStrength(heroCreature.strength);
            hero.setDexterity(heroCreature.dexterity);
            hero.setAgility(heroCreature.agility);
            hero.setExperience(heroCreature.experience);
            warriorsHeros.add(hero);
        }
        return warriorsHeros;
    }

    /**
     * createMonster will return all random monster we have in dataclass
     * @return ArrayList of monsters
     */
    public Monster createMonster(int level){
        List<CreatureDataClass.MonsterCreature> monsterCreatures = CreatureDataClass.monsterCreature().stream().filter(creature -> creature.level <= level).collect(Collectors.toList());
        if (!monsterCreatures.stream().findFirst().isPresent()){
            monsterCreatures = CreatureDataClass.monsterCreature();
        }
        CreatureDataClass.MonsterCreature monsterCreature = monsterCreatures.get(0);
        Monster monster = new Monster(monsterCreature.name, monsterCreature.hp);
        monster.setLevel(monsterCreature.level);
        monster.setHp(monsterCreature.hp);
        monster.setCurrentHp(monsterCreature.hp);
        monster.setDamage(monsterCreature.damage);
        monster.setDefence(monsterCreature.defense);
        monster.setDodge(monsterCreature.dodge);
        return monster;
    }
}
