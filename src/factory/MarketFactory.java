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
     * @return Market object
     */
    public Market createMarket(){
        Market market = new Market(marketNames[new Random().nextInt(marketNames.length)]);
        List<Product> products = new ArrayList<>();
        products.addAll(productFactory.getWeapons());
        products.addAll(productFactory.getArmors());
        products.addAll(productFactory.getLightningSpells());
        products.addAll(productFactory.getFireSpells());
        products.addAll(productFactory.getIceSpells());
        products.addAll(productFactory.getPotions());
        market.addProducts(products);
        return market;
    }
}
