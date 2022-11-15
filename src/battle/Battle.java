package battle;

import factory.*;
import game.Game;
import product.*;
import creature.*;
import inventory.*;
import controller.*;
import utility.Utility;

import java.util.*;

/**
 * Battle class implements Game & GameController and acts like a mini-game of the Legends of Valdor
 */
public class Battle extends Game implements GameController {
    private AbstractCreature currentAbstractCreature;
    private ArrayList<Monster> monsters;
    private final ArrayList<Hero> party;
    private final CreaturesFactory creatureFactory;
    private final Deque<ArrayList<? extends AbstractCreature>> turnQueue;

    /**
     * Creates a new battle object
     * @param party heroes party
     */
    public Battle(ArrayList<Hero> party){
        this.party = party;
        creatureFactory = new CreaturesFactory();
        this.turnQueue = new LinkedList<>();
        this.turnQueue.add(party);
        InventoryPublisher.getInstance().register(new InventoryObserver());
    }

    /**
     * calculate the highest level hero to spawn monsters
     * @return integer
     */
    private int getPartyLevel(){
        int maxLevel = 0;
        for (AbstractCreature hero : party) {
            if (maxLevel < hero.getLevel()) {
                maxLevel = hero.getLevel();
            }
        }
        return maxLevel;
    }

    /**
     * Set everything to start the battle
     */
    public void initialize(){
        int maxPartyLevel = getPartyLevel();
        this.monsters = creatureFactory.createMonsters(party.size(), maxPartyLevel);
        this.turnQueue.add(this.monsters);
    }

    /**
     * Show the inventory of player in a filtered fashion
     * This will only show products with the product type and
     * the product type came from the creature move for attack
     *
     * @param inventory hero inventory
     * @param productType type of products to show
     */
    private void showInventory(Inventory inventory, ProductType productType){
        System.out.println("\t\t\t\t******** Showing Inventory "+productType.getTypeName()+ " ********");
        inventory.showSpecificProducts(productType);
    }

    /**
     * get the move from the battle
     * @return BattleMove enum constant
     */
    private BattleMove getMove(){
        System.out.println();
        System.out.println("Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]");
        BattleMove battleMove;
        while (true){
            try {
                battleMove = BattleMove.valueOf(getStringFromUser().toUpperCase());
            } catch (Exception e){
                System.out.println("Invalid, Please enter a valid Move!");
                System.out.println("Try again!");
                continue;
            }
            break;
        }
        return battleMove;
    }

    /**
     * Get product from the hero inventory
     * @param hero hero who is communicating
     * @param productType type of product based on move
     * @return Product
     */
    private Product getProduct(Hero hero, ProductType productType){
        showInventory(hero.getInventory(), productType);
        Product product = null;
        while (true){
            String productCode = getStringFromUser();
            if (productCode.equals("q")){
                break;
            }
            product = hero.getInventory().getProduct(productCode);
            if (product == null){
                System.out.println("INVALID, Please enter valid Product code!");
                System.out.println("Try again!");
                continue;
            }
            break;
        }
        return product;
    }

    /**
     * Apply spell effects on the monsters
     * @param hero hero who made a move
     * @param spell spell hero used
     */
    private void applySpellEffects(Hero hero, Spell spell){
        System.out.println("Applying Spell Effects on Monsters");

        for (Monster monster : this.monsters){
            spell.applySpellEffects(monster);
        }
        hero.reduceMana(spell.getRequiredMana());
    }

