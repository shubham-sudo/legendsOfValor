package factory;

import product.*;
import utility.ProductDataClass;

import java.util.*;

/**
 * Product factory to create several type of products
 */
public class ProductFactory {

    /**
     * get all weapons with the given level
     * @return List of Products
     */
    public List<Product> getWeapons(){
        List<ProductDataClass.WeaponProduct> weaponProducts = ProductDataClass.weapons();
        List<Product> products = new ArrayList<>();
        weaponProducts.forEach(product -> products.add(new Weapon(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, (int)product.requiredHands)));
        return products;
    }

    /**
     * get all potions with the given level
     * @return List of Products
     */
    public List<Product> getPotions(){
        List<ProductDataClass.PotionProduct> potionProducts = ProductDataClass.potions();
        List<Product> products = new ArrayList<>();
        potionProducts.forEach(product -> products.add(new Potion(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, product.attributesAffected)));
        return products;
    }

    /**
     * get all armors with the given level
     * @return List of Products
     */
    public List<Product> getArmors(){
        List<ProductDataClass.ArmorProduct> armorProducts = ProductDataClass.armors();
        List<Product> products = new ArrayList<>();
        armorProducts.forEach(product -> products.add(new Armor(product.name, product.requiredLevel, product.cost, product.name, product.impactValue)));
        return products;
    }

    /**
     * get all lightning spells with the given level
     * @return List of Products
     */
    public List<Product> getLightningSpells(){
        List<ProductDataClass.SpellProduct> spellProducts = ProductDataClass.lightningSpells();
        List<Product> products = new ArrayList<>();
        spellProducts.forEach(product -> products.add(new LightningSpell(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, product.manaCost)));
        return products;
    }

    /**
     * get all fire spells with the given level
     * @return List of Products
     */
    public List<Product> getFireSpells(){
        List<ProductDataClass.SpellProduct> spellProducts = ProductDataClass.fireSpells();
        List<Product> products = new ArrayList<>();
        spellProducts.forEach(product -> products.add(new FireSpell(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, product.manaCost)));
        return products;
    }

    /**
     * get all ice spells with the given level
     * @return List of Products
     */
    public List<Product> getIceSpells(){
        List<ProductDataClass.SpellProduct> spellProducts = ProductDataClass.iceSpells();
        List<Product> products = new ArrayList<>();
        spellProducts.forEach(product -> products.add(new IceSpell(product.name, product.requiredLevel, product.cost, product.name, product.impactValue, product.manaCost)));
        return products;
    }
}
