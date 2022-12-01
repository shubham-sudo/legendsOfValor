# CS611 - Assignment Number - 3
## Legends of Valor
---------------------------------------------------------------------------
SHUBHAM KAUSHIK

## Files
---------------------------------------------------------------------------
src
├── battle
│     ├── Battle.java : Battle class implements Game & GameController and acts like a mini-game of the Legends of Valdor
│     ├── BattleMove.java : Enum to encapsulate the battle moves
│     └── CreatureBattleMove.java : Creature move for any round of battle
├── controller
│     ├── GameController.java : An interface for providing default api and functions for running game(s)
│     ├── LegendsGameController.java : LegendsGameController implements the GameController and control the whole game of Legends of Valor
│     └── MainController.java : This is the main controller used in Main.java class, which will also run the game user wants to run
├── creature
│     ├── AbstractCreature.java : Abstract class for all type of creatures with also implements Creature interface for basic operations
│     ├── CreatureAttributes.java : An enum which helps in comparison for increase and decrease of attributes
│     ├── Creature.java : Interface for defining several function for every type of creature
│     ├── Hero.java : Abstract base class for all type of heroes
│     ├── Monster.java : Parent class for all monsters in the Game
│     ├── Paladins.java : Paladins is a type of Hero with more strength and agility
│     ├── Sorcerers.java : Sorcerers is a type of hero with more dexterity and agility
│     └── Warriors.java : Warriors is a type of hero with more strength and dexterity
├── factory
│     ├── CreatureFactory.java : Factory class for creating creatures of different types, This class uses DataClass a data source for creating creatures
│     ├── CreaturesFactory.java : This factory class is used by the client to get the heroes and monsters
│     ├── MapFactory.java : MapFactory is to create a several type of maps, The idea was to use the easy, medium and hard level to generate different type of maps
│     ├── MarketFactory.java : Factory for market to show on market space tile
│     └── ProductFactory.java : Product factory to create several type of products
├── game
│     ├── Game.java : Abstract Game class for all the games
│     └── LegendsGame.java : Child class of Game which defines the Legend Game
├── inventory
│     ├── CreatureInventory.java : Child class of the Inventory to define different function for the creature
│     ├── Inventory.java : Abstract inventory class for Market and Creatures
│     ├── InventoryObserver.java : This is the observer which cleans the inventory if any product is no more usable
│     ├── InventoryPublisher.java : A publisher for sending signal to the observers of the inventory to update
│     └── MarketInventory.java : MarketInventory is the child class of Inventory for markets
├── map
│     ├── CurrentPosition.java : Current position is just to hold the position of the player in the map
│     ├── Map.java : Map of the game where player can move on different tiles
│     └── Tile.java : Each Tile object of the map
├── market
│     ├── Market.java : Market is another small mini-game which extends Game and implements GameController
│     └── MarketMove.java : Enum for possible moves in market
├── product
│     ├── Armor.java : Armor product class which save with some damage
│     ├── Consumable.java : An interface for all consumable type of products
│     ├── FireSpell.java : Fire spell is a Spell which reduce the defence of the opponent
│     ├── IceSpell.java : Ice spell is a Spell which reduce the damage to the opponent
│     ├── LightningSpell.java : Lightning spell is a Spell which reduce the agility of the opponent
│     ├── Potion.java : Potion products are healer to heal some attributes of the creature
│     ├── Product.java : Base abstract product class for all the products creature can have
│     ├── ProductType.java : An enum for different type of products of the Game
│     ├── SpellEffects.java : An interface to spell effects for the different type of spells
│     ├── Spell.java : Spell are used to decrease few attributes of the opponent, This is the abstract class for different type of spells
│     ├── Weapon.java : Weapon product to fight against opponent
│     └── Wearable.java : An interface for all wearable products
├── PubSub
│     ├── GameObserver.java : An interface for all observer designs
│     └── GamePublisher.java : An interface for all Game publishers
├── space
│     ├── CommonSpace.java : Common space on the tile
│     ├── InaccessibleSpace.java : Inaccessible space on map (maybe wall)
│     ├── MarketSpace.java : Market space on map
│     └── Space.java : An interface for all types of space on tiles
├── utility
│     ├── CreatureDataClass.java : DataClass for all creatures, the data is hardcoded here, When game run it will pick data from here
│     ├── ProductDataClass.java : Dataclass for the hard coded products, This will be used when game runs
│     ├── SingletonScanner.java : Single scanner object across the game
│     └── Utility.java : Utility class helps in printing data in table formats
├── wallet
│   └── Wallet.java : Wallet class gives the feature of wallet to the creature
└── Main.java : Main class

## Notes
---------------------------------------------------------------------------
1. No config files used as per the current structure.
2. (a) Implemented Pub-Sub model for performing inventory update in case of SPELL CAST, since casting is a consumable operation so that will be removed from inventory.
   (b) Inventory is an abstract class, which is has-a relation with Heros and Market. Abstract Inventory does the basic operations and MarketInventory & CreatureInventory does their inventory specific jobs.
   (c) Defined Player with couple of heroes. This is changeable upto certain limits.
   (c) Strategy of occupying different spaces is quite different, hence used a strategy pattern in this.
3. The rendering of board is very simple with lines and pipes where 'P*' represents the player "M" & "X" are used for market & inaccessible tile respectively.
4. GameController is an interface which gives a basic functionality for Legends Game and small Battle like games.

## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "legends" after unzipping the files
2. Run the following instructions:
   mkdir bin
   javac -d bin @sources.txt
   java -cp bin Main

## Input/Output Example
---------------------------------------------------------------------------
Hello, Welcome!!!
Which game you want to play today?
[1] Legends
Enter your input : 1

******** LegendsGame ********
This is a magical game full of spells, monsters, and heroes.
The monsters and heroes live in a fictional world. They do not get along and therefore fight each other.

Default Board size is [8],
Do you want to change it? [YES/yes/y/NO/no/n]
Enter your input : n

Default party size is [3],
Do you want to change it? [YES/yes/y/NO/no/n]
Enter your input : n

