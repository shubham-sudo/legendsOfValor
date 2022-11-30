package controller;

import PubSub.OneRoundObserver;
import PubSub.OneRoundPublisher;
import creature.Creature;
import creature.CreatureAttributes;
import creature.Hero;
import creature.Monster;
import inventory.CreatureInventory;
import inventory.Inventory;
import product.*;
import utility.Utility;

import java.util.Iterator;


/**
 * Battle controller to do attack, cast, potion, equip type of moves
 * It is a singleton class
 */
public class BattleController implements GameController, ProductController{
    private static BattleController battleController;
    private static final double WEAPON_DAMAGE_FACTOR = 0.05;
    private static final double HERO_DODGE_CHANCE_FACTOR = 0.002;
    private static final double MONSTER_DODGE_CHANCE_FACTOR = 0.01;

    /**
     * Constructor from the Battle Controller
     */
    private BattleController(){
        OneRoundPublisher.getOneRoundPublisherInstance().register(new OneRoundObserver());
    }

    /**
     * get the instance of battle controller
     * @return BattleController object
     */
    public static BattleController getBattleControllerInstance() {
        if (battleController == null) {
            battleController = new BattleController();
        }
        return battleController;
    }

    /**
     * Check if the creature can cast the spell by checking required mana
     * @param creature creature who wants to cast the spell
     * @return true if creature can, false otherwise
     */
    public boolean canCast(Creature creature) {
        Hero hero = (Hero) creature;
        Iterator<Product> it = hero.inventory().productsIterator();
        while (it.hasNext()){
            Product spell = it.next();
            if (spell instanceof Spell) {
                if (((Spell) spell).getRequiredMana() >= creature.getMana()) {
                    return true;
                }
            }
        }
        System.out.println("Creature " + creature.getName() + " don't have enough mana to CAST any spell");
        return false;
    }

    /**
     * Get the actual attack impact by the creature after using weapons or armors
     * @param attacker Attacker creature
     * @param opponent Opponent creature
     * @return double damage value
     */
    private double getActualAttackDamage(Creature attacker, Creature opponent){
        double totalDamage = attacker.getDamage();

        if (attacker instanceof Hero) {
            totalDamage -= totalDamage * 0.25;
        }

        if (attacker instanceof Hero && ((Hero) attacker).getInHandWeapons().size() > 0) {
            totalDamage = 0;
            for (Weapon weapon : ((Hero) attacker).getInHandWeapons()) {
                totalDamage += (attacker.getDamage() + weapon.getDamageValue()) * WEAPON_DAMAGE_FACTOR;
            }
        }

        // check for opponent defence
        double totalDefence = opponent.getDefence();

        if (opponent instanceof Hero) {
            if (((Hero) opponent).getArmor() != null) {
                totalDefence += ((Hero) opponent).getArmor().getDamageReductionValue();
            }
        }

        return (totalDamage - totalDefence) > 0 ? (totalDamage - totalDefence) : (totalDamage - totalDefence) + 10;
    }

    /**
     * Cast the spell
     * @param creature creature who wants to cast
     * @param opponent opponent who will be getting damage
     */
    public void cast(Creature creature, Creature opponent){
        Hero hero = (Hero) creature;

        if (Utility.rollDice() < (opponent.getDodgeChance() * MONSTER_DODGE_CHANCE_FACTOR)) {
            System.out.println(opponent.getName() + " dodged the attack by " + hero.getName());
            return;
        }

        Inventory inventory = hero.inventory();
        inventory.show(ProductType.SPELL);
        Spell product = (Spell) getProduct(inventory);

        if (product == null){
            return;
        }

        if (product.isSafeToConsume(hero)){
            product.applySpellEffects(creature, opponent);
            product.consume(hero);
            System.out.println(
                    hero.getName() + " attacked with Spell " + product.getName() + " to " + opponent.getName()
                            + " and decreased " + product.affectedAttribute() + " by "
                            + product.getSpellDamage(product.getDamageValue(), hero.getDexterity()));
        } else {
            System.out.println("Can't cast " + product.getName() + "!");
            System.out.println("Required Mana is " + product.getRequiredMana());
        }
        OneRoundPublisher.getOneRoundPublisherInstance().notifyObservers(creature, opponent, null);
    }