    /**
     * Get a move of the creature to play rounds of battle
     * @return CreatureBattleMove object
     */
    private CreatureBattleMove getCreatureMove(){
        boolean turnOver = false;
        BattleMove battleMove;
        Product productToUse = null;
        Hero hero = (Hero)this.currentAbstractCreature;
        do {
            battleMove = getMove();

            if (battleMove == BattleMove.INFO){
                System.out.println();
                System.out.println("\t\t\t\t******** "+ hero.getName() +" Inventory Items ********");
                showInventory(hero.getInventory(), ProductType.ARMOR);
                showInventory(hero.getInventory(), ProductType.POTION);
                showInventory(hero.getInventory(), ProductType.SPELL);
                System.out.println();
                System.out.println("\t\t\t\t******** "+ hero.getName() +" Hero(s) Health ********");
                showInfo(new ArrayList<>(Collections.singletonList(hero)));
            }
            else if (battleMove == BattleMove.EQUIP){
                System.out.println("Enter your Product code ['q' to exit]");
                Product product;
                product = getProduct(hero, ProductType.ARMOR);
                if (product == null) {
                    continue;
                }
                if(product instanceof Armor) {
                    if (hero.getCurrentArmor() == null){
                        System.out.println(""+ hero.getName()+ " equipped an armor of "+product.getName());
                        hero.setArmorOn((Armor) product);
                    }
                    else {
                        System.out.println(""+ hero.getName()+ " replaced armor with "+product.getName());
                        hero.setArmorOn((Armor) product);
                    }
                } else {
                    if (hero.tryEquipWeapon((Weapon) product)) {
                        System.out.println("" + hero.getName() + " equipped a weapon named " + product.getName());
                    } else {
                        System.out.println("Cannot equip weapon, No hands are free");
                    }
                }
            }
            else if (battleMove == BattleMove.POTION) {
                System.out.println("Enter your potion code ['q' to exit]");
                Product product;
                product = getProduct(hero, ProductType.POTION);
                if (product == null) continue;
                hero.heal((Potion) product);
                System.out.println(hero.getName() + " used a " + product.getName() +
                        " and recovered " + CreatureAttributes.flatAttributesAffected(((Potion) product).getAttributesAffected()) +
                        " by " + ((Potion) product).getHealValue());
            }
            else if (battleMove == BattleMove.CAST){
                System.out.println("Enter your Spell code ['q' to exit]");
                Product product;
                product = getProduct(hero, ProductType.SPELL);
                if (product == null) continue;
                if (((Spell)product).isConsumable(hero)){
                    applySpellEffects(hero, (Spell)product);
                    ((Spell) product).consume(hero);
                } else {
                    System.out.println("Don't have enough Mana to hit Spell");
                }
                productToUse = product;
                turnOver = true;
            } else if (battleMove == BattleMove.ATTACK){
                turnOver = true;
            }
        } while (!turnOver);
        return new CreatureBattleMove(battleMove, productToUse);
    }

    /**
     * Select the creature to make a move for the rounds
     * @param party hero party
     * @return hero who will make next move
     */
    private Hero getCreatureToMakeMove(ArrayList<? extends AbstractCreature> party){
        System.out.println("Choose your hero to make move");
        this.showInfo(party);

        int index = -1;

        do{
            index = getIntFromUser();

            if (index > 0 && index <= party.size() && !party.get(index-1).isAlive()){
                System.out.println("Can't fight with FAINTED hero.");
                System.out.println("Choose another!!!");
            }
            if (index <= 0 || index > party.size()){
                System.out.println("INVALID INDEX, Try again!");
            }

        } while(index <= 0 || index > party.size() || !party.get(index-1).isAlive());

        return (Hero)party.get(index-1);
    }

    /**
     * compute the attack impact based on the product used
     * @param creatureBattleMove object of CreatureBattleMove
     * @return integer damage value
     */
    private int getAttackImpact(CreatureBattleMove creatureBattleMove){
        return this.currentAbstractCreature.getAttackImpact(creatureBattleMove);
    }

