package utility;

import creature.CreatureAttributes;
import product.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StandardOutput {
    private static final String[] productsHeader = new String[]{
            "Name",
            "Level",
            "Price",
            "Product Code"
    };
    private static final int columnLength = 22;

    public static void showArmorProducts(List<Product> armors) {
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

    public static void showSpellProducts(List<Product> spells) {
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

    public static void showWeaponProducts(List<Product> weapons) {
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

    public static void showPotionProducts(List<Product> potions) {
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
}
