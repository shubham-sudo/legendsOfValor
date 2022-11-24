package creature;


/**
 * Paladins is a type of Hero with more strength and agility
 */
public class Paladins extends Hero{
    private final double LEVEL_UP_FACTOR = 0.05;    // TODO: (shubham) pick this up from config files

    /**
     * Creates a new Paladin
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
    public Paladins(String name, double health, double baseMana, double strength, double agility, int hands, double dexterity, double experience) {
        super(name, health, baseMana, strength, agility, hands, dexterity, experience);
    }

    /**
     * Level up favoured skills of paladins on level up
     */
    @Override
    protected void levelUpFavouredSkills() {
        increaseStrength(strength + (strength * LEVEL_UP_FACTOR));
        increaseAgility(agility + (agility * LEVEL_UP_FACTOR));
    }
}