    /**
     * update the impact on the opponent team, also check if they can dodge or defend
     * @param damage damage given by the opponent
     */
    private void updateImpact(int damage){
        ArrayList<? extends AbstractCreature> opponents = turnQueue.pop();
        int distributedDamage = damage / opponents.size();
        for (AbstractCreature opponent : opponents){
            if (opponent.isAlive()) {
                opponent.updateAttackImpact(distributedDamage);
                System.out.println(this.currentAbstractCreature.getName() + " attacked the " + opponent.getName() + " for " + distributedDamage + " damage!");
            }
            if (!opponent.isAlive()){
                System.out.println(opponent.getName() + " Fainted!");
            }
        }
        turnQueue.add(opponents);
    }

    /**
     * Leveling up the creature happens when the experience become a factor of 50
     * @param creature creature to leveUp
     */
    private void levelUp(Hero creature){
        creature.levelUp();
        System.out.println();
        System.out.println("\t\t\t\t******** " + creature.getName()+" leveled UP on "+creature.getLevel() + " ********");
    }

    /**
     * If battle is done and hero party won they this will apply some effects of wining
     * @param party party who won the round / battle
     */
    public void winEffects(ArrayList<? extends AbstractCreature> party){
        System.out.println();
        System.out.println("Applied Wining Effects");
        System.out.println(Utility.paddedString(Hero.header));
        for (AbstractCreature abstractCreature : party){
            Hero hero = (Hero) abstractCreature;
            if (hero.isAlive()) {
                int highestLevel = getPartyLevel();
                hero.gainGold(highestLevel * 100);
                hero.gainExperience(this.monsters.size() * 2);
            } else {
                abstractCreature.regain();
                abstractCreature.energize();
            }
            System.out.println(Utility.paddedString(hero.seekInformation()));
        }

        for (AbstractCreature abstractCreature : party){
            Hero hero = (Hero) abstractCreature;
            if (hero.getExperience() % 50 == 0){
                levelUp(hero);
            }
        }
    }

    /**
     * Play one round of battle
     */
    private void playOneRound(){
        int i = 0;

        while (i < 2){
            ArrayList<? extends AbstractCreature> party = turnQueue.pop();
            System.out.println();
            if (i == 0){
                System.out.println("\t\t\t\t******** Hero's Turn ********");
                this.currentAbstractCreature = getCreatureToMakeMove(party);
                CreatureBattleMove creatureBattleMove = getCreatureMove();
                if (creatureBattleMove.getMoveType() == BattleMove.ATTACK) {
                    updateImpact(getAttackImpact(creatureBattleMove));
                }
                getEnter();
            }
            else {
                boolean monster = party.stream().anyMatch(AbstractCreature::isAlive);
                if (monster){
                    System.out.println("\t\t\t\t******** Monster Turn ********");
                    this.currentAbstractCreature = party.stream().filter(AbstractCreature::isAlive).findFirst().get();
                    int damage = getAttackImpact(new CreatureBattleMove(BattleMove.ATTACK, null));
                    updateImpact(damage);
                    getEnter();
                }
            }
            turnQueue.add(party);
            if (!canPlayRound()){
                System.out.println();
                if (i % 2 == 0){
                    System.out.println("\t\t\t\t******** All Monsters Died ********");
                    winEffects(party);
                    break;
                } else {
                    System.out.println("\t\t\t\t ******** Hero Party Died ********");
                }
            }
            i++;
        }
    }

    /**
     * Check if we can play another round of battle
     * @return boolean value
     */
    private boolean canPlayRound(){
        int count = 0;
        for (ArrayList<? extends AbstractCreature> party : turnQueue){
            if (party.stream().anyMatch(AbstractCreature::isAlive)){
                count++;
            }
        }
        return count == 2;
    }

    /**
     * Run the battle
     */
    @Override
    public void run() {
        System.out.println();
        System.out.println("\t\t\t\t******** Battle started !!! ********");
        initialize();
        int round = 0;

        while (canPlayRound()){
            turnQueue.forEach(this::showInfo);
            System.out.println("\t\t\t\t******** Starting Round #" + round + " ********");
            playOneRound();
            this.party.forEach(Hero::regain);
            round++;
        }
        this.party.forEach(Hero::energize);
    }
}


