package controller;

import PubSub.BattleOneRoundObserver;
import PubSub.BattleOneRoundPublisher;
import creature.Creature;
import creature.CreatureAttributes;
import creature.Hero;
import creature.Monster;
import inventory.CreatureInventory;
import inventory.Inventory;
import product.*;
import utility.Utility;

import java.util.Iterator;


public class BattleController implements GameController, ProductController{
    private static BattleController battleController;
    private static final double WEAPON_DAMAGE_FACTOR = 0.05;
    private static final double HERO_DODGE_CHANCE_FACTOR = 0.002;
    private static final double MONSTER_DODGE_CHANCE_FACTOR = 0.01;

    private BattleController(){
        BattleOneRoundPublisher.getBattleOneRoundPublisherInstance().register(new BattleOneRoundObserver());
    }

    public static BattleController getBattleControllerInstance() {
        if (battleController == null) {
            battleController = new BattleController();
        }
        return battleController;
    }

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

    private double getActualAttackDamage(Creature attacker, Creature opponent){
        double totalDamage = attacker.getDamage();

        if (attacker instanceof Hero) {
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

    public void cast(Creature creature, Creature opponent){
        Hero hero = (Hero) creature;
        Monster monster = (Monster) opponent;

        if (Utility.rollDice() < (monster.getDodgeChance() * MONSTER_DODGE_CHANCE_FACTOR)) {
            System.out.println(monster.getName() + " dodged the attack by " + hero.getName());
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
                    hero.getName() + " attacked " + monster.getName()
                            + " and decreased " + product.affectedAttribute() + " by "
                            + product.getSpellDamage(product.getDamageValue(), hero.getDexterity()));
        } else {
            System.out.println("Can't cast " + product.getName() + "!");
            System.out.println("Required Mana is " + product.getRequiredMana());
        }
        BattleOneRoundPublisher.getBattleOneRoundPublisherInstance().notifyObservers(creature, opponent, null);
    }

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

    public void attack(Creature creature, Creature opponent){
        double dodgeFactor = HERO_DODGE_CHANCE_FACTOR;
        if (creature instanceof Monster){
            dodgeFactor = MONSTER_DODGE_CHANCE_FACTOR;
        }

        if (Utility.rollDice() < (opponent.getDodgeChance() * dodgeFactor)) {
            System.out.println(creature.getName() + " dodged the attack by " + opponent.getName());
        }

        double damage = getActualAttackDamage(creature, opponent);
        opponent.decreaseHealth(damage);
        System.out.println(creature.getName() + " attacked " + opponent.getName() + " with damage of " + damage);
        BattleOneRoundPublisher.getBattleOneRoundPublisherInstance().notifyObservers(creature, opponent, null);
    }

    @Override
    public void run() throws IllegalAccessException {
    }
}
