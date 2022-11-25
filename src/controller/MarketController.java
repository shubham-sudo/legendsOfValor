package controller;

import creature.Hero;
import factory.MarketFactory;
import inventory.Inventory;
import market.Market;
import move.MarketMove;
import product.Product;
import product.ProductType;


/**
 * Market is another small mini-game which implements GameController
*/
public class MarketController implements GameController {
    private static final double SELLING_FACTOR = 0.5;
    private final MarketFactory marketFactory;
    private Market market;
    private Hero customer;

    /**
     * Market Controller to interact with Customer
     */
    public MarketController(){
        this.marketFactory = new MarketFactory();
    }

    /**
     * Set new Customer for the market
     * @param customer hero who want to enter into market
     */
    public void setNewCustomer(Hero customer) {
        this.customer = customer;
    }

    /**
     * get move of the hero to buy or sell
     * @return MarketMove given by hero
     */
    private MarketMove getMove(){
        System.out.println();
        System.out.println("Enter your move : [BUY/SELL/SHOW/EXIT]");
        MarketMove marketMove;

        while (true){
            try {
                marketMove = MarketMove.valueOf(getStringFromUser().toUpperCase());
            } catch (Exception e){
                System.out.println("Invalid, Please enter a valid Move!");
                System.out.println("Try again!");
                continue;
            }
            break;
        }
        return marketMove;
    }

    /**
     * get product type move of the hero for INFO move
     * @return MarketMove given by hero
     */
    private ProductType getType(){
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
     * Check if Hero can buy this product
     * @param product product to buy
     * @return true if possible, false otherwise
     */
    private boolean isSafeToBuy(Product product) {
        return customer.getLevel() <= product.getLevel() && customer.getWallet().getGold() <= product.getPrice();
    }

    /**
     * Place order for BUY / SELL of Product
     * @param product Product object to buy or sell
     * @param marketMove market move
     */
    private void placeOrder(Product product, MarketMove marketMove) {
        if (marketMove == MarketMove.BUY){
            customer.getWallet().debit(product.getPrice());
            customer.inventory().addProduct(product);
            market.inventory().removeProduct(product);
        } else if (marketMove == MarketMove.SELL){
            customer.inventory().removeProduct(product);
            market.inventory().addProduct(product);
            customer.getWallet().credit(product.getPrice() * SELLING_FACTOR);
        }
    }

    /**
     * Get Product from Customer to BUY / SELL
     * @return Product object or null
     */
    private Product getProduct(Inventory inventory){
        System.out.println();
        System.out.println("Enter the product_code ['q' to exit the operation]");
        String productCode;

        while (true) {
            productCode = getStringFromUser();
            if (productCode.equals("q")){
                break;
            } else if (!inventory.hasProduct(productCode)){
                System.out.println("INVALID, Please enter a valid product code!");
                System.out.println("Try again!");
            } else {
                break;
            }
        }

        return market.inventory().getProduct(productCode);
    }

    /**
     * Perform the buy or sell for any hero chosen by player
     */
    private void entertainCustomer(){
        MarketMove marketMove;

        while (true){
            marketMove = getMove();

            if (marketMove == MarketMove.SHOW){
                System.out.println();
                market.inventory().show(getType());
            } else if (marketMove == MarketMove.BUY) {
                System.out.println();
                market.inventory().show();
                Product product = getProduct(market.inventory());
                if (product != null) {
                    if (isSafeToBuy(product)){
                        placeOrder(product, marketMove);
                    } else {
                        System.out.println("Sorry, You can't buy " + product);
                    }
                }
            } else if (marketMove == MarketMove.SELL) {
                System.out.println();
                customer.inventory().show();
                Product product = getProduct(customer.inventory());
                if (product != null) {
                    placeOrder(product, marketMove);
                }
            } else if (marketMove == MarketMove.EXIT) {
                break;
            }
        }
    }

    @Override
    public void run() throws IllegalAccessException {
        if (customer != null){
            market = marketFactory.createMarket(1);  // TODO: (shubham) remove level from the market
            entertainCustomer();
        } else {
            throw new IllegalAccessException("No Customer found!");
        }
    }
}