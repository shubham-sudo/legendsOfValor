package inventory;

import product.*;
import utility.Utility;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Child class of the Inventory to define different function for the creature
 */
public class CreatureInventory extends Inventory {
    /**
     * This reduces the count of product if consumable and
     * keep the count as one for non-consumable
     * @see Inventory#addProduct(Product)
     * @param product product
     */
    @Override
    public void addProduct(Product product) {
        if (product instanceof Consumable){
            if (productCountMap.containsKey(product.getProductCode())){
                productCountMap.put(product.getProductCode(), productCountMap.get(product.getProductCode()) + 1);
            } else {
                productCountMap.put(product.getProductCode(), 1);
                products.put(product.getProductCode(), product);
            }
        }
        else {
            if (!this.products.containsKey(product.getProductCode())){
                this.products.put(product.getProductCode(), product);
                this.productCountMap.put(product.getProductCode(), 1);
            }
        }
    }

    /**
     * Shows the creature inventory in the console
     */
    @Override
    public void show() {
        String[] creatureInventoryHeader = new String[Product.tableHeader.length + 1];
        System.arraycopy(Product.tableHeader, 0, creatureInventoryHeader, 0, Product.tableHeader.length);
        creatureInventoryHeader[Product.tableHeader.length] = "Quantity";
        System.out.println(Utility.paddedString(creatureInventoryHeader));
        Iterator<Product> it = this.productsIterator();

        while (it.hasNext()){
            Product product = it.next();
            String[] productInfo = product.getInfo();
            String[] strings = new String[productInfo.length + 1];
            System.arraycopy(productInfo, 0, strings, 0, Math.min(Product.tableHeader.length, productInfo.length));
            strings[strings.length-1] = String.valueOf(this.productCountMap.get(product.getProductCode()));
            System.out.println(Utility.paddedString(strings));
        }
        System.out.println();
    }

    /**
     * shows the filtered products from the inventory
     * @param products products to show
     */
    public void show(ArrayList<Product> products){
        String[] creatureInventoryHeader = new String[Product.tableHeader.length + 1];
        System.arraycopy(Product.tableHeader, 0, creatureInventoryHeader, 0, Product.tableHeader.length);
        creatureInventoryHeader[Product.tableHeader.length] = "Quantity";
        System.out.println(Utility.paddedString(creatureInventoryHeader));

        for (Product product : products) {
            String[] productInfo = product.getInfo();
            String[] strings = new String[productInfo.length + 1];
            System.arraycopy(productInfo, 0, strings, 0, Math.min(Product.tableHeader.length, productInfo.length));
            strings[strings.length - 1] = String.valueOf(this.productCountMap.get(product.getProductCode()));
            System.out.println(Utility.paddedString(strings));
        }
        System.out.println();
    }

    /**
     * Shows the specific type of product from the inventory
     * @param productType type of the product
     */
    public void showSpecificProducts(ProductType productType){
        ArrayList<Product> products = new ArrayList<>();
        switch (productType){
            case ARMOR:
            case WEAPON:
                for (Product product : this.products.values()){
                    if (product instanceof Wearable){
                        products.add(product);
                    }
                }
                break;
            case SPELL:
                for (Product product : this.products.values()){
                    if (product instanceof Spell){
                        products.add(product);
                    }
                }
                break;
            case POTION:
                for (Product product : this.products.values()){
                    if (product instanceof Potion){
                        products.add(product);
                    }
                }
                break;
            default:
                break;
        }
        show(products);
    }
}
