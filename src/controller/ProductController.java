package controller;

import inventory.Inventory;
import product.Product;
import product.ProductType;

/**
 * Controller which gives all operation to operate on products (Items)
 */
public interface ProductController extends GameInputManager {

    /**
     * get product type move of the hero for INFO move
     * @return MarketMove given by hero
     */
    default ProductType getType(){
        System.out.println();
        System.out.println("Enter your type : [ARMOR/WEAPON/POTION/SPELL/ALL]");
        ProductType productType;

        while (true){
            try {
                productType = ProductType.valueOf(getStringFromUser().toUpperCase());
            } catch (Exception e){
                System.out.println("Invalid, Please enter a valid Type!");
                System.out.println("Try again!");
                continue;
            }
            break;
        }
        return productType;
    }

    /**
     * Get product from the given inventory asking user the product_code
     * @param inventory inventory object
     * @return product object or null
     */
    default Product getProduct(Inventory inventory){
        System.out.println();
        System.out.println("Enter the product_code ['q' to exit the operation]");
        String productCode;

        while (true) {
            productCode = getStringFromUser();
            if (productCode.equals("q")) {
                break;
            } else if (!inventory.hasProduct(productCode)) {
                System.out.println("INVALID, Please enter a valid product code!");
                System.out.println("Try again!");
            } else {
                break;
            }
        }
        return inventory.getProduct(productCode);
    }
}
