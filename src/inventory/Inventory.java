package inventory;

import product.Product;
import product.ProductType;

import java.util.*;

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
     * get product from the inventory if exists using product code
     * @param productCode product code of the product to fetch
     * @return product or null
     */
    public Product getProduct(String productCode){
        Product product = null;
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
     * Show method shows the inventory products
     */
    public abstract void show();

    /**
     * Show specific products from inventory based on the given product type
     * @param productType type of the product
     */
    public abstract void showSpecificProducts(ProductType productType);

    /**
     * Consume the product if it is consumable
     * Count would be reduced from the inventory for these products
     *
     * @param product product
     */
    public void consumeProduct(Product product){
        removeProduct(product);
    }
}


