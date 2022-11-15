package creature;

import battle.*;
import inventory.*;
import product.*;
import utility.Utility;
import wallet.Wallet;

import java.util.*;

/**
 * Abstract base class for all type of heroes
 */
public abstract class Hero extends AbstractCreature {
    private int freeHands = 2;
    private int busyHands = 0;
    private int mana;
    private int currentMana;
    private int strength;
    private int dexterity;
    private int experience;
    private int agility;
    private final Inventory inventory;
    private final Wallet wallet;
    private Armor currentArmor;
    private final ArrayList<Weapon> currentWeapons;

    public static final String[] header = new String[]{
            "HERO Name",
            "HP",
            "MP",
            "IS ALIVE",
            "STRENGTH",
            "EXPERIENCE",
            "DEXTERITY",
            "AGILITY",
            "In-Hand WEAPON(s)",
            "In-Hand ARMOR",
            "GOLD"
    };

    /**
     * Creates a new hero using below params
     * @param name name of hero
     * @param health starting health
     * @param gold starting gold in wallet
     */
    public Hero(String name, int health, float gold) {
        super(name, health);
        currentWeapons = new ArrayList<>();
        this.inventory = new CreatureInventory();
        this.wallet = new Wallet(gold);
    }

    /**
     * @see Creature#getMana()
     */
    @Override
    public float getMana(){
        return this.currentMana;
    }

    /**
     * @see Creature#isArmorOn()
     */
    @Override
    public boolean isArmorOn(){
        return currentArmor == null;
    }

    /**
     * @see Creature#setArmorOn(Armor)
     * @param armor Product
     */
    @Override
    public void setArmorOn(Armor armor){
        this.inventory.removeProduct(currentArmor);
        this.currentArmor = armor;
    }

    /**
     * @see Creature#removeArmor()
     */
    @Override
    public void removeArmor(){
        if (this.currentArmor != null){
            this.inventory.addProduct(this.currentArmor);
        }
        this.currentArmor = null;
    }

    /**
     * @see Creature#getFreeHands()
     * @return integer
     */
    @Override
    public int getFreeHands(){
        return this.freeHands;
    }

    /**
     * @see Creature#getBusyHands()
     * @return integer
     */
    @Override
    public int getBusyHands(){
        return this.busyHands;
    }

    /**
     * @see Creature#reduceMana(float)
     * @param mana mana to reduce
     */
    @Override
    public void reduceMana(float mana){
        this.currentMana -= mana;
    }

    /**
     * @see Creature#reduceDamage(float)
     * @param damage number of damage to reduce
     */
    @Override
    public void reduceDamage(float damage){
        this.strength -= damage;
    }

    /**
     * @see Creature#reduceDefence(float)
     * @param defence number
     */
    @Override
    public void reduceDefence(float defence){
        this.health -= defence;
    }

    /**
     * @see Creature#reduceAgility(float)
     * @param agility float number
     */
    @Override
    public void reduceAgility(float agility){
        this.agility -= agility;
    }

    /**
     * @see Creature#improveDefence(float)
     * @param defence float number
     */
    @Override
    public void improveDefence(float defence){
        this.health += defence;
    }

    /**
     * @see Creature#improveDamage(float)
     * @param damage number of points to increase
     */
    @Override
    public void improveDamage(float damage){
        this.strength += damage;
    }

    /**
     * @see Creature#tryEquipWeapon(Weapon)
     * @param weapon product
     * @return boolean
     */
    @Override
    public boolean tryEquipWeapon(Weapon weapon){
        if (this.freeHands >= weapon.getRequiredHands()) {
            currentWeapons.add(weapon);
            this.freeHands -= weapon.getRequiredHands();
            this.inventory.removeProduct(weapon);
            return true;
        }
        return false;
    }

    /**
     * @see Creature#removeWeapon(Weapon)
     * @param weapon product
     * @return boolean
     */
    @Override
    public boolean removeWeapon(Weapon weapon){
        if (currentWeapons.contains(weapon)){
            currentWeapons.remove(weapon);
            this.freeHands += weapon.getRequiredHands();
            this.inventory.addProduct(weapon);
            return true;
        }
        return false;
    }

    /**
     * Set the mana while creating new creature
     * @param mp mana value
     */
    public void setMp(int mp) {
        this.mana = mp;
        this.currentMana = mp;
    }

    /**
     * Getter for hero strength
     * @return float value
     */
    public float getStrength() {
        return strength;
    }

    /**
     * Setter for strength
     * @param strength float value
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Getter for dexterity
     * @return float value
     */
    public float getDexterity() {
        return dexterity;
    }

    /**
     * Setter for dexterity
     * @param dexterity float value
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * Getter for experience
     * @return float value
     */
    public float getExperience() {
        return experience;
    }

    /**
     * Setter for experience
     * @param experience integer value
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Getter for agility
     * @return float value
     */
    public float getAgility() {
        return agility;
    }

