package creature;

/**
 * Warriors is a type of hero with more strength and dexterity
 */
public class Warriors extends Hero{

    /**
     * Creates a new sorcerers
     * @param name name of the hero
     * @param health starting health
     * @param gold starting gold
     */
    public Warriors(String name, int health, float gold) {
        super(name, health, gold);
    }

    /**
     * @see AbstractCreature#levelUpFavouredSkills()
     */
    @Override
    public void levelUpFavouredSkills() {
        this.setStrength((int)(this.getStrength() + this.getStrength() * 0.05));
        this.setDexterity((int)(this.getDexterity() + this.getDexterity() * 0.05));
    }
}
