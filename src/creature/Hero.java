package creature;

import inventory.*;
import product.*;
import wallet.Wallet;

import java.util.*;

/**
 * Abstract base class for all type of heroes
 */
public abstract class Hero extends AbstractCreature {
    private static int ID = 0;
    private int id;   // TODO: (shubham) this should be final, fix the createCreature factory
    private final double baseMana;
    private final Wallet wallet;
    private final Inventory inventory;
    private final ArrayList<Weapon> inHandWeapons;
    protected double dexterity;
    private int hands;
    private double health;
    private double mana;
    private double defence;
    private double experience;
    private Armor armor;

    /**
     * Creates and object of AbstractCreature class
     *
     * @param name   name of the creature
     * @param health starting health of the creature
     */
    public Hero(String name, double health, double baseMana, double strength, double agility, int hands, double dexterity, double experience) {
        super(name, health, strength, agility);
        this.id = ++ID;
        this.hands = hands;
        this.health = health;
        this.mana = baseMana;
        this.baseMana = baseMana;
        this.defence = strength * 0.10;
        this.dexterity = dexterity;
        this.experience = experience;
        this.wallet = new Wallet();
        this.inventory = new CreatureInventory();
        this.inHandWeapons = new ArrayList<>();
    }

    /**
     * Get the experience of this creature
     *
     * @see Creature#getExperience()
     * @return double
     */
    @Override
    public double getExperience() {
        return experience;
    }

    /**
     * Get weapons this creature have in hand
     * @return array list of weapons
     */
    public ArrayList<Weapon> getInHandWeapons() {
        return inHandWeapons;
    }

    /**
     * Get health of the hero
     * @return double
     */
    public double getHealth() {
        return this.health;
    }

    /**
     * Get the damage hero can make
     *
     * @see Creature#getDamage()
     * @return double
     */
    @Override
    public double getDamage() {
        return this.strength * 0.60;
    }

    /**
     * Get defence of the hero towards attacks
     *
     * @see Creature#getDefence()
     * @return double
     */
    @Override
    public double getDefence() {
        return this.defence;
    }

    /**
     * Get dexterity of the hero
     *
     * @see Creature#getDexterity()
     * @return double
     */
    @Override
    public double getDexterity(){
        return this.dexterity;
    }

    /**
     * Set the id of the creature
     *
     * @see Creature#setId(int)
     * @param id id to be set
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Decrease damage to the hero
     *
     * @see Creature#decreaseDamage(double)
     * @param damage double
     */
    @Override
    public void decreaseDamage(double damage) {
        decreaseStrength(damage);
    }

    /**
     * Decrease defence of this hero
     *
     * @see Creature#decreaseDefence(double)
     * @param defence double
     */
    @Override
    public void decreaseDefence(double defence) {
        this.defence = (this.defence - defence) < 0 ? 0 : (this.defence - defence);
    }

    /**
     * Decrease dexterity of the hero
     *
     * @see Creature#decreaseDexterity(double)
     * @param dexterity double
     */
    @Override
    public void decreaseDexterity(double dexterity){
        this.dexterity -= Math.max((this.dexterity - dexterity), 0);
    }

    /**
     * Decrease health of this hero
     *
     * @see Creature#decreaseHealth(double)
     * @param health double
     */
    @Override
    public void decreaseHealth(double health) {
        this.health = Math.max((this.health - health), 0);
        if (this.health <= 0) {
            this.health = 0;
            this.setDead();
        }
    }

    /**
     * Decrease mana of the hero
     * @param mana double
     */
    public void decreaseMana(double mana) {
        this.mana = Math.max((this.mana - mana), 0);
    }

    /**
     * Display value for board
     * @return string
     */
    @Override
    public String displayValue() {
        return "H"+id;
    }

    /**
     * Remove armor may be you want to replace with another
     */
    public void dropArmor(Armor armor) throws IllegalAccessException{
        if (this.armor == armor){
            this.armor = null;
            this.inventory.addProduct(armor);
        } else {
            throw new IllegalAccessException("Illegal armor, Not in Use");
        }
    }

    /**
     * drop weapon may be you want to replace
     * @param weapon product
     */
    public void dropWeapon(Weapon weapon) throws IllegalAccessException{
        if (inHandWeapons.contains(weapon)){
            inHandWeapons.remove(weapon);
            this.hands += weapon.getRequiredHands();
            this.inventory.addProduct(weapon);
        } else {
            throw new IllegalAccessException("Illegal weapon, Not in Hand");
        }
    }

    /**
     * Put on some armor
     * @param armor Product
     */
    public void equipArmor(Armor armor){
        this.armor = armor;
        this.inventory.removeProduct(armor);
    }

