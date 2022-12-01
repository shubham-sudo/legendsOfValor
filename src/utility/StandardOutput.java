package utility;

import creature.Creature;
import creature.CreatureAttributes;
import creature.Hero;
import product.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * Standard Output helps to generate nice looking table and aligning stuff in console.
 */
public class StandardOutput {
    private static final String[] productsHeader = new String[]{
            "Name",
            "Level",
            "Price",
            "Product Code"
    };
    private static final String[] heroHeader = new String[]{
            "  Name",
            "Level",
            "Health",
            "Mana",
            "Strength",
            "Agility",
            "Dexterity",
            "Experience",
    };
    private static final String[] monsterHeader = new String[]{
            "  Name",
            "Level",
            "Health",
            "Strength",
            "Agility",
    };

    private static final int columnLength = 22;

    /**
     * Show armors in a table
     * @param armors armors list
     */
    public static void showArmorProducts(List<? extends Product> armors) {
        String[] armorHeader = new String[]{
                "Damage Reduction"
        };
        if (armors.size() == 0){
            System.out.println("\t\t\t\t No armor available");
            return;
        }
        System.out.println("\n \t\t\t\t######## AVAILABLE ARMORS ########");
        System.out.println();
        System.out.println(Utility.getPaddedString(Stream.concat(Arrays.stream(productsHeader), Arrays.stream(armorHeader)).toArray((String[]::new)), columnLength));

        for (Product armor : armors) {
            Armor product = (Armor) armor;
            String[] details = new String[productsHeader.length + armorHeader.length];
            details[0] = product.getName();
            details[1] = String.valueOf(product.getLevel());
            details[2] = String.valueOf(product.getPrice());
            details[3] = product.getProductCode();
            details[4] = String.valueOf(product.getDamageReductionValue());

            System.out.println(Utility.getPaddedString(details, columnLength));
        }
    }

    /**
     * Show spells in a table
     * @param spells list
     */
    public static void showSpellProducts(List<? extends Product> spells) {
        String[] spellHeader = new String[]{
                "Damage Value",
                "Required Mana"
        };
        if (spells.size() == 0){
            System.out.println("\t\t\t\t No spell available");
            return;
        }
        System.out.println("\n \t\t\t\t######## AVAILABLE SPELLS ########");
        System.out.println();
        System.out.println(Utility.getPaddedString(Stream.concat(Arrays.stream(productsHeader), Arrays.stream(spellHeader)).toArray((String[]::new)), columnLength));

        for (Product spell : spells) {
            Spell product = (Spell) spell;
            String[] details = new String[productsHeader.length + spellHeader.length];
            details[0] = product.getName();
            details[1] = String.valueOf(product.getLevel());
            details[2] = String.valueOf(product.getPrice());
            details[3] = product.getProductCode();
            details[4] = String.valueOf(product.getDamageValue());
            details[5] = String.valueOf(product.getRequiredMana());

            System.out.println(Utility.getPaddedString(details, columnLength));
        }
    }

    /**
     * Show weapons in a table
     * @param weapons list
     */
    public static void showWeaponProducts(List<? extends Product> weapons) {
        String[] weaponHeader = new String[]{
                "Damage",
                "Required Hands"
        };
        if (weapons.size() == 0){
            System.out.println("\t\t\t\t No weapon available");
            return;
        }
        System.out.println("\n \t\t\t\t######## AVAILABLE WEAPONS ########");
        System.out.println();
        System.out.println(Utility.getPaddedString(Stream.concat(Arrays.stream(productsHeader), Arrays.stream(weaponHeader)).toArray((String[]::new)), columnLength));

        for (Product weapon : weapons) {
            Weapon product = (Weapon) weapon;
            String[] details = new String[productsHeader.length + weaponHeader.length];
            details[0] = product.getName();
            details[1] = String.valueOf(product.getLevel());
            details[2] = String.valueOf(product.getPrice());
            details[3] = product.getProductCode();
            details[4] = String.valueOf(product.getDamageValue());
            details[5] = String.valueOf(product.getRequiredHands());

            System.out.println(Utility.getPaddedString(details, columnLength));
        }
    }

    /**
     * Show potions in a table
     * @param potions list
     */
    public static void showPotionProducts(List<? extends Product> potions) {
        String[] potionHeader = new String[]{
                "Healing Value",
                "Attributes Affected"
        };
        if (potions.size() == 0){
            System.out.println("\t\t\t\t No potion available");
            return;
        }
        System.out.println("\n \t\t\t\t######## AVAILABLE POTIONS ########");
        System.out.println();
        System.out.println(Utility.getPaddedString(Stream.concat(Arrays.stream(productsHeader), Arrays.stream(potionHeader)).toArray((String[]::new)), columnLength));

        for (Product potion : potions) {
            Potion product = (Potion) potion;
            String[] details = new String[productsHeader.length + potionHeader.length];
            details[0] = product.getName();
            details[1] = String.valueOf(product.getLevel());
            details[2] = String.valueOf(product.getPrice());
            details[3] = product.getProductCode();
            details[4] = String.valueOf(product.getHealValue());
            details[5] = CreatureAttributes.flatAttributes(product.getAttributes());

            System.out.println(Utility.getPaddedString(details, columnLength));
        }
    }

    /**
     * Show creature in a table
     * @param creature creature
     * @param header boolean
     * @param index index to show
     */
    public static void showCreature(Creature creature, boolean header, int index){
        int newColumnLength = 15;
        String[] details = null;
        if (header) {
            if (creature instanceof Hero) {
                System.out.println();
                System.out.println("\t\t######## Hero's ########");
                System.out.println(Utility.getPaddedString(heroHeader, newColumnLength));
                details = getStrings(creature);
            } else {
                System.out.println();
                System.out.println("\t\t######## Monster's ########");
                System.out.println(Utility.getPaddedString(monsterHeader, newColumnLength));
                details = new String[monsterHeader.length];
                details[2] = String.valueOf(creature.getHealth());
                details[3] = String.valueOf(creature.getStrength());
                details[4] = String.valueOf(creature.getAgility());
            }
        } else {
            if (creature instanceof Hero) {
                details = getStrings(creature);
            } else {
                details = new String[monsterHeader.length];
                details[2] = String.valueOf(creature.getHealth());
                details[3] = String.valueOf(creature.getStrength());
                details[4] = String.valueOf(creature.getAgility());
            }
        }

        details[0] = index + " " + creature.getName();
        details[1] = String.valueOf(creature.getLevel());

        System.out.println(Utility.getPaddedString(details, newColumnLength));
    }

    /**
     * helper method for show creatues
     * @param creature creature object
     * @return string array
     */
    private static String[] getStrings(Creature creature) {
        String[] details;
        details = new String[heroHeader.length];
        details[2] = creature.getHealth() + "/" + creature.baseHealth();
        details[3] = String.valueOf(creature.getMana());
        details[4] = String.valueOf(creature.getStrength());
        details[5] = String.valueOf(creature.getAgility());
        details[6] = String.valueOf(creature.getDexterity());
        details[7] = creature.getExperience() + "/" + creature.getLevel() * 10;
        return details;
    }
}