Enter Number for the type of Hero!
[1] Paladins
[2] Sorcerers
[3] Warriors
Enter a Integer : 1
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/500             YES                 500                 5                   500                 500                                                         2500.0              
[2] Caliber_Heist       100/100             400/400             YES                 400                 8                   400                 400                                                         2500.0              
[3] Garl_Glittergold    100/100             100/100             YES                 600                 5                   400                 500                                                         2500.0              
[4] Sehanine_Moonbow    100/100             300/300             YES                 750                 7                   700                 700                                                         2500.0              
[5] Skoraeus_Stonebones 100/100             250/250             YES                 650                 4                   350                 600                                                         2500.0              
[6] Parzival            100/100             300/300             YES                 750                 7                   700                 650                                                         2500.0              


Enter a Integer : 1

Enter Number for the type of Hero!
[1] Paladins
[2] Sorcerers
[3] Warriors
Enter a Integer : 2
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Rillifane_Rallathil 100/100             1300/1300           YES                 750                 9                   500                 450                                                         2500.0              
[2] Skye_Soar           100/100             1000/1000           YES                 700                 5                   500                 400                                                         2500.0              
[3] Segojan_Earthcaller 100/100             900/900             YES                 800                 5                   650                 500                                                         2500.0              
[4] Kalabar             100/100             800/800             YES                 850                 6                   600                 400                                                         2500.0              
[5] Reverie_Ashels      100/100             900/900             YES                 800                 7                   400                 700                                                         2500.0              
[6] Reign_Havoc         100/100             800/800             YES                 800                 8                   800                 800                                                         2500.0              


Enter a Integer : 2

Enter Number for the type of Hero!
[1] Paladins
[2] Sorcerers
[3] Warriors
Enter a Integer : 3
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Eunoia_Cyn          100/100             400/400             YES                 700                 6                   600                 800                                                         2500.0              
[2] Undefeated_Yoj      100/100             400/400             YES                 800                 7                   700                 400                                                         2500.0              
[3] Gaerdal_Ironhand    100/100             100/100             YES                 700                 7                   600                 500                                                         1354.0              
[4] Sehanine_Monnbow    100/100             600/600             YES                 700                 8                   500                 800                                                         2500.0              
[5] Flandal_Steelskin   100/100             200/200             YES                 750                 7                   700                 650                                                         2500.0              
[6] Muamman_Duathall    100/100             300/300             YES                 900                 6                   750                 500                                                         2546.0              


Enter a Integer : 1

				 ******** Chosen Heroes are ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/500             YES                 500                 5                   500                 500                                                         2500.0              
[2] Skye_Soar           100/100             1000/1000           YES                 700                 5                   500                 400                                                         2500.0              
[3] Eunoia_Cyn          100/100             400/400             YES                 700                 6                   600                 800                                                         2500.0              

Party is all-set to move on map !

Hit Enter ...



				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  |  M  |     |     |     |  M  |
				------------------------------------------------
				|  P* |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position

Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : d

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |  P* |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : d

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |     |  P* |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : w

				********Welcome to the Bud's Place Market!!!********
Enter 'm' to enter into market!
Enter your Move : d

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     | MP* |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : m

				********Welcome to the Bud's Place Market!!!********
Enter 'm' to enter into market!
Enter your Move : m
				******** Choose your hero to BUY/SELL ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/500             YES                 500                 5                   500                 500                                                         2500.0              
[2] Skye_Soar           100/100             1000/1000           YES                 700                 5                   500                 400                                                         2500.0              
[3] Eunoia_Cyn          100/100             400/400             YES                 700                 6                   600                 800                                                         2500.0              

Enter a Integer : 1

Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : info

				******** Amaryllis_Astra Inventory Status ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            


Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : buy

Product Name        Level               Price               Product Code        Type                Impact Value        More Info           
Luck_Elixir         4                   500.0               luck_elixir         Potion              65.0                AGILITY             
Full_Body_Armor     8                   1000.0              full_body_armor     Armor               1100.0              
Ice_Blade           1                   250.0               ice_blade           IceSpell            450.0               100.0               
Flame_Tornado       4                   700.0               flame_tornado       FireSpell           850.0               300.0               
Breath_of_Fire      1                   350.0               breath_of_fire      FireSpell           450.0               100.0               
Platinum_Shield     1                   150.0               platinum_shield     Armor               200.0               
Electric_Arrows     5                   550.0               electric_arrows     LightningSpell      650.0               200.0               
Dagger              1                   200.0               dagger              Weapon              250.0               
Wizard_Shield       10                  1200.0              wizard_shield       Armor               1500.0              
Lightning_Dagger    1                   400.0               lightning_dagger    LightningSpell      500.0               150.0               
Lava_Comet          7                   800.0               lava_comet          FireSpell           1000.0              550.0               
Breastplate         3                   350.0               breastplate         Armor               600.0               
Heat_Wave           2                   450.0               heat_wave           FireSpell           600.0               150.0               
Healing_Potion      1                   250.0               healing_potion      Potion              100.0               HP                  
Sword               1                   500.0               sword               Weapon              800.0               
Snow_Cannon         2                   500.0               snow_cannon         IceSpell            650.0               250.0               
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT
Thunder_Blast       4                   750.0               thunder_blast       LightningSpell      950.0               400.0               
Frost_Blizzard      5                   750.0               frost_blizzard      IceSpell            850.0               350.0               
Mermaid_Tears       5                   850.0               mermaid_tears       Potion              100.0               HP/MP/STRENGTH/AGIL
Hell_Storm          3                   600.0               hell_storm          FireSpell           950.0               600.0               
Magic_Potion        2                   350.0               magic_potion        Potion              100.0               MP                  
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               
Arctic_Storm        6                   700.0               arctic_storm        IceSpell            800.0               300.0               
Guardian_Angel      10                  1000.0              guardian_angel      Armor               1000.0              
Strength_Potion     1                   200.0               strength_potion     Potion              75.0                STRENGTH            


Enter the product_code of Product to BUY ['q' to exit]
Enter your input : sword
Amaryllis_Astra Inventory Updated!!!
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Sword               1                   500.0               sword               Weapon              800.0               1                   


Hit Enter ...


