package utility;

import creature.CreatureAttributes;

import java.util.*;

/**
 * Dataclass for the hard coded products
 * This will be used when game runs
 */
public class ProductDataClass{

    /**
     * Dataclass for Potions
     */
    public static class PotionProduct {
        public final String name;
        public final float cost;
        public final int requiredLevel;
        public final float impactValue;
        public final Set<CreatureAttributes> attributesAffected;
        public PotionProduct(String name, float cost, int level, float impactValue, Set<CreatureAttributes> attributesAffected){
            this.name = name;
            this.cost = cost;
            this.impactValue = impactValue;
            this.requiredLevel = level;
            this.attributesAffected = attributesAffected;
        }

        @Override
        public String toString() {
            return ""+this.name+"\t"+this.cost+"\t"+this.requiredLevel+"\t"+this.impactValue+"\t"+CreatureAttributes.flatAttributes(this.attributesAffected);
        }
    }

    /**
     * Dataclass for Armors
     */
    public static class ArmorProduct {
        public final String name;
        public final float cost;
        public final int requiredLevel;
        public final float impactValue;

        public ArmorProduct(String name, float cost, int level, float impactValue){
            this.name = name;
            this.cost = cost;
            this.requiredLevel = level;
            this.impactValue = impactValue;
        }
        @Override
        public String toString() {
            return ""+this.name+"\t"+this.cost+"\t"+this.requiredLevel+"\t"+this.impactValue;
        }
    }

    /**
     * Dataclass for Spells
     */
    public static class SpellProduct {
        public final String name;
        public final float cost;
        public final int requiredLevel;
        public final float impactValue;
        public final float manaCost;

        public SpellProduct(String name, float cost, int level, float impactValue, float manaCost){
            this.name = name;
            this.cost = cost;
            this.requiredLevel = level;
            this.impactValue = impactValue;
            this.manaCost = manaCost;
        }

        @Override
        public String toString() {
            return ""+this.name+"\t"+this.cost+"\t"+this.requiredLevel+"\t"+this.impactValue+"\t"+this.manaCost;
        }
    }

    /**
     * Dataclass for Weapon
     */
    public static class WeaponProduct {
        public final String name;
        public final float cost;
        public final int requiredLevel;
        public final float impactValue;
        public final float requiredHands;

        public WeaponProduct(String name, float cost, int level, float impactValue, float rh){
            this.name = name;
            this.cost = cost;
            this.requiredLevel = level;
            this.impactValue = impactValue;
            this.requiredHands = rh;
        }

        @Override
        public String toString() {
            return ""+this.name+"\t"+this.cost+"\t"+this.requiredLevel+"\t"+this.impactValue+"\t"+this.requiredHands;
        }
    }

    /**
     * data holder for Potions
     */
    public static List<PotionProduct> potions(){
        PotionProduct p1 = new PotionProduct("Healing_Potion", 250F, 1, 100F, EnumSet.of(CreatureAttributes.HEALTH));
        PotionProduct p2 = new PotionProduct("Strength_Potion", 200F, 1, 75F, EnumSet.of(CreatureAttributes.STRENGTH));
        PotionProduct p3 = new PotionProduct("Magic_Potion", 350F, 2, 100F, EnumSet.of(CreatureAttributes.MANA));
        PotionProduct p4 = new PotionProduct("Luck_Elixir", 500F, 4, 65F, EnumSet.of(CreatureAttributes.AGILITY));
        PotionProduct p5 = new PotionProduct("Mermaid_Tears", 850F, 5, 100F, EnumSet.of(
                CreatureAttributes.HEALTH,
                CreatureAttributes.MANA,
                CreatureAttributes.STRENGTH,
                CreatureAttributes.AGILITY
        ));
        PotionProduct p6 = new PotionProduct("Ambrosia", 1000F, 8, 150F, EnumSet.of(
                CreatureAttributes.HEALTH,
                CreatureAttributes.MANA,
                CreatureAttributes.STRENGTH,
                CreatureAttributes.DEXTERITY,
                CreatureAttributes.AGILITY
        ));
        return new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6));
    }

    /**
     * data holder for armors
     */
    public static List<ArmorProduct> armors(){
        ArmorProduct a1 = new ArmorProduct("Platinum_Shield", 150F, 1, 200F);
        ArmorProduct a2 = new ArmorProduct("Breastplate", 350F, 3, 600F);
        ArmorProduct a3 = new ArmorProduct("Full_Body_Armor", 1000F, 8, 1100F);
        ArmorProduct a4 = new ArmorProduct("Wizard_Shield", 1200F, 10, 1500F);
        ArmorProduct a5 = new ArmorProduct("Guardian_Angel", 1000F, 10, 1000F);
        return new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5));
    }

    /**
     * data holder for lightning spell
     */
    public static List<SpellProduct> lightningSpells(){
        SpellProduct s1 = new SpellProduct("Lightning_Dagger", 400F, 1, 500F, 150F);
        SpellProduct s2 = new SpellProduct("Thunder_Blast", 750F, 4, 950F, 400F);
        SpellProduct s3 = new SpellProduct("Electric_Arrows", 550F, 5, 650F, 200F);
        SpellProduct s4 = new SpellProduct("Spark_Needles", 500F, 2, 600F, 200F);
        return new ArrayList<>(Arrays.asList(s1, s2, s3, s4));
    }

    /**
     * data holder for ice spells
     */
    public static List<SpellProduct> iceSpells(){
        SpellProduct s1 = new SpellProduct("Snow_Cannon", 500F, 2, 650F, 250F);
        SpellProduct s2 = new SpellProduct("Ice_Blade", 250F, 1, 450F, 100F);
        SpellProduct s3 = new SpellProduct("Frost_Blizzard", 750F, 5, 850F, 350F);
        SpellProduct s4 = new SpellProduct("Arctic_Storm", 700F, 6, 800F, 300F);
        return new ArrayList<>(Arrays.asList(s1, s2, s3, s4));
    }

    /**
     * data holder for fire spells
     */
    public static List<SpellProduct> fireSpells(){
        SpellProduct s1 = new SpellProduct("Flame_Tornado", 700F, 4, 850F, 300F);
        SpellProduct s2 = new SpellProduct("Breath_of_Fire", 350F, 1, 450F, 100F);
        SpellProduct s3 = new SpellProduct("Heat_Wave", 450F, 2, 600F, 150F);
        SpellProduct s4 = new SpellProduct("Lava_Comet", 800F, 7, 1000F, 550F);
        SpellProduct s5 = new SpellProduct("Hell_Storm", 600F, 3, 950F, 600F);
        return new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5));
    }

    /**
     * data holder for weapons
     */
    public static List<WeaponProduct> weapons(){
        WeaponProduct w1 = new WeaponProduct("Sword", 500F, 1, 800F, 1);
        WeaponProduct w2 = new WeaponProduct("Bow", 300F, 2, 500F, 2);
        WeaponProduct w3 = new WeaponProduct("Scythe", 1000F, 6, 1100F, 2);
        WeaponProduct w4 = new WeaponProduct("Axe", 550F, 5, 850F, 1);
        WeaponProduct w5 = new WeaponProduct("TSwords", 1400F, 8, 1600F, 2);
        WeaponProduct w6 = new WeaponProduct("Dagger", 200F, 1, 250F, 1);
        return new ArrayList<>(Arrays.asList(w1, w2, w3, w4, w5, w6));
    }
}
