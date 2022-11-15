package market;

import product.*;
import game.Game;
import creature.*;
import inventory.*;
import controller.GameController;

import java.util.*;

/**
 * Market is another small mini-game which extends Game and implements GameController
 */
public class Market extends Game implements GameController {
    private ArrayList<Hero> party;
    private final String name;
    private Hero currentHero;
    private final Inventory inventory;

    /**
     * Creates a new market for the map tile
     * @param name name of the market
     */
    public Market(String name){
        this(name, new ArrayList<>());
    }

    /**
     * Creates the new market for the map tiles
     * @param name using this name
     * @param products add these products by default
     */
    public Market(String name, ArrayList<Product> products){
        this.name = name;
        this.inventory = new MarketInventory();
        this.addProducts(products);
    }

    /**
     * getter for the name of market
     * @return string
     */
    public String getName() {
        return name;
    }

    /**
     * Set current customer for the market
     * @param party ArrayList of heroes
     */
    public void setCustomer(ArrayList<Hero> party) {
        this.party = party;
    }

    /**
     * Add new products to the market
     * @param products products
     */
    public void addProducts(List<Product> products){
        this.inventory.addProducts(products);
    }

    /**
     * place a purchase request for any product
     * @param product product to purchase
     */
    public void purchase(Product product){
        Hero hero = this.currentHero;
        hero.getWallet().debit((int)product.getPrice());
        hero.getInventory().addProduct(product);
        this.inventory.removeProduct(product);
    }

    /**
     * Get creature to make a move (buy/sell) for the market
     * @param party all heroes
     * @return one hero to place a request
     */
    private Hero getCreatureToMakeMove(ArrayList<Hero> party){
        System.out.println("\t\t\t\t******** Choose your hero to BUY/SELL ********");
        this.showInfo(this.party);

        int index = -1;
        while (index <= 0 || index > party.size()){
            index = getIntFromUser();
            if (index <= 0 || index > party.size()){
                System.out.println("INVALID INDEX, Try again!");
            }
        }
        return party.get(index-1);
    }

    /**
     * get move of the hero to buy or sell
     * @return MarketMove given by hero
     */
    private MarketMove getMove(){
        System.out.println();
        System.out.println("Enter your move : [BUY/SELL/INFO/EXIT]");
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
     * Perform the buy or sell for any hero chosen by player
     */
    private void performBuySellOperations(){
        MarketMove marketMove;
        Hero hero = this.currentHero;

        while (true) {
            marketMove = getMove();

            if (marketMove == MarketMove.INFO){
                System.out.println();
                System.out.println("\t\t\t\t******** "+hero.getName()+" Inventory Status ********");
                hero.getInventory().show();
            }
            else if (marketMove == MarketMove.SELL) {
                while(true){
                    System.out.println();
                    hero.getInventory().show();
                    System.out.println();
                    System.out.println("Enter the product_code of Product to SELL ['q' to exit]");
                    String productCode = getStringFromUser();
                    if (productCode.equals("q")){
                        break;
                    }
                    else {
                        Product product = hero.getInventory().getProduct(productCode);
                        if (product == null) {
                            System.out.println("INVALID, Please enter valid Product code!");
                            System.out.println("Should be in your Inventory, Try again!");
                            continue;
                        }
                        hero.getInventory().removeProduct(product);
                        hero.getWallet().credit((int) (product.getPrice() / 2));
                        this.inventory.addProduct(product);
                        System.out.println("" + hero.getName() + " Inventory Updated!!!");
                        hero.getInventory().show();
                        getEnter();
                        break;
                    }
                }
            } else if (marketMove == MarketMove.BUY) {
                while(true){
                    System.out.println();
                    this.inventory.show();
                    System.out.println();
                    System.out.println("Enter the product_code of Product to BUY ['q' to exit]");
                    String productCode = getStringFromUser();
                    if (productCode.equals("q")){
                        break;
                    }
                    else {
                        Product product = this.inventory.getProduct(productCode);
                        if (product == null) {
                            System.out.println("INVALID, Please enter valid Product code!");
                            System.out.println("Should be in market Inventory, Try again!");
                            continue;
                        }
                        if (product.getPrice() > hero.getWallet().getGold()) {
                            System.out.println("SORRY, Can't buy product with price more than " + hero.getWallet().getGold());
                            System.out.println("Choose another!");
                            continue;
                        }
                        if (product.getLevel() > hero.getLevel()){
                            System.out.println("SORRY, Can't buy product with level more than " + hero.getLevel());
                            System.out.println("Choose another!");
                            continue;
                        }
                        this.purchase(product);
                        System.out.println("" + hero.getName() + " Inventory Updated!!!");
                        hero.getInventory().show();
                        getEnter();
                    }
                }
            } else if (marketMove == MarketMove.EXIT) {
                break;
            }
        }
    }

    /**
     * To string for market
     * @return string
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Run the whole market till the hero want ot buy or sell
     */
    @Override
    public void run() {
        char input = this.getCharFromUser();
        if (input == 'm') {
            boolean wantToChange;
            do {
                this.currentHero = getCreatureToMakeMove(party);
                performBuySellOperations();
                System.out.println("Do you want to SELECT another Hero! [YES/yes/y/NO/no/n]");
                wantToChange = getUserYesOrNo();
            } while (wantToChange);
        }
    }
}