Product Name        Level               Price               Product Code        Type                Impact Value        More Info           
Luck_Elixir         4                   500.0               luck_elixir         Potion              65.0                AGILITY             
Full_Body_Armor     8                   1000.0              full_body_armor     Armor               1100.0              
Ice_Blade           1                   250.0               ice_blade           IceSpell            450.0               100.0               
Flame_Tornado       4                   700.0               flame_tornado       FireSpell           850.0               300.0               
Breath_of_Fire      1                   350.0               breath_of_fire      FireSpell           450.0               100.0               
Platinum_Shield     1                   150.0               platinum_shield     Armor               200.0               
Electric_Arrows     5                   550.0               electric_arrows     LightningSpell      650.0               200.0               
Dagger              1                   200.0               dagger              Weapon              250.0               
Wizard_Shield       10                  1200.0              wizard_shield       Armor               1500.0              
Lightning_Dagger    1                   400.0               lightning_dagger    LightningSpell      500.0               150.0               
Lava_Comet          7                   800.0               lava_comet          FireSpell           1000.0              550.0               
Breastplate         3                   350.0               breastplate         Armor               600.0               
Heat_Wave           2                   450.0               heat_wave           FireSpell           600.0               150.0               
Healing_Potion      1                   250.0               healing_potion      Potion              100.0               HP                  
Snow_Cannon         2                   500.0               snow_cannon         IceSpell            650.0               250.0               
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT
Thunder_Blast       4                   750.0               thunder_blast       LightningSpell      950.0               400.0               
Frost_Blizzard      5                   750.0               frost_blizzard      IceSpell            850.0               350.0               
Mermaid_Tears       5                   850.0               mermaid_tears       Potion              100.0               HP/MP/STRENGTH/AGIL
Hell_Storm          3                   600.0               hell_storm          FireSpell           950.0               600.0               
Magic_Potion        2                   350.0               magic_potion        Potion              100.0               MP                  
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               
Arctic_Storm        6                   700.0               arctic_storm        IceSpell            800.0               300.0               
Guardian_Angel      10                  1000.0              guardian_angel      Armor               1000.0              
Strength_Potion     1                   200.0               strength_potion     Potion              75.0                STRENGTH            


Enter the product_code of Product to BUY ['q' to exit]
Enter your input : ambrosia
Amaryllis_Astra Inventory Updated!!!
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Sword               1                   500.0               sword               Weapon              800.0               1                   
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT1                   


Hit Enter ...


Product Name        Level               Price               Product Code        Type                Impact Value        More Info           
Luck_Elixir         4                   500.0               luck_elixir         Potion              65.0                AGILITY             
Full_Body_Armor     8                   1000.0              full_body_armor     Armor               1100.0              
Ice_Blade           1                   250.0               ice_blade           IceSpell            450.0               100.0               
Flame_Tornado       4                   700.0               flame_tornado       FireSpell           850.0               300.0               
Breath_of_Fire      1                   350.0               breath_of_fire      FireSpell           450.0               100.0               
Platinum_Shield     1                   150.0               platinum_shield     Armor               200.0               
Electric_Arrows     5                   550.0               electric_arrows     LightningSpell      650.0               200.0               
Dagger              1                   200.0               dagger              Weapon              250.0               
Wizard_Shield       10                  1200.0              wizard_shield       Armor               1500.0              
Lightning_Dagger    1                   400.0               lightning_dagger    LightningSpell      500.0               150.0               
Lava_Comet          7                   800.0               lava_comet          FireSpell           1000.0              550.0               
Breastplate         3                   350.0               breastplate         Armor               600.0               
Heat_Wave           2                   450.0               heat_wave           FireSpell           600.0               150.0               
Healing_Potion      1                   250.0               healing_potion      Potion              100.0               HP                  
Snow_Cannon         2                   500.0               snow_cannon         IceSpell            650.0               250.0               
Thunder_Blast       4                   750.0               thunder_blast       LightningSpell      950.0               400.0               
Frost_Blizzard      5                   750.0               frost_blizzard      IceSpell            850.0               350.0               
Mermaid_Tears       5                   850.0               mermaid_tears       Potion              100.0               HP/MP/STRENGTH/AGIL
Hell_Storm          3                   600.0               hell_storm          FireSpell           950.0               600.0               
Magic_Potion        2                   350.0               magic_potion        Potion              100.0               MP                  
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               
Arctic_Storm        6                   700.0               arctic_storm        IceSpell            800.0               300.0               
Guardian_Angel      10                  1000.0              guardian_angel      Armor               1000.0              
Strength_Potion     1                   200.0               strength_potion     Potion              75.0                STRENGTH            


Enter the product_code of Product to BUY ['q' to exit]
Enter your input : q

Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : exit
Do you want to SELECT another Hero! [YES/yes/y/NO/no/n]
Enter your input : y
				******** Choose your hero to BUY/SELL ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/500             YES                 500                 5                   500                 500                                                         1000.0              
[2] Skye_Soar           100/100             1000/1000           YES                 700                 5                   500                 400                                                         2500.0              
[3] Eunoia_Cyn          100/100             400/400             YES                 700                 6                   600                 800                                                         2500.0              

Enter a Integer : 1

Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : info

				******** Amaryllis_Astra Inventory Status ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Sword               1                   500.0               sword               Weapon              800.0               1                   
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT1                   


Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : exit
Do you want to SELECT another Hero! [YES/yes/y/NO/no/n]
Enter your input : n

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     | MP* |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : d

				********Welcome to the Imperial Outpost Market!!!********
Enter 'm' to enter into market!
Enter your Move : n

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  | MP* |     |     |     |  M  |
				------------------------------------------------
				|     |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : w

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |  P* |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : 
INVALID, Please enter a Character!
Try again!
Enter your Move : 
INVALID, Please enter a Character!
Try again!
Enter your Move : w

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |  P* |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : w

				******** Battle started !!! ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/500             YES                 500                 5                   500                 500                                                         1000.0              
[2] Skye_Soar           100/100             1000/1000           YES                 700                 5                   500                 400                                                         2500.0              
[3] Eunoia_Cyn          100/100             400/400             YES                 700                 6                   600                 800                                                         2500.0              

    MONSTER Name        HP                  DAMAGE              DEFENCE             DODGE               
