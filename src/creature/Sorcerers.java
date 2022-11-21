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

    @Override
    public void increaseStrength(double bonusStrength) {

    }

    @Override
    public void decreaseStrength(double bonusStrength) {

    }

    @Override
    public void increaseAgility(double bonusAgility) {

    }

    @Override
    public void decreaseAgility(double bonusAgility) {

    }

    @Override
    public void increaseDexterity(double bonusDexterity) {

    }

    @Override
    public void decreaseDexterity(double bonusDexterity) {

    }

    @Override
    public String displayValue() {
        return null;
    }

    @Override
    public boolean typeEquals(Creature creature) {
        return false;
    }
}
