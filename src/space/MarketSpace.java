package space;

import creature.AbstractCreature;
import creature.Hero;
import factory.MarketFactory;
import market.Market;

import java.util.ArrayList;

/**
 * Market space on map
 */
public class MarketSpace implements Space {
    private final MarketFactory marketFactory = new MarketFactory();

    /**
     * Return true if this space is accessible
     * @see Space#isAccessible()
     * @return boolean
     */
    @Override
    public boolean isAccessible() {
        return true;
    }

    /**
     * Get the highest level of the party to filter product of market
     * @param party party trying to get into market
     * @return integer
     */
    private int getPartyLevel(ArrayList<Hero> party){
        int maxLevel = 0;
        for (AbstractCreature hero : party) {
            if (maxLevel < hero.getLevel()) {
                maxLevel = hero.getLevel();
            }
        }
        return maxLevel;
    }

    /**
     * Occupy this space if accessible
     * @see Space#occupy(ArrayList)
     * @param party party to access this space
     */
    @Override
    public void occupy(ArrayList<Hero> party) {
        System.out.println();
        Market market = marketFactory.createMarket(getPartyLevel(party));
        market.setCustomer(party);
        System.out.println("\t\t\t\t********Welcome to the "+ market.getName()+" Market!!!********");
        System.out.println("Enter 'm' to enter into market!");
        market.run();
    }

    /**
     * to string for this space
     * @return string
     */
    @Override
    public String toString() {
        return "M";
    }
}