[1] Blinky              100                 450                 350                 35                  
[2] Natsunomeryu        100                 100                 200                 10                  
[3] Natsunomeryu        100                 100                 200                 10                  

				******** Starting Round #0 ********

				******** Hero's Turn ********
Choose your hero to make move
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/500             YES                 500                 5                   500                 500                                                         1000.0              
[2] Skye_Soar           100/100             1000/1000           YES                 700                 5                   500                 400                                                         2500.0              
[3] Eunoia_Cyn          100/100             400/400             YES                 700                 6                   600                 800                                                         2500.0              

Enter a Integer : 1

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : info

				******** Amaryllis_Astra Inventory Items ********
				******** Showing Inventory Equipable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Sword               1                   500.0               sword               Weapon              800.0               1                   

				******** Showing Inventory Healable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT1                   

				******** Showing Inventory Castable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            


				******** Amaryllis_Astra Hero(s) Health ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/500             YES                 500                 5                   500                 500                                                         1000.0              


Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : equip
Enter your Product code ['q' to exit]
				******** Showing Inventory Equipable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Sword               1                   500.0               sword               Weapon              800.0               1                   

Enter your input : sword
Amaryllis_Astra equipped a weapon named Sword

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : info

				******** Amaryllis_Astra Inventory Items ********
				******** Showing Inventory Equipable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            

				******** Showing Inventory Healable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT1                   

				******** Showing Inventory Castable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            


				******** Amaryllis_Astra Hero(s) Health ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/500             YES                 500                 5                   500                 500                 Product<sword>                          1000.0              


Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : potion
Enter your potion code ['q' to exit]
				******** Showing Inventory Healable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT1                   

Enter your input : ambrosia
Amaryllis_Astra used a Ambrosia and recovered HP/MP/STRENGTH/DEXTERITY/AGILITY by 150.0

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : info

				******** Amaryllis_Astra Inventory Items ********
				******** Showing Inventory Equipable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            

				******** Showing Inventory Healable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            

				******** Showing Inventory Castable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            


				******** Amaryllis_Astra Hero(s) Health ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     250/100             500/650             YES                 650                 5                   650                 650                 Product<sword>                          1000.0              


Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : attack
Amaryllis_Astra attacked the Blinky for 241 damage!
Amaryllis_Astra attacked the Natsunomeryu for 241 damage!
Amaryllis_Astra attacked the Natsunomeryu for 241 damage!

Hit Enter ...
n

				******** Monster Turn ********
Blinky attacked the Amaryllis_Astra for 150 damage!
Blinky attacked the Skye_Soar for 150 damage!
Skye_Soar Fainted!
Blinky attacked the Eunoia_Cyn for 150 damage!
Eunoia_Cyn Fainted!

Hit Enter ...

    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/650             YES                 650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           0/100               1000/1000           NO                  700                 5                   500                 400                                                         2500.0              
[3] Eunoia_Cyn          0/100               400/400             NO                  700                 6                   600                 800                                                         2500.0              

    MONSTER Name        HP                  DAMAGE              DEFENCE             DODGE               
[1] Blinky              100                 450                 350                 35                  
[2] Natsunomeryu        59                  100                 200                 10                  
[3] Natsunomeryu        59                  100                 200                 10                  

				******** Starting Round #1 ********

				******** Hero's Turn ********
Choose your hero to make move
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     100/100             500/650             YES                 650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           0/100               1000/1000           NO                  700                 5                   500                 400                                                         2500.0              
[3] Eunoia_Cyn          0/100               400/400             NO                  700                 6                   600                 800                                                         2500.0              

Enter a Integer : 2
Can't fight with FAINTED hero.
Choose another!!!
Enter a Integer : 3
Can't fight with FAINTED hero.
Choose another!!!
Enter a Integer : 1

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : cast
Enter your Spell code ['q' to exit]
				******** Showing Inventory Castable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            

Enter your input : q

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : attack
Amaryllis_Astra attacked the Blinky for 241 damage!
Amaryllis_Astra attacked the Natsunomeryu for 241 damage!
Amaryllis_Astra attacked the Natsunomeryu for 241 damage!

Hit Enter ...



				******** Monster Turn ********
Blinky attacked the Amaryllis_Astra for 150 damage!
Amaryllis_Astra Fainted!
Blinky attacked the Skye_Soar for 150 damage!
Skye_Soar Fainted!
Blinky attacked the Eunoia_Cyn for 150 damage!
Eunoia_Cyn Fainted!

Hit Enter ...

				 ******** Hero Party Died ********

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |  P* |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : d

				********Welcome to the Next-Gen Market!!!********
Enter 'm' to enter into market!
Enter your Move : m
				******** Choose your hero to BUY/SELL ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     50/100              750/650             YES                 650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           50/100              1500/1000           YES                 700                 5                   500                 400                                                         2500.0              
[3] Eunoia_Cyn          50/100              600/400             YES                 700                 6                   600                 800                                                         2500.0              

Enter a Integer : n
INVALID, Please enter a Integer!
Try again!
Enter a Integer : 2

Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : info

				******** Skye_Soar Inventory Status ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            


Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : buy

Product Name        Level               Price               Product Code        Type                Impact Value        More Info           
Luck_Elixir         4                   500.0               luck_elixir         Potion              65.0                AGILITY             
Full_Body_Armor     8                   1000.0              full_body_armor     Armor               1100.0              
Ice_Blade           1                   250.0               ice_blade           IceSpell            450.0               100.0               
Flame_Tornado       4                   700.0               flame_tornado       FireSpell           850.0               300.0               
Breath_of_Fire      1                   350.0               breath_of_fire      FireSpell           450.0               100.0               
Platinum_Shield     1                   150.0               platinum_shield     Armor               200.0               
Electric_Arrows     5                   550.0               electric_arrows     LightningSpell      650.0               200.0               
Dagger              1                   200.0               dagger              Weapon              250.0               
Wizard_Shield       10                  1200.0              wizard_shield       Armor               1500.0              
Lightning_Dagger    1                   400.0               lightning_dagger    LightningSpell      500.0               150.0               
Lava_Comet          7                   800.0               lava_comet          FireSpell           1000.0              550.0               
Breastplate         3                   350.0               breastplate         Armor               600.0               
Heat_Wave           2                   450.0               heat_wave           FireSpell           600.0               150.0               
Healing_Potion      1                   250.0               healing_potion      Potion              100.0               HP                  
Sword               1                   500.0               sword               Weapon              800.0               
Snow_Cannon         2                   500.0               snow_cannon         IceSpell            650.0               250.0               
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT
Thunder_Blast       4                   750.0               thunder_blast       LightningSpell      950.0               400.0               
Frost_Blizzard      5                   750.0               frost_blizzard      IceSpell            850.0               350.0               
Mermaid_Tears       5                   850.0               mermaid_tears       Potion              100.0               HP/MP/STRENGTH/AGIL
Hell_Storm          3                   600.0               hell_storm          FireSpell           950.0               600.0               
Magic_Potion        2                   350.0               magic_potion        Potion              100.0               MP                  
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               
Arctic_Storm        6                   700.0               arctic_storm        IceSpell            800.0               300.0               
Guardian_Angel      10                  1000.0              guardian_angel      Armor               1000.0              
Strength_Potion     1                   200.0               strength_potion     Potion              75.0                STRENGTH            


