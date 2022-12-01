package creature;

/**
 * Sorcerers is a type of hero with more dexterity and agility
 */
public class Sorcerers extends Hero{
    private final double LEVEL_UP_FACTOR = 0.05;

    /**
     * Creates a new sorcerers
     *
     * @param name       name of the creature
     * @param health     starting health of the creature
     * @param baseMana   starting mana of the creature
     * @param strength   strength to damage for creature
     * @param agility    agility to dodge for creature
     * @param hands      number of hands for creature
     * @param dexterity  dexterity to cast spell
     * @param experience starting experience for creature
     */
    public Sorcerers(String name, double health, double baseMana, double strength, double agility, int hands, double dexterity, double experience) {
        super(name, health, baseMana, strength, agility, hands, dexterity, experience);
    }

    /**
     * Level up favoured skills of paladins on level up
     */
    @Override
    public void levelUpFavouredSkills() {
        increaseDexterity(dexterity + (dexterity * LEVEL_UP_FACTOR));
        increaseAgility(agility + (agility * LEVEL_UP_FACTOR));
    }
}