    /**
     * setter for agility
     * @param agility integer value
     */
    public void setAgility(int agility) {
        this.agility = agility;
    }

    /**
     * Getter for inventory
     * @return Inventory object (CreatureInventory)
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Getter for accessing wallet
     * @return Wallet object
     */
    public Wallet getWallet() {
        return wallet;
    }

    /**
     * @see AbstractCreature#seekInformation()
     * @return String array
     */
    @Override
    public String[] seekInformation() {
        return new String[]{this.name,
                ""+this.currentHealth+"/"+this.health,
                ""+this.currentMana+"/"+this.mana,
                (this.isAlive() ? "YES" : "NO"),
                String.valueOf(this.strength),
                String.valueOf(this.experience),
                String.valueOf(this.dexterity),
                String.valueOf(this.agility),
                (this.getCurrentWeapons() != null ? this.getCurrentWeapons() : ""),
                (this.getCurrentArmor() != null ? this.getCurrentArmor().getName() : ""),
                String.valueOf(this.wallet.getGold())
        };
    }

    /**
     * Get all weapons in hand right now
     * This is for printing stuff in console
     * @return String of all those weapons
     */
    public String getCurrentWeapons(){
        String[] strings = new String[this.currentWeapons.size()];
        for(int i = 0; i < this.currentWeapons.size(); i++){
            strings[i] = this.currentWeapons.get(i).toString();
        }
        return String.join("/", strings);
    }

    /**
     * Get current armor in on Creature
     * This is for printing stuff
     * @return Armor object
     */
    public Armor getCurrentArmor() {
        return currentArmor;
    }

    /**
     * @see AbstractCreature#getAttackImpact(CreatureBattleMove) 
     * @param creatureBattleMove based on the ATTACK OR CAST
     * @return string
     */
    @Override
    public int getAttackImpact(CreatureBattleMove creatureBattleMove) {
        int damage = 0;
        if (currentWeapons.size() == 0){
            damage += this.strength;
        }
        for (Weapon weapon : currentWeapons){
            damage += (int)((this.strength + weapon.getDamageValue()) * 0.5);
        }
        return damage;
    }

    /**
     * @see AbstractCreature#updateAttackImpact(int)
     * @param damage integer
     */
    @Override
    public void updateAttackImpact(int damage) {
        if (Utility.rollDice() > this.agility * 100){
            System.out.println(""+ this.getName() + " Dodge the attack!!");
        } else {
            if (this.isAlive()){
                int damageReduction = 0;
                if (this.currentArmor != null){
                    System.out.println(""+this.currentArmor.getName() + " using "+this.getName()+" Armor to defended upto "+this.currentArmor.getDamageReductionValue());
                    damageReduction = (int)this.currentArmor.getDamageReductionValue();
                }
                this.currentHealth -= (damage - damageReduction);
                this.currentHealth = Math.max(this.currentHealth, 0);
                if (this.currentHealth == 0){
                    this.setDead();
                }
            }
        }
    }

    /**
     * Heal the creature with the potion given in parameter
     * @param potion product
     */
    public void heal(Potion potion){
        for (CreatureAttributes attribute : potion.getAttributesAffected()){
            switch (attribute){
                case HP:
                    this.currentHealth += potion.getHealValue();
                    break;
                case MP:
                    this.currentMana += potion.getHealValue();
                    break;
                case STRENGTH:
                    this.strength += potion.getHealValue();
                    break;
                case DEXTERITY:
                    this.dexterity += potion.getHealValue();
                    break;
                case EXPERIENCE:
                    this.experience += potion.getHealValue();
                    break;
                case AGILITY:
                    this.agility += potion.getHealValue();
                    break;
                default:
                    break;
            }
        }
        this.inventory.consumeProduct(potion);
    }

    /**
     * @see AbstractCreature#levelUp()
     */
    @Override
    public void levelUp(){
        super.levelUp();
        this.mana += this.mana * 1.1;
        this.experience += this.experience * 10;
    }

    /**
     * @see AbstractCreature#regain()
     */
    @Override
    public void regain(){
        this.currentHealth += this.health * 0.1;
        this.currentMana += this.currentMana * 0.1;
    }

    /**
     * Gain experience after battle
     * @param exp integer
     */
    public void gainExperience(int exp){
        this.experience += exp;
    }

    /**
     * Game gold after battle
     * @param gold integer
     */
    public void gainGold(int gold){
        this.wallet.credit(gold);
    }

    /**
     * @see AbstractCreature#notifyObservers()
     */
    @Override
    public void notifyObservers() {
        InventoryPublisher.getInstance().notifyObservers(this.inventory, this);
    }

    /**
     * Energise the hero with half of base hp
     */
    @Override
    public void energize() {
        super.energize();
        this.currentHealth += this.health * 0.5;
        this.currentMana += this.currentMana * 0.5;
    }
}