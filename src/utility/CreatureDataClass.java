package utility;

import java.util.*;

/**
 * DataClass for all creatures, the data is hardcoded here
 * When game run it will pick data from here
 */
public class CreatureDataClass {
    /**
     * Hero creature data holder class
     */
    public static class HeroCreature {
        public final String name;
        public final int hp;
        public final int mp;
        public final int strength;
        public final int dexterity;
        public final int experience;
        public final int agility;
        public final int money;

        public HeroCreature(String name, int mp, int strength, int agility, int dexterity, int startingMoney, int experience) {
            this.name = name;
            this.hp = 100;
            this.mp = mp;
            this.strength = strength;
            this.dexterity = dexterity;
            this.experience = experience;
            this.agility = agility;
            this.money = startingMoney;
        }
    }

    /**
     * Monster holder data class
     */
    public static class MonsterCreature {
        public final String name;
        public final int hp;
        public final int level;
        public final int damage;
        public final int defense;
        public final int dodge;

        public MonsterCreature(String name, int level, int damage, int defense, int dodge) {
            this.name = name;
            this.hp = 100;
            this.level = level;
            this.damage = damage;
            this.defense = defense;
            this.dodge = dodge;
        }
    }

    /**
     * return hero paladins from the hard coded data
     * @return list of heroes
     */
    public static List<HeroCreature> heroPaladins(){
        List<HeroCreature> heroCreatures = new ArrayList<>(paladins());
        Collections.shuffle(heroCreatures);
        return heroCreatures;
    }

    /**
     * return hero warriors from the hard coded data
     * @return list of heroes
     */
    public static List<HeroCreature> heroWarriors(){
        List<HeroCreature> heroCreatures = new ArrayList<>(warriors());
        Collections.shuffle(heroCreatures);
        return heroCreatures;
    }

    /**
     * return hero sorcerers from the hard coded data
     * @return list of heroes
     */
    public static List<HeroCreature> heroSorcerers(){
        List<HeroCreature> heroCreatures = new ArrayList<>(sorcerers());
        Collections.shuffle(heroCreatures);
        return heroCreatures;
    }

    /**
     * return monster creature from the hard coded data
     * @return list of monster
     */
    public static List<MonsterCreature> monsterCreature(){
        List<MonsterCreature> monsterCreatures = new ArrayList<>();
        monsterCreatures.addAll(spirits());
        monsterCreatures.addAll(exoskeletons());
        monsterCreatures.addAll(dragons());
        Collections.shuffle(monsterCreatures);
        return monsterCreatures;
    }

    /**
     * Data holder for warriors
     * @return arraylist
     */
    public static List<HeroCreature> warriors() {
        HeroCreature h1 = new HeroCreature("Gaerdal_Ironhand", 100, 700, 500, 600, 1354, 7);
        HeroCreature h2 = new HeroCreature("Sehanine_Monnbow", 600, 700, 800, 500, 2500, 8);
        HeroCreature h3 = new HeroCreature("Muamman_Duathall", 300, 900, 500, 750, 2546, 6);
        HeroCreature h4 = new HeroCreature("Flandal_Steelskin", 200, 750, 650, 700, 2500, 7);
        HeroCreature h5 = new HeroCreature("Undefeated_Yoj", 400, 800, 400, 700, 2500, 7);
        HeroCreature h6 = new HeroCreature("Eunoia_Cyn", 400, 700, 800, 600, 2500, 6);

        return new ArrayList<>(Arrays.asList(h1, h2, h3, h4, h5, h6));
    }

    /**
     * Data holder for sorcerers
     * @return arraylist
     */

    public static List<HeroCreature> sorcerers() {
        HeroCreature h1 = new HeroCreature("Rillifane_Rallathil", 1300, 750, 450, 500, 2500, 9);
        HeroCreature h2 = new HeroCreature("Segojan_Earthcaller", 900, 800, 500, 650, 2500, 5);
        HeroCreature h3 = new HeroCreature("Reign_Havoc", 800, 800, 800, 800, 2500, 8);
        HeroCreature h4 = new HeroCreature("Reverie_Ashels", 900, 800, 700, 400, 2500, 7);
        HeroCreature h5 = new HeroCreature("Kalabar", 800, 850, 400, 600, 2500, 6);
        HeroCreature h6 = new HeroCreature("Skye_Soar", 1000, 700, 400, 500, 2500, 5);

        return new ArrayList<>(Arrays.asList(h1, h2, h3, h4, h5, h6));
    }