    /**
     * do equip of some products from the inventory
     * Since this can be done by hero as of now we are casting creature
     * @param creature Creature who want to perform equip
     */
    public void doEquip(Creature creature) {
        Hero hero = (Hero) creature;
        Inventory inventory = hero.inventory();
        inventory.show(ProductType.ARMOR);
        inventory.show(ProductType.WEAPON);
        Equipable product = (Equipable) getProduct(inventory);

        if (product == null) {
            return;
        }

        if (product.isSafeToEquip(hero)) {
            try {
                product.equip(hero);
                System.out.println(hero.getName() + " successfully equipped " + product);
            } catch (IllegalAccessException ie){
                // ie.printStackTrace();
                System.out.println("Unable to perform equip!");
            }
        } else {
            if (product instanceof Armor) {
                System.out.println("Drop the equipped "+ hero.getArmor() +" armor first!");
            } else if (product instanceof Weapon) {
                System.out.println("Free hands for " + hero.getName() + " are " + hero.getFreeHands());
                System.out.println("Number of hands required to equip "
                        + ((Weapon) product).getName() + " are " + ((Weapon) product).getRequiredHands());
            }
        }
    }

    /**
     * Heal or use potion to heal some attributes
     * @param creature creature who wants to heal
     */
    public void heal(Creature creature) {
        Hero hero = (Hero) creature;
        Inventory inventory = hero.inventory();
        inventory.show(ProductType.POTION);
        Potion product = (Potion) getProduct(inventory);

        if (product == null) {
            return;
        }

        if (product.isSafeToConsume(hero)){
            product.consume(hero);
            System.out.println(
                    hero.getName() + " healed with " + product.getName()
                            + " and increased " + CreatureAttributes.flatAttributes(product.getAttributes())
                            + " by " + product.getHealValue());
        } else {  // This situation will never occur
            System.out.println("Can't use " + product.getName() + "!");
            System.out.println("Seems like already consumed!");
        }
    }

    /**
     * Drop any product which was equipped
     * @param creature creature who wants to perform this actions
     */
    public void dropEquipable(Creature creature) {
        Hero hero = (Hero) creature;
        System.out.println();
        Inventory tempInventory = new CreatureInventory();

        if (hero.getArmor() != null){
            System.out.println("Armor in use\nName: " + hero.getArmor().getName());
            System.out.println("Saves upto damage: " + hero.getArmor().getDamageReductionValue());
            System.out.println("Armor Product Code: " + hero.getArmor().getProductCode());
            tempInventory.addProduct(hero.getArmor());
            System.out.println();
        }
        if (hero.getInHandWeapons().size() > 0){
            for (Weapon weapon : hero.getInHandWeapons()) {
                System.out.println("Weapon in use\nName: " + weapon.getName());
                System.out.println("Damage increases upto: " + weapon.getDamageValue());
                System.out.println("Weapon Product Code: " + weapon.getProductCode());
                tempInventory.addProduct(weapon);
                System.out.println();
            }
        }

        Equipable product = (Equipable) getProduct(tempInventory);

        if (product == null) {
            return;
        }
        try {
            product.drop(hero);
            System.out.println("Dropped " + product + " successfully");
        } catch (IllegalAccessException ie) {
            System.out.println(ie.getMessage());
        }
    }

    /**
     * Attack with the strength, weapon
     * @param attacker attacker creature
     * @param opponent opponent creature
     */
    public void attack(Creature attacker, Creature opponent){
        double dodgeFactor = HERO_DODGE_CHANCE_FACTOR;
        if (attacker instanceof Monster){
            dodgeFactor = MONSTER_DODGE_CHANCE_FACTOR;
        }

        if (Utility.rollDice() < (opponent.getDodgeChance() * dodgeFactor)) {
            System.out.println();
            System.out.println(attacker.getName() + " dodged the attack by " + opponent.getName());
        }

        double damage = getActualAttackDamage(attacker, opponent);
        if (damage < 0) {
            opponent.decreaseHealth(0);
            System.out.println(attacker.getName() + " attacked " + opponent.getName());
            System.out.println("Nice Job!, " + opponent.getName() + " defended " + attacker.getName() + " fully!");
        } else {
            opponent.decreaseHealth(damage);
            System.out.println(attacker.getName() + " attacked " + opponent.getName() + " with damage of " + damage);
        }

        // if opponent is fainted!
        if (!opponent.isAlive()) {
            System.out.println();
            System.out.println(opponent.getName() + " fainted by the attack of " + attacker.getName());
        }
        OneRoundPublisher.getOneRoundPublisherInstance().notifyObservers(attacker, opponent, null);
    }

    /**
     * Run the battle
     * Since the controller is capable of managing all the operations
     * and there would be only one chance for the creature so
     * run won't make more sense.
     * @throws IllegalAccessException throw if invalid operation
     */
    @Override
    public void run() throws IllegalAccessException {
    }
}
