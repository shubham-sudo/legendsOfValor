package creature;

/**
 * Parent class for all monsters in the Game
 */
public class Monster extends AbstractCreature {
    private static int ID = 0;  // TODO: (shubham) think if we can place a icon, instead of 'M'
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

    @Override
    public void setId(int id) {  // TODO: (shubham) remove this once createCreature factory is fixed
    }

    @Override
    public void decreaseDamage(double damage) {
        decreaseStrength(damage);
    }

    @Override
    public void decreaseDefence(double defence) {
        this.defence = (this.defence - defence) < 0 ? 0 : (this.defence - defence);
    }

    @Override
    public String displayValue() {
        return "M"+id;
    }


    @Override
    public double getDamage() {
        return strength;
    }

    @Override
    public double getDefence() {
        return defence;
    }

    @Override
    public void increaseDamage(double damage) {
        increaseStrength(damage);
    }

    @Override
    public void increaseDefence(double defence) {
        this.defence += defence;
    }

    @Override
    public boolean typeEquals(Creature creature) {
        return creature instanceof Monster;
    }
}
