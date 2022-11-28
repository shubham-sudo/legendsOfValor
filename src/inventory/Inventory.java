package inventory;

import product.*;
import utility.StandardOutput;

import java.util.*;
import java.util.stream.Collectors;


/**
 * abstract inventory for Market and Creatures
 */
public abstract class Inventory {
    protected final HashMap<String, Product> products;
    protected final HashMap<String, Integer> productCountMap;

    /**
     * Creates a new inventory
     */
    public Inventory(){
        this.products = new HashMap<>();
        this.productCountMap = new HashMap<>();
    }

    /**
     * added new product into the inventory
     * @param product product
     */
    public void addProduct(Product product){
        if (productCountMap.containsKey(product.getProductCode())){
            productCountMap.put(product.getProductCode(), productCountMap.get(product.getProductCode()) + 1);
        }
        else {
            this.products.put(product.getProductCode(), product);
            this.productCountMap.put(product.getProductCode(), 1);
        }
    }

    /**
     * add a list of products into the inventory
     * @param products List of products
     */
    public void addProducts(List<Product> products){
        for (Product product : products){
            addProduct(product);
        }
    }

    /**
     * remove the product from the inventory
     * @param product product to remove
     */
    public void removeProduct(Product product){
        if (productCountMap.containsKey(product.getProductCode())){
            if (productCountMap.get(product.getProductCode()) > 1) {
                productCountMap.put(product.getProductCode(), productCountMap.get(product.getProductCode()) - 1);
            }
            else{
                productCountMap.remove(product.getProductCode());
                products.remove(product.getProductCode());
            }
        }
    }

    /**
     * Check if inventory has the product
     * @param productCode product code
     * @return true if present, false otherwise
     */
    public boolean hasProduct(String productCode) {
        return productCountMap.containsKey(productCode);
    }

    /**
     * get product from the inventory if exists using product code
     * @param productCode product code of the product to fetch
     * @return product or null
     */
    public Product getProduct(String productCode){
        Product product = null;
        if (productCode == null){
            return null;
        }
        if (this.products.containsKey(productCode)){
            product = this.products.get(productCode);
        }
        return product;
    }

    /**
     * Iterator over all the products in the inventory
     * @return Iterator object
     */
    public Iterator<Product> productsIterator() {
        Collection<Product> copyProducts = products.values();
        return copyProducts.iterator();
    }

    /**
     * Shows the products present in the inventory of the market
     */
    public void show() {
        show(ProductType.ARMOR);
        show(ProductType.WEAPON);
        show(ProductType.POTION);
        show(ProductType.SPELL);
    }

    /**
     * Shows the specific products from the market
     * @param type type of the product
     */
    public void show(ProductType type) {
        List<Product> products;

        switch (type){
            case ARMOR:
                products = this.products.values().stream().filter(product -> product instanceof Armor).collect(Collectors.toList());
                StandardOutput.showArmorProducts(products);
                break;
            case WEAPON:
                products = this.products.values().stream().filter(product -> product instanceof Weapon).collect(Collectors.toList());
                StandardOutput.showWeaponProducts(products);
                break;
            case SPELL:
            case ICESPELL:
            case FIRESPELL:
            case LIGHTNINGSPELL:
                products = this.products.values().stream().filter(product -> product instanceof Spell).collect(Collectors.toList());
                StandardOutput.showSpellProducts(products);
                break;
            case POTION:
                products = this.products.values().stream().filter(product -> product instanceof Potion).collect(Collectors.toList());
                StandardOutput.showPotionProducts(products);
                break;
            case ALL:
                show();
                break;
        }
    }

}