Enter the product_code of Product to BUY ['q' to exit]
Enter your input : q

Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : info

				******** Skye_Soar Inventory Status ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            


Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : buy

Product Name        Level               Price               Product Code        Type                Impact Value        More Info           
Luck_Elixir         4                   500.0               luck_elixir         Potion              65.0                AGILITY             
Full_Body_Armor     8                   1000.0              full_body_armor     Armor               1100.0              
Ice_Blade           1                   250.0               ice_blade           IceSpell            450.0               100.0               
Flame_Tornado       4                   700.0               flame_tornado       FireSpell           850.0               300.0               
Breath_of_Fire      1                   350.0               breath_of_fire      FireSpell           450.0               100.0               
Platinum_Shield     1                   150.0               platinum_shield     Armor               200.0               
Electric_Arrows     5                   550.0               electric_arrows     LightningSpell      650.0               200.0               
Dagger              1                   200.0               dagger              Weapon              250.0               
Wizard_Shield       10                  1200.0              wizard_shield       Armor               1500.0              
Lightning_Dagger    1                   400.0               lightning_dagger    LightningSpell      500.0               150.0               
Lava_Comet          7                   800.0               lava_comet          FireSpell           1000.0              550.0               
Breastplate         3                   350.0               breastplate         Armor               600.0               
Heat_Wave           2                   450.0               heat_wave           FireSpell           600.0               150.0               
Healing_Potion      1                   250.0               healing_potion      Potion              100.0               HP                  
Sword               1                   500.0               sword               Weapon              800.0               
Snow_Cannon         2                   500.0               snow_cannon         IceSpell            650.0               250.0               
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT
Thunder_Blast       4                   750.0               thunder_blast       LightningSpell      950.0               400.0               
Frost_Blizzard      5                   750.0               frost_blizzard      IceSpell            850.0               350.0               
Mermaid_Tears       5                   850.0               mermaid_tears       Potion              100.0               HP/MP/STRENGTH/AGIL
Hell_Storm          3                   600.0               hell_storm          FireSpell           950.0               600.0               
Magic_Potion        2                   350.0               magic_potion        Potion              100.0               MP                  
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               
Arctic_Storm        6                   700.0               arctic_storm        IceSpell            800.0               300.0               
Guardian_Angel      10                  1000.0              guardian_angel      Armor               1000.0              
Strength_Potion     1                   200.0               strength_potion     Potion              75.0                STRENGTH            


Enter the product_code of Product to BUY ['q' to exit]
Enter your input : breastplate
Skye_Soar Inventory Updated!!!
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Breastplate         3                   350.0               breastplate         Armor               600.0               1                   


Hit Enter ...


Product Name        Level               Price               Product Code        Type                Impact Value        More Info           
Luck_Elixir         4                   500.0               luck_elixir         Potion              65.0                AGILITY             
Full_Body_Armor     8                   1000.0              full_body_armor     Armor               1100.0              
Ice_Blade           1                   250.0               ice_blade           IceSpell            450.0               100.0               
Flame_Tornado       4                   700.0               flame_tornado       FireSpell           850.0               300.0               
Breath_of_Fire      1                   350.0               breath_of_fire      FireSpell           450.0               100.0               
Platinum_Shield     1                   150.0               platinum_shield     Armor               200.0               
Electric_Arrows     5                   550.0               electric_arrows     LightningSpell      650.0               200.0               
Dagger              1                   200.0               dagger              Weapon              250.0               
Wizard_Shield       10                  1200.0              wizard_shield       Armor               1500.0              
Lightning_Dagger    1                   400.0               lightning_dagger    LightningSpell      500.0               150.0               
Lava_Comet          7                   800.0               lava_comet          FireSpell           1000.0              550.0               
Heat_Wave           2                   450.0               heat_wave           FireSpell           600.0               150.0               
Healing_Potion      1                   250.0               healing_potion      Potion              100.0               HP                  
Sword               1                   500.0               sword               Weapon              800.0               
Snow_Cannon         2                   500.0               snow_cannon         IceSpell            650.0               250.0               
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT
Thunder_Blast       4                   750.0               thunder_blast       LightningSpell      950.0               400.0               
Frost_Blizzard      5                   750.0               frost_blizzard      IceSpell            850.0               350.0               
Mermaid_Tears       5                   850.0               mermaid_tears       Potion              100.0               HP/MP/STRENGTH/AGIL
Hell_Storm          3                   600.0               hell_storm          FireSpell           950.0               600.0               
Magic_Potion        2                   350.0               magic_potion        Potion              100.0               MP                  
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               
Arctic_Storm        6                   700.0               arctic_storm        IceSpell            800.0               300.0               
Guardian_Angel      10                  1000.0              guardian_angel      Armor               1000.0              
Strength_Potion     1                   200.0               strength_potion     Potion              75.0                STRENGTH            


Enter the product_code of Product to BUY ['q' to exit]
Enter your input : spark_needles
Skye_Soar Inventory Updated!!!
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Breastplate         3                   350.0               breastplate         Armor               600.0               1                   
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               1                   


Hit Enter ...


