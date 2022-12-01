package wallet;

/**
 * Wallet class gives the feature of wallet to the creature
 */
public class Wallet {
    private double gold;

    /**
     * creates a new wallet with given starting gold
     * @param gold given gold
     */
    public Wallet(double gold){
        this.gold = gold > 0 ? gold : 0;
    }

    public Wallet(){
        this(0);
    }

    /**
     * Creates a new wallet without gold
     * @return float
     */
    public double getGold() {
        return gold;
    }

    /**
     * credit the gold into the wallet
     * @param gold double value
     */
    public void credit(double gold){
        if (gold < 0){
            throw new IllegalArgumentException("Value can't be negative");
        }
        this.gold += gold;
    }

    /**
     * debit the gold from the wallet
     * @param gold double value
     */
    public void debit(double gold){
        if (gold < 0){
            throw new IllegalArgumentException("Value can't be negative");
        }
        this.gold -= gold;
    }
}
