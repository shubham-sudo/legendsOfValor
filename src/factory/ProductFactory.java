package factory;

import product.*;
import utility.ProductDataClass;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Product factory to create several type of products
 */
public class ProductFactory {

    /**
     * get all weapons with the given level
     * @param level level to filter the weapons
     * @return List of Products
     */
    public List<Product> getWeapons(int level){
        List<ProductDataClass.WeaponProduct> weaponProducts = ProductDataClass.weapons().stream().filter(product -> product.requiredLevel <= level).collect(Collectors.toList());
        List<Product> products = new ArrayList<>();
        weaponProducts.forEach(product -> products.add(new Weapon(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, (int)product.requiredHands)));
        return products;
    }

    /**
     * get all potions with the given level
     * @param level level to filter the portion
     * @return List of Products
     */
    public List<Product> getPotions(int level){
        List<ProductDataClass.PotionProduct> potionProducts = ProductDataClass.potions().stream().filter(product -> product.requiredLevel >= level).collect(Collectors.toList());
        List<Product> products = new ArrayList<>();
        potionProducts.forEach(product -> products.add(new Potion(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, product.attributesAffected)));
        return products;
    }

    /**
     * get all armors with the given level
     * @param level level to filter the armors
     * @return List of Products
     */
    public List<Product> getArmors(int level){
        List<ProductDataClass.ArmorProduct> armorProducts = ProductDataClass.armors().stream().filter(product -> product.requiredLevel >= level).collect(Collectors.toList());
        List<Product> products = new ArrayList<>();
        armorProducts.forEach(product -> products.add(new Armor(product.name, product.requiredLevel, product.cost, product.name, product.impactValue)));
        return products;
    }

    /**
     * get all lightning spells with the given level
     * @param level level to filter the spells
     * @return List of Products
     */
    public List<Product> getLightningSpells(int level){
        List<ProductDataClass.SpellProduct> spellProducts = ProductDataClass.lightningSpells().stream().filter(product -> product.requiredLevel >= level).collect(Collectors.toList());
        List<Product> products = new ArrayList<>();
        spellProducts.forEach(product -> products.add(new LightningSpell(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, product.manaCost)));
        return products;
    }

    /**
     * get all fire spells with the given level
     * @param level level to filter the spells
     * @return List of Products
     */
    public List<Product> getFireSpells(int level){
        List<ProductDataClass.SpellProduct> spellProducts = ProductDataClass.fireSpells().stream().filter(product -> product.requiredLevel >= level).collect(Collectors.toList());
        List<Product> products = new ArrayList<>();
        spellProducts.forEach(product -> products.add(new FireSpell(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, product.manaCost)));
        return products;
    }

    /**
     * get all ice spells with the given level
     * @param level level to filter the spells
     * @return List of Products
     */
    public List<Product> getIceSpells(int level){
        List<ProductDataClass.SpellProduct> spellProducts = ProductDataClass.iceSpells().stream().filter(product -> product.requiredLevel >= level).collect(Collectors.toList());
        List<Product> products = new ArrayList<>();
        spellProducts.forEach(product -> products.add(new IceSpell(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, product.manaCost)));
        return products;
    }
}
