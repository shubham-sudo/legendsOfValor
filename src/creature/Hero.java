package creature;

import inventory.*;
import map.Position;
import product.*;
import wallet.Wallet;

import java.util.*;

/**
 * Abstract base class for all type of heroes
 */
public abstract class Hero extends AbstractCreature {
    private static int ID = 0;  // TODO: (shubham) think if we can place a icon, instead of 'H'
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
        this.defence = strength * 0.25;
        this.dexterity = dexterity;
        this.experience = experience;
        this.wallet = new Wallet();
        this.inventory = new CreatureInventory();
        this.inHandWeapons = new ArrayList<>();
    }

    public ArrayList<Weapon> getInHandWeapons() {
        return inHandWeapons;
    }


    @Override
    public double getDamage() {
        return this.strength;
    }

    @Override
    public double getDefence() {
        return this.defence;
    }

    @Override
    public double getDexterity(){
        return this.dexterity;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
    public void decreaseDexterity(double dexterity){
        this.dexterity -= Math.max((this.dexterity - dexterity), 0);
    }

    @Override
    public void decreaseHealth(double health) {
        this.health = Math.max((this.health - health), 0);
    }

    public void decreaseMana(double mana) {
        this.mana = Math.max((this.mana - mana), 0);
    }

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

    public Inventory inventory(){
        return this.inventory;
    }

    public double getMana() {
        return mana;
    }

    public Wallet getWallet(){
        return this.wallet;
    }

    /**
     * heal the skill of the creature using potion
     * @param potion potion is product used for healing
     */
    protected void heal(Potion potion){
        for (CreatureAttributes attribute : potion.getAttributes()){
            switch (attribute){
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
        // TODO: (shubham) remember to notify observer that potion is used
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
    public void increaseDexterity(double dexterity){
        this.dexterity += dexterity;
    }

    @Override
    public void increaseHealth(double health) {
        this.health += health;
    }

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

    protected abstract void levelUpFavouredSkills();

    public void revive(){
        super.revive();
        this.health = this.baseHealth * 0.5;
        this.mana = this.baseMana * 0.5;
    }

    public  void regain(){
        this.health *= 1.1;
        this.mana *= 1.1;
    }

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

    public void usePotion(Potion potion){
        heal(potion);
    }
}