    /**
     * equip weapon if you have some free hands
     * @param weapon product
     */
    public void equipWeapon(Weapon weapon) throws IllegalAccessException{
        if (this.getFreeHands() >= weapon.getRequiredHands()){
            inHandWeapons.add(weapon);
            this.hands -= weapon.getRequiredHands();
            this.inventory.removeProduct(weapon);
        } else {
            throw new IllegalAccessException("No Free Hands!");
        }
    }

    /**
     * Gain experience on monster kill and
     * levelUp if experience reached to current_level * 10
     *
     * @param exp experience to add
     */
    public void gainExperience(double exp){
        this.experience += exp;
        if (this.experience >= this.level * 10){
            levelUp();
        }
    }

    /**
     * Gain some gold after every monster kill
     * @param gold double value
     */
    public void gainGold(double gold){
        this.wallet.credit(gold);
    }

    /**
     * Get armor currently using
     * @return Armor
     */
    public Armor getArmor(){
        return this.armor;
    }

    /**
     * Getter for getting number of free hands
     * @return integer
     */
    public int getFreeHands(){
        return this.hands;
    }

    /**
     * Get the inventory of this hero
     * @return Inventory object
     */
    public Inventory inventory(){
        return this.inventory;
    }

    /**
     * Get the current mana of hero
     * @return double
     */
    public double getMana() {
        return mana;
    }

    /**
     * Get the Wallet of the hero
     * @return Wallet
     */
    public Wallet getWallet(){
        return this.wallet;
    }

    /**
     * heal the skill of the creature using potion
     * @param potion potion is product used for healing
     */
    protected void heal(Potion potion){
        for (CreatureAttributes attribute : potion.getAttributes()) {
            switch (attribute) {
                case HEALTH:
                    increaseHealth(potion.getHealValue());
                    break;
                case MANA:
                    increaseMana(potion.getHealValue());
                    break;
                case STRENGTH:
                    increaseStrength(potion.getHealValue());
                    break;
                case DEXTERITY:
                    increaseDexterity(potion.getHealValue());
                    break;
                case AGILITY:
                    increaseAgility(potion.getHealValue());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Increase the damage hero can make
     *
     * @see Creature#increaseDamage(double)
     * @param damage double
     */
    @Override
    public void increaseDamage(double damage) {
        increaseStrength(damage);
    }

    /**
     * Increase the defence for this hero
     *
     * @see Creature#increaseDefence(double)
     * @param defence double
     */
    @Override
    public void increaseDefence(double defence) {
        this.defence += defence;
    }

    /**
     * Increase the dexterity of hero
     *
     * @see Creature#decreaseDexterity(double)
     * @param dexterity double
     */
    @Override
    public void increaseDexterity(double dexterity){
        this.dexterity += dexterity;
    }

    /**
     * Increase health for this hero
     *
     * @see Creature#increaseHealth(double)
     * @param health double
     */
    @Override
    public void increaseHealth(double health) {
        this.health += health;
    }

    /**
     * Increase mana of this hero
     * @param mana double
     */
    private void increaseMana(double mana){
        this.mana += mana;
    }

    /**
     * Level up the hero when hero gains
     * experience = current_level * 10
     */
    @Override
    protected void levelUp(){
        super.levelUp();
        this.health = this.baseHealth;
        this.mana *= 1.1;
        levelUpFavouredSkills();
    }

    /**
     * Abstract method for leveling up the favoured skills
     */
    protected abstract void levelUpFavouredSkills();

    /**
     * Revive hero with half of the base health and mana
     */
    public void revive(){
        super.revive();
        this.health = this.baseHealth * 0.5;
        this.mana = this.baseMana * 0.5;
    }

    /**
     * regain some health and mana after every round
     */
    public  void regain(){
        this.health *= 1.1;
        this.mana *= 1.1;
    }

    /**
     * Check for the type of creature
     *
     * @see Creature#typeEquals(Creature)
     * @param creature creature
     * @return true or false
     */
    @Override
    public boolean typeEquals(Creature creature) {
        return creature instanceof Hero;
    }

    /**
     * use mana when cast a spell in attack
     * @param mana mana to reduce
     */
    public void useMana(double mana) throws IllegalAccessException {
        if (this.mana >= mana){
            this.mana -= mana;
        }
        throw new IllegalAccessException("Not Enough Mana!");
    }

    /**
     * use potion to heal up
     * @param potion potion product
     */
    public void usePotion(Potion potion){
        heal(potion);
        if (inventory.hasProduct(potion.getProductCode())) {
            this.inventory.removeProduct(potion);
        }
    }
}