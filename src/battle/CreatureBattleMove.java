package battle;

import product.Product;

/**
 * Creature move for any round of battle
 */
public class CreatureBattleMove {
    private final BattleMove battleMove;
    private final Product product;

    /**
     * Create a new object of creature battle move
     * @param battleMove enum value of the battle move
     * @param product product used for the attack
     */
    public CreatureBattleMove(BattleMove battleMove, Product product) {
        this.battleMove = battleMove;
        this.product = product;
    }

    /**
     * getter for the BattleMove
     * @return
     */
    public BattleMove getMoveType() {
        return battleMove;
    }

    /**
     * getter for the product used for the move
     * @return
     */
    public Product getProduct() {
        return product;
    }
}