Product Name        Level               Price               Product Code        Type                Impact Value        More Info           
Luck_Elixir         4                   500.0               luck_elixir         Potion              65.0                AGILITY             
Full_Body_Armor     8                   1000.0              full_body_armor     Armor               1100.0              
Ice_Blade           1                   250.0               ice_blade           IceSpell            450.0               100.0               
Flame_Tornado       4                   700.0               flame_tornado       FireSpell           850.0               300.0               
Breath_of_Fire      1                   350.0               breath_of_fire      FireSpell           450.0               100.0               
Platinum_Shield     1                   150.0               platinum_shield     Armor               200.0               
Electric_Arrows     5                   550.0               electric_arrows     LightningSpell      650.0               200.0               
Dagger              1                   200.0               dagger              Weapon              250.0               
Wizard_Shield       10                  1200.0              wizard_shield       Armor               1500.0              
Lightning_Dagger    1                   400.0               lightning_dagger    LightningSpell      500.0               150.0               
Lava_Comet          7                   800.0               lava_comet          FireSpell           1000.0              550.0               
Heat_Wave           2                   450.0               heat_wave           FireSpell           600.0               150.0               
Healing_Potion      1                   250.0               healing_potion      Potion              100.0               HP                  
Sword               1                   500.0               sword               Weapon              800.0               
Snow_Cannon         2                   500.0               snow_cannon         IceSpell            650.0               250.0               
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT
Thunder_Blast       4                   750.0               thunder_blast       LightningSpell      950.0               400.0               
Frost_Blizzard      5                   750.0               frost_blizzard      IceSpell            850.0               350.0               
Mermaid_Tears       5                   850.0               mermaid_tears       Potion              100.0               HP/MP/STRENGTH/AGIL
Hell_Storm          3                   600.0               hell_storm          FireSpell           950.0               600.0               
Magic_Potion        2                   350.0               magic_potion        Potion              100.0               MP                  
Arctic_Storm        6                   700.0               arctic_storm        IceSpell            800.0               300.0               
Guardian_Angel      10                  1000.0              guardian_angel      Armor               1000.0              
Strength_Potion     1                   200.0               strength_potion     Potion              75.0                STRENGTH            


Enter the product_code of Product to BUY ['q' to exit]
Enter your input : ambrosia
Skye_Soar Inventory Updated!!!
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT1                   
Breastplate         3                   350.0               breastplate         Armor               600.0               1                   
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               1                   


Hit Enter ...


Product Name        Level               Price               Product Code        Type                Impact Value        More Info           
Luck_Elixir         4                   500.0               luck_elixir         Potion              65.0                AGILITY             
Full_Body_Armor     8                   1000.0              full_body_armor     Armor               1100.0              
Ice_Blade           1                   250.0               ice_blade           IceSpell            450.0               100.0               
Flame_Tornado       4                   700.0               flame_tornado       FireSpell           850.0               300.0               
Breath_of_Fire      1                   350.0               breath_of_fire      FireSpell           450.0               100.0               
Platinum_Shield     1                   150.0               platinum_shield     Armor               200.0               
Electric_Arrows     5                   550.0               electric_arrows     LightningSpell      650.0               200.0               
Dagger              1                   200.0               dagger              Weapon              250.0               
Wizard_Shield       10                  1200.0              wizard_shield       Armor               1500.0              
Lightning_Dagger    1                   400.0               lightning_dagger    LightningSpell      500.0               150.0               
Lava_Comet          7                   800.0               lava_comet          FireSpell           1000.0              550.0               
Heat_Wave           2                   450.0               heat_wave           FireSpell           600.0               150.0               
Healing_Potion      1                   250.0               healing_potion      Potion              100.0               HP                  
Sword               1                   500.0               sword               Weapon              800.0               
Snow_Cannon         2                   500.0               snow_cannon         IceSpell            650.0               250.0               
Thunder_Blast       4                   750.0               thunder_blast       LightningSpell      950.0               400.0               
Frost_Blizzard      5                   750.0               frost_blizzard      IceSpell            850.0               350.0               
Mermaid_Tears       5                   850.0               mermaid_tears       Potion              100.0               HP/MP/STRENGTH/AGIL
Hell_Storm          3                   600.0               hell_storm          FireSpell           950.0               600.0               
Magic_Potion        2                   350.0               magic_potion        Potion              100.0               MP                  
Arctic_Storm        6                   700.0               arctic_storm        IceSpell            800.0               300.0               
Guardian_Angel      10                  1000.0              guardian_angel      Armor               1000.0              
Strength_Potion     1                   200.0               strength_potion     Potion              75.0                STRENGTH            


Enter the product_code of Product to BUY ['q' to exit]
Enter your input : sword
Skye_Soar Inventory Updated!!!
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Sword               1                   500.0               sword               Weapon              800.0               1                   
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT1                   
Breastplate         3                   350.0               breastplate         Armor               600.0               1                   
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               1                   


Hit Enter ...


Product Name        Level               Price               Product Code        Type                Impact Value        More Info           
Luck_Elixir         4                   500.0               luck_elixir         Potion              65.0                AGILITY             
Full_Body_Armor     8                   1000.0              full_body_armor     Armor               1100.0              
Ice_Blade           1                   250.0               ice_blade           IceSpell            450.0               100.0               
Flame_Tornado       4                   700.0               flame_tornado       FireSpell           850.0               300.0               
Breath_of_Fire      1                   350.0               breath_of_fire      FireSpell           450.0               100.0               
Platinum_Shield     1                   150.0               platinum_shield     Armor               200.0               
Electric_Arrows     5                   550.0               electric_arrows     LightningSpell      650.0               200.0               
Dagger              1                   200.0               dagger              Weapon              250.0               
Wizard_Shield       10                  1200.0              wizard_shield       Armor               1500.0              
Lightning_Dagger    1                   400.0               lightning_dagger    LightningSpell      500.0               150.0               
Lava_Comet          7                   800.0               lava_comet          FireSpell           1000.0              550.0               
Heat_Wave           2                   450.0               heat_wave           FireSpell           600.0               150.0               
Healing_Potion      1                   250.0               healing_potion      Potion              100.0               HP                  
Snow_Cannon         2                   500.0               snow_cannon         IceSpell            650.0               250.0               
Thunder_Blast       4                   750.0               thunder_blast       LightningSpell      950.0               400.0               
Frost_Blizzard      5                   750.0               frost_blizzard      IceSpell            850.0               350.0               
Mermaid_Tears       5                   850.0               mermaid_tears       Potion              100.0               HP/MP/STRENGTH/AGIL
Hell_Storm          3                   600.0               hell_storm          FireSpell           950.0               600.0               
Magic_Potion        2                   350.0               magic_potion        Potion              100.0               MP                  
Arctic_Storm        6                   700.0               arctic_storm        IceSpell            800.0               300.0               
Guardian_Angel      10                  1000.0              guardian_angel      Armor               1000.0              
Strength_Potion     1                   200.0               strength_potion     Potion              75.0                STRENGTH            


