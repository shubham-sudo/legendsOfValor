package wallet;

/**
 * Wallet class gives the feature of wallet to the creature
 */
public class Wallet {
    private float gold;

    /**
     * creates a new wallet with given starting gold
     * @param gold given gold
     */
    public Wallet(float gold){
        this.gold = gold > 0 ? gold : 0;
    }

    /**
     * Creates a new wallet without gold
     * @return float
     */
    public float getGold() {
        return gold;
    }

    /**
     * credit the gold into the wallet
     * @param gold float value
     */
    public void credit(float gold){
        if (gold < 0){
            throw new IllegalArgumentException("Value can't be negative");
        }
        this.gold += gold;
    }

    /**
     * debit the gold from the wallet
     * @param gold float value
     */
    public void debit(float gold){
        if (gold < 0){
            throw new IllegalArgumentException("Value can't be negative");
        }
        this.gold -= gold;
    }
}
