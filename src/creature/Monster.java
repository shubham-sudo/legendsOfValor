package creature;

import battle.CreatureBattleMove;
import product.Armor;
import product.Weapon;
import utility.Utility;

/**
 * Parent class for all monsters in the Game
 */
public class Monster extends AbstractCreature {
    public static final String[] header = new String[]{
            "MONSTER Name",
            "HP",
            "DAMAGE",
            "DEFENCE",
            "DODGE"
    };
    private int damage;
    private int defence;
    private int dodge;

    /**
     * Creates a new monster with name and hp
     * @param name name of monster
     * @param hp health of monster
     */
    public Monster(String name, int hp) {
        super(name, hp);
    }

    /**
     * Getter for the monster damage
     * @return integer
     */
    public int getDamage() {
        return damage;
    }

    /**
     * setter for the monster damage
     * @param damage integer
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * setter for the Defence
     * @param defence integer
     */
    public void setDefence(int defence) {
        this.defence = defence;
    }

    /**
     * Setter for the Dodge of monster
     * @param dodge integer
     */
    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    /**
     * @see AbstractCreature#seekInformation()
     * @return string array
     */
    @Override
    public String[] seekInformation() {
        return new String[]{
                this.name,
                String.valueOf(this.currentHealth),
                String.valueOf(this.damage),
                String.valueOf(this.defence),
                String.valueOf(this.dodge),
        };
    }

    /**
     * @see AbstractCreature#getAttackImpact(CreatureBattleMove)
     * @param creatureBattleMove based on the ATTACK OR CAST
     * @return
     */
    @Override
    public int getAttackImpact(CreatureBattleMove creatureBattleMove) {
        return this.damage;
    }

    /**
     * @see AbstractCreature#updateAttackImpact(int)
     * @param damage integer
     */
    @Override
    public void updateAttackImpact(int damage) {
        if (Utility.rollDice() > (this.dodge * 100)){
            System.out.println(""+ this.getName() + " Dodge the attack!!");
        } else {
            if (this.isAlive()){
                System.out.println(""+this.getName()+" can defence damage upto "+defence);
                this.currentHealth -= Math.max((damage - defence), 0);
                this.currentHealth = Math.max(this.currentHealth, 0);
                if (this.currentHealth == 0){
                    this.setDead();
                }
            }
        }
    }

    /**
     * @see AbstractCreature#levelUpFavouredSkills()
     */
    @Override
    public void levelUpFavouredSkills() {
        // increase the level of monster favoured skills
    }

    /**
     * @see AbstractCreature#regain()
     */
    @Override
    public void regain() {
        this.health += this.health * 0.5;
    }

    /**
     * @see AbstractCreature#getMana()
     * @return float value
     */
    @Override
    public float getMana() {
        return 0;
    }

    /**
     * @see Creature#isArmorOn()
     * @return boolean false always in this case
     */
    @Override
    public boolean isArmorOn() {
        return false;
    }

    /**
     * @see Creature#setArmorOn(Armor)
     * @param armor Product
     */
    @Override
    public void setArmorOn(Armor armor) {

    }

    /**
     * @see Creature#removeArmor()
     */
    @Override
    public void removeArmor() {

    }

    /**
     * @see Creature#getFreeHands()
     * @return integer number
     */
    @Override
    public int getFreeHands() {
        return 0;
    }

    /**
     * @see Creature#getBusyHands()
     * @return integer value
     */
    @Override
    public int getBusyHands() {
        return 0;
    }

    /**
     * @see Creature#notifyObservers()
     */
    @Override
    public void notifyObservers() {
    }

    /**
     * @see Creature#reduceMana(float)
     * @param mana mana to reduce
     */
    @Override
    public void reduceMana(float mana) {
    }

    /**
     * @see Creature#reduceDamage(float)
     * @param damage number of damage to reduce
     */
    @Override
    public void reduceDamage(float damage) {
        this.damage -= damage;
        if (this.damage < 0) this.damage = 0;
    }

    /**
     * @see Creature#reduceDefence(float)
     * @param defence number
     */
    @Override
    public void reduceDefence(float defence) {
        this.defence -= defence;
        if (this.defence < 0) this.defence = 0;
    }

    /**
     * @see Creature#reduceAgility(float)
     * dodge in this case
     * @param agility float number
     */
    @Override
    public void reduceAgility(float agility) {
        this.dodge -= agility;
        if (this.dodge < 0) this.dodge = 0;
    }

    /**
     * @see Creature#improveDefence(float)
     * @param defence float number
     */
    @Override
    public void improveDefence(float defence) {
        this.defence += defence;
    }

    /**
     * @see Creature#tryEquipWeapon(Weapon)
     * @param weapon product
     * @return false always
     */
    @Override
    public boolean tryEquipWeapon(Weapon weapon) {
        return false;
    }

    /**
     * @see Creature#removeWeapon(Weapon)
     * @param weapon product
     * @return false always
     */
    @Override
    public boolean removeWeapon(Weapon weapon) {
        return false;
    }

    /**
     * @see Creature#improveDamage(float)
     * @param damage number of points to increase
     */
    @Override
    public void improveDamage(float damage) {
        this.damage += damage;
    }
}