Enter the product_code of Product to BUY ['q' to exit]
Enter your input : q

Enter your move : [BUY/SELL/INFO/EXIT]
Enter your input : exit
Do you want to SELECT another Hero! [YES/yes/y/NO/no/n]
Enter your input : n

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     | MP* |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : a

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |  P* |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |     |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move : s

				******** Battle started !!! ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     50/100              750/650             YES                 650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           50/100              1500/1000           YES                 700                 5                   500                 400                                                         150.0               
[3] Eunoia_Cyn          50/100              600/400             YES                 700                 6                   600                 800                                                         2500.0              

    MONSTER Name        HP                  DAMAGE              DEFENCE             DODGE               
[1] BigBad-Wolf         100                 150                 250                 15                  
[2] Casper              100                 100                 100                 50                  
[3] BigBad-Wolf         100                 150                 250                 15                  

				******** Starting Round #0 ********

				******** Hero's Turn ********
Choose your hero to make move
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     50/100              750/650             YES                 650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           50/100              1500/1000           YES                 700                 5                   500                 400                                                         150.0               
[3] Eunoia_Cyn          50/100              600/400             YES                 700                 6                   600                 800                                                         2500.0              

Enter a Integer : 2

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : equip
Enter your Product code ['q' to exit]
				******** Showing Inventory Equipable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Sword               1                   500.0               sword               Weapon              800.0               1                   
Breastplate         3                   350.0               breastplate         Armor               600.0               1                   

Enter your input : sword
Skye_Soar equipped a weapon named Sword

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : equip
Enter your Product code ['q' to exit]
				******** Showing Inventory Equipable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Breastplate         3                   350.0               breastplate         Armor               600.0               1                   

Enter your input : breastplate
Skye_Soar equipped an armor of Breastplate

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : equip
Enter your Product code ['q' to exit]
				******** Showing Inventory Equipable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Breastplate         3                   350.0               breastplate         Armor               600.0               1                   

Enter your input : q

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : potion
Enter your potion code ['q' to exit]
				******** Showing Inventory Healable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Ambrosia            8                   1000.0              ambrosia            Potion              150.0               HP/MP/STRENGTH/DEXT1                   

Enter your input : ambrosia
Skye_Soar used a Ambrosia and recovered HP/MP/STRENGTH/DEXTERITY/AGILITY by 150.0

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : potion
Enter your potion code ['q' to exit]
				******** Showing Inventory Healable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            

Enter your input : q

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : info

				******** Skye_Soar Inventory Items ********
				******** Showing Inventory Equipable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Breastplate         3                   350.0               breastplate         Armor               600.0               1                   

				******** Showing Inventory Healable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            

				******** Showing Inventory Castable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               1                   


				******** Skye_Soar Hero(s) Health ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Skye_Soar           200/100             1500/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               


Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : attack
Skye_Soar attacked the BigBad-Wolf for 275 damage!
Skye_Soar attacked the Casper for 275 damage!
Casper Fainted!
Skye_Soar attacked the BigBad-Wolf for 275 damage!

Hit Enter ...


				******** Monster Turn ********
BigBad-Wolf attacked the Amaryllis_Astra for 50 damage!
Amaryllis_Astra Fainted!
BigBad-Wolf attacked the Skye_Soar for 50 damage!
BigBad-Wolf attacked the Eunoia_Cyn for 50 damage!
Eunoia_Cyn Fainted!

Hit Enter ...

    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     0/100               750/650             NO                  650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           750/100             1500/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               
[3] Eunoia_Cyn          0/100               600/400             NO                  700                 6                   600                 800                                                         2500.0              

    MONSTER Name        HP                  DAMAGE              DEFENCE             DODGE               
[1] BigBad-Wolf         75                  150                 250                 15                  
[2] Casper              0                   100                 100                 50                  
[3] BigBad-Wolf         75                  150                 250                 15                  

				******** Starting Round #1 ********

				******** Hero's Turn ********
Choose your hero to make move
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     0/100               750/650             NO                  650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           750/100             1500/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               
[3] Eunoia_Cyn          0/100               600/400             NO                  700                 6                   600                 800                                                         2500.0              

Enter a Integer : 2

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : cast
Enter your Spell code ['q' to exit]
				******** Showing Inventory Castable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Spark_Needles       2                   500.0               spark_needles       LightningSpell      600.0               200.0               1                   

Enter your input : spart_needles
INVALID, Please enter valid Product code!
Try again!
Enter your input : spark_needles
Applying Spell Effects on Monsters
Hitting Lightning Spell on BigBad-Wolf
Hitting Lightning Spell on Casper
Hitting Lightning Spell on BigBad-Wolf

Hit Enter ...


				******** Monster Turn ********
BigBad-Wolf attacked the Amaryllis_Astra for 50 damage!
Amaryllis_Astra Fainted!
BigBad-Wolf attacked the Skye_Soar for 50 damage!
BigBad-Wolf attacked the Eunoia_Cyn for 50 damage!
Eunoia_Cyn Fainted!

Hit Enter ...


    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     0/100               750/650             NO                  650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           1300/100            1300/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               
[3] Eunoia_Cyn          0/100               600/400             NO                  700                 6                   600                 800                                                         2500.0              

    MONSTER Name        HP                  DAMAGE              DEFENCE             DODGE               
[1] BigBad-Wolf         75                  150                 250                 15                  
[2] Casper              0                   100                 100                 50                  
[3] BigBad-Wolf         75                  150                 250                 15                  

				******** Starting Round #2 ********

				******** Hero's Turn ********
