package creature;

/**
 * Paladins is a type of Hero with more strength and agility
 */
public class Paladins extends Hero{
    /**
     * Creates a new Paladin
     * @param name name of hero
     * @param health starting health
     * @param gold starting gold
     */
    public Paladins(String name, int health, float gold) {
        super(name, health, gold);
    }

    /**
     * @see AbstractCreature#levelUpFavouredSkills()
     */
    @Override
    public void levelUpFavouredSkills() {
        this.setStrength((int)(this.getStrength() + this.getStrength() * 0.05));
        this.setAgility((int)(this.getAgility() + this.getAgility() * 0.05));
    }
}
