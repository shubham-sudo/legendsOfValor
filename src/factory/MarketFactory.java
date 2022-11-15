package factory;

import market.Market;
import product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Factory for market to show on market space tile
 */
public class MarketFactory {
    private final String[] marketNames = new String[]{"Bud's Place", "Crimson Castle", "Imperial Outpost", "BatCave", "Next-Gen"};
    private final ProductFactory productFactory = new ProductFactory();

    /**
     * Create a random market and add products into the inventory of the market
     * @param level level of the product this market servers
     * @return Market object
     */
    public Market createMarket(int level){
        Market market = new Market(marketNames[new Random().nextInt(marketNames.length)]);
        List<Product> products = new ArrayList<>();
        products.addAll(productFactory.getWeapons(level));
        products.addAll(productFactory.getArmors(level));
        products.addAll(productFactory.getLightningSpells(level));
        products.addAll(productFactory.getFireSpells(level));
        products.addAll(productFactory.getIceSpells(level));
        products.addAll(productFactory.getPotions(level));
        market.addProducts(products);
        return market;
    }
}
