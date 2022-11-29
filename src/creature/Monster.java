package creature;

/**
 * Parent class for all monsters in the Game
 */
public class Monster extends AbstractCreature {
    private static int ID = 0;
    private final int id;
    private double defence;

    /**
     * Creates a new monster with name and hp
     * @param name name of monster
     * @param health health of monster
     */
    public Monster(String name, int health, double damage, double defence, double dodge) {
        super(name, health, damage, dodge);
        this.id = ++ID;
        this.defence = defence;
    }

    /**
     * Set id of the creature if required
     * this mostly required by the heroes
     * @param id id to set (the display value)
     */
    @Override
    public void setId(int id) {  // TODO: (shubham) remove this once createCreature factory is fixed
    }

    /**
     * Decrease the damage this creature can perform
     * @see Creature#decreaseDamage(double)
     * @param damage damage to perform
     */
    @Override
    public void decreaseDamage(double damage) {
        decreaseStrength(damage);
    }

    /**
     * Decrease the defence of the creature
     * @see Creature#decreaseDefence(double)
     * @param defence defence to reduce
     */
    @Override
    public void decreaseDefence(double defence) {
        this.defence = (this.defence - defence) < 0 ? 0 : (this.defence - defence);
    }

    /**
     * display value on board for this creature
     * @return string
     */
    @Override
    public String displayValue() {
        return "M"+id;
    }

    /**
     * Get the damage this creature can make
     * @return double
     */
    @Override
    public double getDamage() {
        return strength;
    }

    /**
     * Get the defence this creature can do
     * @return double
     */
    @Override
    public double getDefence() {
        return defence;
    }

    /**
     * Increase the damage value of the creature
     * @param damage double
     */
    @Override
    public void increaseDamage(double damage) {
        increaseStrength(damage);
    }

    /**
     * increase the defence value of the creature
     * @param defence double
     */
    @Override
    public void increaseDefence(double defence) {
        this.defence += defence;
    }

    /**
     * check if the creature is of same type or not
     *
     * @see Creature#typeEquals(Creature)
     * @param creature creature object
     * @return true if of same type, false otherwise
     */
    @Override
    public boolean typeEquals(Creature creature) {
        return creature instanceof Monster;
    }
}