    /**
     * Data holder for paladins
     * @return arraylist
     */
    public static List<HeroCreature> paladins() {
        HeroCreature h1 = new HeroCreature("Parzival", 300, 750, 650, 700, 2500, 7);
        HeroCreature h2 = new HeroCreature("Sehanine_Moonbow", 300, 750, 700, 700, 2500, 7);
        HeroCreature h3 = new HeroCreature("Skoraeus_Stonebones", 250, 650, 600, 350, 2500, 4);
        HeroCreature h4 = new HeroCreature("Garl_Glittergold", 100, 600, 500, 400, 2500, 5);
        HeroCreature h5 = new HeroCreature("Amaryllis_Astra", 500, 500, 500, 500, 2500, 5);
        HeroCreature h6 = new HeroCreature("Caliber_Heist", 400, 400, 400, 400, 2500, 8);

        return new ArrayList<>(Arrays.asList(h1, h2, h3, h4, h5, h6));
    }

    /**
     * Data holder for spirits
     * @return arraylist
     */
    public static List<MonsterCreature> spirits() {
        MonsterCreature m1 = new MonsterCreature("Andrealphus", 2, 600, 500, 40);
        MonsterCreature m2 = new MonsterCreature("Blinky", 1, 450, 350, 35);
        MonsterCreature m3 = new MonsterCreature("Andromalius", 3, 550, 450, 25);
        MonsterCreature m4 = new MonsterCreature("Chiang-shih", 4, 700, 600, 40);
        MonsterCreature m5 = new MonsterCreature("FallenAngel", 5, 800, 700, 50);
        MonsterCreature m6 = new MonsterCreature("Ereshkigall", 6, 950, 450, 35);
        MonsterCreature m7 = new MonsterCreature("Melchiresas", 7, 350, 150, 75);
        MonsterCreature m8 = new MonsterCreature("Jormunngand", 8, 600, 900, 20);
        MonsterCreature m9 = new MonsterCreature("Rakkshasass", 9, 550, 600, 35);
        MonsterCreature m10 = new MonsterCreature("Taltecuhtli", 10, 300, 200, 50);
        MonsterCreature m11 = new MonsterCreature("Casper", 1, 100, 100, 50);

        return new ArrayList<>(Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11));
    }

    /**
     * Data holder for exoskeletons
     * @return arraylist
     */
    public static List<MonsterCreature> exoskeletons() {
        MonsterCreature m1 = new MonsterCreature("Cyrrollalee", 7, 700, 800, 75);
        MonsterCreature m2 = new MonsterCreature("Brandobaris", 3, 350, 450, 30);
        MonsterCreature m3 = new MonsterCreature("BigBad-Wolf", 1, 150, 250, 15);
        MonsterCreature m4 = new MonsterCreature("WickedWitch", 2, 250, 350, 25);
        MonsterCreature m5 = new MonsterCreature("Aasterinian", 4, 400, 500, 45);
        MonsterCreature m6 = new MonsterCreature("Chronepsish", 6, 650, 750, 60);
        MonsterCreature m7 = new MonsterCreature("Kiaransalee", 8, 850, 950, 85);
        MonsterCreature m8 = new MonsterCreature("St-Shargaas", 5, 550, 650, 55);
        MonsterCreature m9 = new MonsterCreature("Merrshaullk", 10, 1000, 600, 55);
        MonsterCreature m10 = new MonsterCreature("St-Yeenoghu", 9, 950, 850, 90);
        MonsterCreature m11 = new MonsterCreature("DocOck", 6, 600, 600, 55);
        MonsterCreature m12 = new MonsterCreature("Exodia", 10, 1000, 1000, 50);

        return new ArrayList<>(Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12));
    }

    /**
     * Data holder for dragons
     * @return arraylist
     */
    public static List<MonsterCreature> dragons() {
        MonsterCreature m1 = new MonsterCreature("Desghidorrah", 3, 300, 400, 35);
        MonsterCreature m2 = new MonsterCreature("Chrysophylax", 2, 200, 500, 20);
        MonsterCreature m3 = new MonsterCreature("BunsenBurner", 4, 400, 500, 45);
        MonsterCreature m4 = new MonsterCreature("Natsunomeryu", 1, 100, 200, 10);
        MonsterCreature m5 = new MonsterCreature("TheScaleless", 7, 700, 600, 75);
        MonsterCreature m6 = new MonsterCreature("Kas-Ethelinh", 5, 600, 500, 60);
        MonsterCreature m7 = new MonsterCreature("Alexstraszan", 10, 1000, 9000, 55);
        MonsterCreature m8 = new MonsterCreature("Phaarthurnax", 6, 600, 700, 60);
        MonsterCreature m9 = new MonsterCreature("D-Maleficent", 9, 900, 950, 85);
        MonsterCreature m10 = new MonsterCreature("TheWeatherbe", 8, 800, 900, 80);
        MonsterCreature m11 = new MonsterCreature("Igneel", 6, 600, 400, 60);
        MonsterCreature m12 = new MonsterCreature("BlueEyesWhite", 9, 900, 600, 75);

        return new ArrayList<>(Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12));
    }
}
