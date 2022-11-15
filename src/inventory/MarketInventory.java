package inventory;

import product.Product;
import product.ProductType;
import utility.Utility;

import java.util.*;

/**
 * MarketInventory is the child class of Inventory for markets
 */
public class MarketInventory extends Inventory {

    /**
     * Shows the products present in the inventory of the market
     * @see Inventory#show()
     */
    @Override
    public void show() {
        System.out.println(Utility.paddedString(Product.tableHeader));
        Iterator<Product> productsIterator = this.productsIterator();

        int i = 1;
        while (productsIterator.hasNext()){
            Product product = productsIterator.next();
            System.out.println(Utility.paddedString(product.getInfo()));
            i++;
        }
        System.out.println();
    }

    /**
     * Shows the specific products from the market
     * @param productType type of the product
     */
    @Override
    public void showSpecificProducts(ProductType productType) {
        // Can be used for showing filtered products from the market
    }
}
