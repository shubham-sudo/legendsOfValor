package creature;

/**
 * Sorcerers is a type of hero with more dexterity and agility
 */
public class Sorcerers extends Hero{

    /**
     * Creates a new sorcerers
     * @param name name of the hero
     * @param health starting health
     * @param gold starting gold
     */
    public Sorcerers(String name, int health, float gold) {
        super(name, health, gold);
    }

    /**
     * @see AbstractCreature#levelUpFavouredSkills()
     */
    @Override
    public void levelUpFavouredSkills() {
        this.setDexterity((int)(this.getDexterity() + this.getDexterity() * 0.05));
        this.setAgility((int)(this.getAgility() + this.getAgility() * 0.05));
    }
}
