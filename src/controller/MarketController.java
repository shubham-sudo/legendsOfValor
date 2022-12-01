package controller;

import creature.Hero;
import factory.MarketFactory;
import market.Market;
import move.MarketMove;
import product.Product;
import product.ProductType;


/**
 * Market is another small mini-game which implements GameController
*/
public class MarketController implements GameController, ProductController {
    private static final double HERO_SELLING_FACTOR = 0.5;
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
     * Check if Hero can buy this product
     * @param product product to buy
     * @return true if possible, false otherwise
     */
    private boolean isSafeToBuy(Product product) {
        return customer.getLevel() >= product.getLevel() && customer.getWallet().getGold() >= product.getPrice();
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
            customer.getWallet().credit(product.getPrice() * HERO_SELLING_FACTOR);
        }
        System.out.println("\n \t\t\t\t######## " + customer.getName() + " inventory updated!!! ########");
        customer.inventory().show(ProductType.valueOf(product.getClass().getSimpleName().toUpperCase()));
    }

    /**
     * Perform the buy or sell for any hero chosen by player
     */
    private void entertainCustomer(){
        System.out.println("\n######## Welcome to " + market.getName() + "!!! ########");
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

    /**
     * Run the market once customer is set to buy & sell
     * @throws IllegalAccessException if customer not found
     */
    @Override
    public void run() throws IllegalAccessException {
        if (customer != null){
            market = marketFactory.createMarket();
            entertainCustomer();
        } else {
            throw new IllegalAccessException("No Customer found!");
        }
    }
}