Choose your hero to make move
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     0/100               750/650             NO                  650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           1300/100            1300/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               
[3] Eunoia_Cyn          0/100               600/400             NO                  700                 6                   600                 800                                                         2500.0              

Enter a Integer : INVALID, Please enter a Integer!
Try again!
Enter a Integer : 2

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : info

				******** Skye_Soar Inventory Items ********
				******** Showing Inventory Equipable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            
Breastplate         3                   350.0               breastplate         Armor               600.0               1                   

				******** Showing Inventory Healable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            

				******** Showing Inventory Castable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            


				******** Skye_Soar Hero(s) Health ********
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Skye_Soar           1300/100            1300/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               


Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : attack
Skye_Soar attacked the BigBad-Wolf for 275 damage!
Skye_Soar attacked the Casper for 275 damage!
Casper Fainted!
Skye_Soar attacked the BigBad-Wolf for 275 damage!

Hit Enter ...


				******** Monster Turn ********
BigBad-Wolf attacked the Amaryllis_Astra for 50 damage!
Amaryllis_Astra Fainted!
BigBad-Wolf attacked the Skye_Soar for 50 damage!
BigBad-Wolf attacked the Eunoia_Cyn for 50 damage!
Eunoia_Cyn Fainted!

Hit Enter ...

    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     0/100               750/650             NO                  650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           1850/100            1300/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               
[3] Eunoia_Cyn          0/100               600/400             NO                  700                 6                   600                 800                                                         2500.0              

    MONSTER Name        HP                  DAMAGE              DEFENCE             DODGE               
[1] BigBad-Wolf         50                  150                 250                 15                  
[2] Casper              0                   100                 100                 50                  
[3] BigBad-Wolf         50                  150                 250                 15                  

				******** Starting Round #3 ********

				******** Hero's Turn ********
Choose your hero to make move
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     0/100               750/650             NO                  650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           1850/100            1300/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               
[3] Eunoia_Cyn          0/100               600/400             NO                  700                 6                   600                 800                                                         2500.0              

Enter a Integer : 2

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : cast
Enter your Spell code ['q' to exit]
				******** Showing Inventory Castable ********
Product Name        Level               Price               Product Code        Type                Impact Value        More Info           Quantity            

Enter your input : q

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : attak
Invalid, Please enter a valid Move!
Try again!
Enter your input : attack
Skye_Soar attacked the BigBad-Wolf for 275 damage!
Skye_Soar attacked the Casper for 275 damage!
Casper Fainted!
Skye_Soar attacked the BigBad-Wolf for 275 damage!

Hit Enter ...


				******** Monster Turn ********
BigBad-Wolf attacked the Amaryllis_Astra for 50 damage!
Amaryllis_Astra Fainted!
BigBad-Wolf attacked the Skye_Soar for 50 damage!
BigBad-Wolf attacked the Eunoia_Cyn for 50 damage!
Eunoia_Cyn Fainted!

Hit Enter ...

    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     0/100               750/650             NO                  650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           2400/100            1300/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               
[3] Eunoia_Cyn          0/100               600/400             NO                  700                 6                   600                 800                                                         2500.0              

    MONSTER Name        HP                  DAMAGE              DEFENCE             DODGE               
[1] BigBad-Wolf         25                  150                 250                 15                  
[2] Casper              0                   100                 100                 50                  
[3] BigBad-Wolf         25                  150                 250                 15                  

				******** Starting Round #4 ********

				******** Hero's Turn ********
Choose your hero to make move
    HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
[1] Amaryllis_Astra     0/100               750/650             NO                  650                 5                   650                 650                 Product<sword>                          1000.0              
[2] Skye_Soar           2400/100            1300/1150           YES                 850                 5                   650                 550                 Product<sword>      Breastplate         150.0               
[3] Eunoia_Cyn          0/100               600/400             NO                  700                 6                   600                 800                                                         2500.0              

Enter a Integer : 2

Enter your move : [ATTACK/CAST/POTION/EQUIP/INFO]
Enter your input : attack
Skye_Soar attacked the BigBad-Wolf for 275 damage!
BigBad-Wolf Fainted!
Skye_Soar attacked the Casper for 275 damage!
Casper Fainted!
Skye_Soar attacked the BigBad-Wolf for 275 damage!
BigBad-Wolf Fainted!

Hit Enter ...


				******** All Monsters Died ********

Applied Wining Effects
HERO Name           HP                  MP                  IS ALIVE            STRENGTH            EXPERIENCE          DEXTERITY           AGILITY             In-Hand WEAPON(s)   In-Hand ARMOR       GOLD                
Amaryllis_Astra     50/100              1125/650            YES                 650                 5                   650                 650                 Product<sword>                          1000.0              
Skye_Soar           2400/100            1300/1150           YES                 850                 11                  650                 550                 Product<sword>      Breastplate         250.0               
Eunoia_Cyn          50/100              900/400             YES                 700                 6                   600                 800                                                         2500.0              

				------------------------------------------------
				|     |  M  |  M  |  M  |  X  |     |  X  |  M  |
				------------------------------------------------
				|     |     |  X  |  M  |  M  |  X  |     |  X  |
				------------------------------------------------
				|     |  X  |     |  X  |     |     |  M  |     |
				------------------------------------------------
				|     |     |     |     |  M  |  M  |     |     |
				------------------------------------------------
				|     |     |  X  |  P* |  X  |  M  |     |  X  |
				------------------------------------------------
				|  X  |  M  |     |     |  M  |     |     |  M  |
				------------------------------------------------
				|     |     |  M  |  M  |     |     |     |  M  |
				------------------------------------------------
				|     |     |     |     |  X  |  M  |     |  M  |
				------------------------------------------------

M --> Represents the Market Space
X --> Represents the Inaccessible Space
  --> Represents the Common Space
P* --> Represents Player position


Please enter your move [W/w/A/a/S/s/D/d/Q/q/I/i/M/m]
Enter your Move :  q

Hope you enjoyed the LegendsGame game!!!

Do you want to REPLAY!!! [YES/yes/y/NO/no/n]
Enter your input : n
