# CS611 - Assignment Number - 4
## Legends of Valor
---------------------------------------------------------------------------
SHUBHAM KAUSHIK
SIYI DU

## Files
---------------------------------------------------------------------------
src
├── controller
│     ├── BattleController.java : Battle controller to do attack, cast, potion, equip type of moves. It is a singleton class
│     ├── GameController.java : An interface for providing default api and functions for running game(s)
│     ├── GameInputManager.java : Define the default methods which a manager need to get the inputs from console
│     ├── LegendsOfValorController.java : Controller for all the legends of valor game moves and interaction with the user
│     ├── MainController.java : This is the main controller used in Main.java class which will also run the game user wants to run
│     ├── MarketController.java : Market is another small mini-game which implements GameController
│     └── ProductController.java : Controller which gives all operation to operate on products (Items)
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
│     ├── CreatureFactory.java : Factory class for creating creatures of different types. This class uses DataClass a data source for creating creatures
│     ├── CreaturesFactory.java : This factory class is used by the client to get the heroes and monsters
│     ├── MapFactory.java : MapFactory is to create a several type of maps. The idea was to use the easy, medium and hard level to generate different type of maps
│     ├── MarketFactory.java : Factory for market to show on market space tile
│     └── ProductFactory.java : Product factory to create several type of products
├── game
│     ├── Game.java : Abstract Game class for all the games
│     └── LegendsOfValor.java : Legends of valor game is played by this class. It manages all the operation which can happen in this game. It computes the safest moves and also generate move for computer player
├── inventory
│     ├── CreatureInventory.java : Child class of the Inventory to define different function for the creature
│     ├── Inventory.java : Abstract inventory for Market and Creatures
│     └── MarketInventory.java : MarketInventory is the child class of Inventory for markets
├── Main.java : Main class
├── map
│     ├── BoardMap.java : Board class to represent the board in the game
│     ├── lane
│     │     ├── ImpassibleLane.java : Impassible lanes are like walls which are composed of Inaccessible Spaces. This can be further used to remove an inaccessible space from wall for an upgraded creature.
│     │     ├── Lane.java : Base class for different types of lanes
│     │     └── PassableLane.java : Passable lanes are composed of special and accessible plain spaces
│     ├── Position.java : Position is to represent the position of creature on board
│     └── space
│         ├── BushSpace.java : Bush space where the dexterity of creatures increases
│         ├── CaveSpace.java : Cave space where the agility of creatures increases
│         ├── FortressSpace.java : Fortress space is just like the Nexus for monster, but this is representing the heroes nexus
│         ├── InaccessibleSpace.java : Inaccessible space which is used for walls between lanes
│         ├── KoulouSpace.java : Koulou space where the strength of creatures increases
│         ├── NexusSpace.java : Nexus space which is a home for Creature
│         ├── NormalSpace.java : Base class for all types of Normal spaces
│         ├── PlainSpace.java : Plane space which is a normal space
│         ├── Space.java : Space interface for all type of spaces
│         └── SpecialSpace.java : Base class for all type of special spaces
├── market
│     └── Market.java : Market is to do purchase and sell
├── move
│     ├── GameMove.java : Game Moves UP, DOWN, RIGHT, LEFT, MARKET, ATTACK, CAST, POTION, EQUIP, INFO, EXIT
│     ├── MarketMove.java : Enum for possible moves in market
│     └── Move.java : Move which consists of all details to make a move
├── player
│     └── Player.java : Player who plays in game also implements Iterator to iterate over all creatures. For our case it is a list of creatures.
├── product
│     ├── Armor.java : Armor product class which save with some damage
│     ├── Consumable.java : An interface for all consumable type of products
│     ├── Equipable.java : An interface for all wearable products
│     ├── FireSpell.java : Fire spell is a Spell which reduce the defence of the opponent
│     ├── IceSpell.java : Ice spell is a Spell which reduce the damage to the opponent
│     ├── LightningSpell.java : Lightning spell is a Spell which reduce the agility of the opponent
│     ├── Potion.java : Potion products are healer to heal some attributes of the creature
│     ├── Product.java : Base abstract product class for all the products creature can have
│     ├── ProductType.java : List the types of product for the game
│     ├── SpellEffects.java : An interface to spell effects for the different type of spells
│     ├── Spell.java : Spell are used to decrease few attributes of the opponent. This is the abstract class for different type of spells
│     └── Weapon.java : Weapon product to fight against opponent
├── PubSub
│     ├── OneRoundObserver.java : Observer apply battle one round effects or game (after 8 rounds)
│     ├── OneRoundPublisher.java : Publisher for one round of the battle or game
│     ├── GameObserver.java : An interface for all observer designs
│     ├── GamePublisher.java : An interface for all Game publishers
│     ├── GameWinObserver.java : observe the game state if anyone is winning
│     └── GameWinPublisher.java : Publish events for change in move of any creature for nexus
├── utility
│     ├── CreatureDataClass.java : DataClass for all creatures, the data is hardcoded here. When game run it will pick data from here
│     ├── ProductDataClass.java : Dataclass for the hard coded products. This will be used when game runs
│     ├── SingletonScanner.java : Single scanner object across the game
│     ├── StandardOutput.java : Standard Output helps to generate nice looking table and aligning stuff in console.
│     └── Utility.java : Utility class helps in printing data in table formats
└── wallet
    └── Wallet.java : Wallet class gives the feature of wallet to the creature
          

## Notes
---------------------------------------------------------------------------
1. No config files used as per the current structure.
2. (a) Implemented Pub-Sub model for performing Game state updates in case of wining & battle impacts on creatures.
   (b) Inventory is an abstract class, which is has-a relation with Heros and Market. Abstract Inventory does the basic operations and MarketInventory & CreatureInventory does their inventory specific jobs.
   (c) Defined Player with couple of heroes. We can add as many heroes as we want, but the lane in starting would show only heroes upto the number of lanes present.
   (c) Strategy of occupying different spaces is quite different, hence used a strategy pattern in this.
3. The rendering of board is very simple with colored spaces and labels where 'L-1' represents the lane 1 and so on.
4. GameController is an interface which gives a basic functionality for Legends of Valor and Battle like games.
5. The BoardMap is formed with lanes and they are adjustable to certain limits. We can have upto 6 lanes as shown in example too.

## ADDITIONAL
----------------------------------------------------------------------------
We have also added couple of images of the board and moves which you can see in README.md
when you open in some IDE which supports README.md view. We encourage you to check it out.

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
[1] Legends Of Valor
Enter your input : 1

		Legends of Valor isa MOBA (multiplayer online battle arena)-like game. 
		The player will control a team of 3 heroes who will attempt to fight their way 
		through to the monsters’ Nexus.The heroes win if any of them reach the monsters’ Nexus. 
		The heroes lose if any monster reaches the heroes’ Nexus.

Default # of Lanes are [3],
Do you want to change it? [YES/yes/y/NO/no/n]
Enter your input : y
Enter a Integer : 20
Please enter a valid # of lanes [3 - 6]
Try again!
Enter a Integer : 6

Enter Player Name
Enter your input : shubham

Enter Number for the type of Hero!
[1] Paladins
[2] Sorcerers
[3] Warriors
Enter a Integer : 1

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          600.0          500.0          400.0          5.0/10         
2 Skoraeus_Sto 1              100.0/100.0    250.0          650.0          600.0          350.0          4.0/10         
3 Caliber_Heis 1              100.0/100.0    400.0          400.0          400.0          400.0          8.0/10         
4 Sehanine_Moo 1              100.0/100.0    300.0          750.0          700.0          700.0          7.0/10         
5 Parzival     1              100.0/100.0    300.0          750.0          650.0          700.0          7.0/10         
6 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          500.0          5.0/10


Enter a Integer : 1

Enter Number for the type of Hero!
[1] Paladins
[2] Sorcerers
[3] Warriors
Enter a Integer : 1

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          500.0          5.0/10         
2 Garl_Glitter 1              100.0/100.0    100.0          600.0          500.0          400.0          5.0/10         
3 Sehanine_Moo 1              100.0/100.0    300.0          750.0          700.0          700.0          7.0/10         
4 Caliber_Heis 1              100.0/100.0    400.0          400.0          400.0          400.0          8.0/10         
5 Skoraeus_Sto 1              100.0/100.0    250.0          650.0          600.0          350.0          4.0/10         
6 Parzival     1              100.0/100.0    300.0          750.0          650.0          700.0          7.0/10


Enter a Integer : 1

Enter Number for the type of Hero!
[1] Paladins
[2] Sorcerers
[3] Warriors
Enter a Integer : 1

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Sehanine_Moo 1              100.0/100.0    300.0          750.0          700.0          700.0          7.0/10         
2 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          500.0          5.0/10         
3 Caliber_Heis 1              100.0/100.0    400.0          400.0          400.0          400.0          8.0/10         
4 Skoraeus_Sto 1              100.0/100.0    250.0          650.0          600.0          350.0          4.0/10         
5 Garl_Glitter 1              100.0/100.0    100.0          600.0          500.0          400.0          5.0/10         
6 Parzival     1              100.0/100.0    300.0          750.0          650.0          700.0          7.0/10


Enter a Integer : 1

Enter Number for the type of Hero!
[1] Paladins
[2] Sorcerers
[3] Warriors
Enter a Integer : 1

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     1              100.0/100.0    300.0          750.0          650.0          700.0          7.0/10         
2 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          500.0          5.0/10         
3 Caliber_Heis 1              100.0/100.0    400.0          400.0          400.0          400.0          8.0/10         
4 Skoraeus_Sto 1              100.0/100.0    250.0          650.0          600.0          350.0          4.0/10         
5 Garl_Glitter 1              100.0/100.0    100.0          600.0          500.0          400.0          5.0/10         
6 Sehanine_Moo 1              100.0/100.0    300.0          750.0          700.0          700.0          7.0/10


Enter a Integer : 1

Enter Number for the type of Hero!
[1] Paladins
[2] Sorcerers
[3] Warriors
Enter a Integer : 1

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Skoraeus_Sto 1              100.0/100.0    250.0          650.0          600.0          350.0          4.0/10         
2 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          500.0          5.0/10         
3 Parzival     1              100.0/100.0    300.0          750.0          650.0          700.0          7.0/10         
4 Garl_Glitter 1              100.0/100.0    100.0          600.0          500.0          400.0          5.0/10         
5 Sehanine_Moo 1              100.0/100.0    300.0          750.0          700.0          700.0          7.0/10         
6 Caliber_Heis 1              100.0/100.0    400.0          400.0          400.0          400.0          8.0/10


Enter a Integer : 1

Enter Number for the type of Hero!
[1] Paladins
[2] Sorcerers
[3] Warriors
Enter a Integer : 1

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Skoraeus_Sto 1              100.0/100.0    250.0          650.0          600.0          350.0          4.0/10         
2 Sehanine_Moo 1              100.0/100.0    300.0          750.0          700.0          700.0          7.0/10         
3 Caliber_Heis 1              100.0/100.0    400.0          400.0          400.0          400.0          8.0/10         
4 Garl_Glitter 1              100.0/100.0    100.0          600.0          500.0          400.0          5.0/10         
5 Parzival     1              100.0/100.0    300.0          750.0          650.0          700.0          7.0/10         
6 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          500.0          5.0/10


Enter a Integer : 1
Heroes are all-set to move on map!

Hit Enter ...


    

1	   M1             M2             M3             M4             M5             M6     
2	                                                                                     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	                                                                                     
8	     H1             H2             H3             H4             H5             H6   
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

######## Game Started ########

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     1              100.0/100.0    300.0          750.0          715.0          700.0          7.0/10

Hit Enter ...


    
1	   M1             M2             M3             M4             M5             M6     
2	                                                                                     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1                                                                              
8	                    H2             H3             H4             H5             H6   
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H2
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          600.0          500.0          440.0          5.0/10

Hit Enter ...


    
1	   M1             M2             M3             M4             M5             M6     
2	                                                                                     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2                                                               
8	                                   H3             H4             H5             H6   
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H3
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          550.0          500.0          5.0/10

Hit Enter ...


    
1	   M1             M2             M3             M4             M5             M6     
2	                                                                                     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3                                                
8	                                                  H4             H5             H6   
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H4
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Sehanine_Moo 1              100.0/100.0    300.0          825.0          700.0          700.0          7.0/10

Hit Enter ...


    
1	   M1             M2             M3             M4             M5             M6     
2	                                                                                     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3             H4                                 
8	                                                                 H5             H6   
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H5
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          550.0          500.0          500.0          5.0/10

Hit Enter ...


    
1	   M1             M2             M3             M4             M5             M6     
2	                                                                                     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3             H4             H5                  
8	                                                                                H6   
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H6
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          660.0          500.0          400.0          5.0/10

Hit Enter ...


    
1	   M1             M2             M3             M4             M5             M6     
2	                                                                                     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3             H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M1 playing his move

Hit Enter ...
u

1	                  M2             M3             M4             M5             M6     
2	   M1                                                                                
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3             H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M2 playing his move

Hit Enter ...


1	                                 M3             M4             M5             M6     
2	   M1             M2                                                                 
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3             H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M3 playing his move

Hit Enter ...


1	                                                M4             M5             M6     
2	   M1             M2             M3                                                  
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3             H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M4 playing his move

Hit Enter ...


1	                                                               M5             M6     
2	   M1             M2             M3             M4                                   
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3             H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M5 playing his move

Hit Enter ...


1	                                                                              M6     
2	   M1             M2             M3             M4             M5                    
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3             H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M6 playing his move

Hit Enter ...


1	                                                                                     
2	   M1             M2             M3             M4             M5             M6     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	                                                                                     
7	     H1             H2             H3             H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move :
INVALID, Please enter a Character!
Try again!
Enter your Move : a
Invalid Move, Try again!!!
Opponent is far away, Save you moves!

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     1              100.0/100.0    300.0          750.0          650.0          770.0          7.0/10

Hit Enter ...


1	                                                                                     
2	   M1             M2             M3             M4             M5             M6     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	     H1                                                                              
7	                    H2             H3             H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H2
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          660.0          500.0          40.0           5.0/10

Hit Enter ...


    
1	                                                                                     
2	   M1             M2             M3             M4             M5             M6     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	     H1             H2                                                               
7	                                   H3             H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H3
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          550.0          5.0/10

Hit Enter ...


1	                                                                                     
2	   M1             M2             M3             M4             M5             M6     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3                                                
7	                                                  H4             H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H4
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Sehanine_Moo 1              100.0/100.0    300.0          825.0          700.0          700.0          7.0/10

Hit Enter ...


1	                                                                                     
2	   M1             M2             M3             M4             M5             M6     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3             H4                                 
7	                                                                 H5             H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H5
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          550.0          500.0          5.0/10

Hit Enter ...


1	                                                                                     
2	   M1             M2             M3             M4             M5             M6     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3             H4             H5                  
7	                                                                                H6   
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H6
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          660.0          500.0          400.0          5.0/10

Hit Enter ...


1	                                                                                     
2	   M1             M2             M3             M4             M5             M6     
3	                                                                                     
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3             H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M1 playing his move

Hit Enter ...


1	                                                                                     
2	                  M2             M3             M4             M5             M6     
3	   M1                                                                                
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3             H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M2 playing his move

Hit Enter ...


1	                                                                                     
2	                                 M3             M4             M5             M6     
3	   M1             M2                                                                 
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3             H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M3 playing his move

Hit Enter ...


1	                                                                                     
2	                                                M4             M5             M6     
3	   M1             M2             M3                                                  
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3             H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M4 playing his move

Hit Enter ...


1	                                                                                     
2	                                                               M5             M6     
3	   M1             M2             M3             M4                                   
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3             H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M5 playing his move

Hit Enter ...


1	                                                                                     
2	                                                                              M6     
3	   M1             M2             M3             M4             M5                    
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3             H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M6 playing his move

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	   M1             M2             M3             M4             M5             M6     
4	                                                                                     
5	                                                                                     
6	     H1             H2             H3             H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move :
INVALID, Please enter a Character!
Try again!
Enter your Move :
INVALID, Please enter a Character!
Try again!
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     1              100.0/100.0    300.0          750.0          650.0          77.0           7.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	   M1             M2             M3             M4             M5             M6     
4	                                                                                     
5	     H1                                                                              
6	                    H2             H3             H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H2
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          600.0          550.0          40.0           5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	   M1             M2             M3             M4             M5             M6     
4	                                                                                     
5	     H1             H2                                                               
6	                                   H3             H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H3
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          55.0           5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	   M1             M2             M3             M4             M5             M6     
4	                                                                                     
5	     H1             H2             H3                                                
6	                                                  H4             H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H4
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Sehanine_Moo 1              100.0/100.0    300.0          750.0          770.0          700.0          7.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	   M1             M2             M3             M4             M5             M6     
4	                                                                                     
5	     H1             H2             H3             H4                                 
6	                                                                 H5             H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H5
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          550.0          500.0          5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	   M1             M2             M3             M4             M5             M6     
4	                                                                                     
5	     H1             H2             H3             H4             H5                  
6	                                                                                H6   
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H6
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          600.0          550.0          400.0          5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	   M1             M2             M3             M4             M5             M6     
4	                                                                                     
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M1 playing his move

Hit Enter ...
u

1	                                                                                     
2	                                                                                     
3	                  M2             M3             M4             M5             M6     
4	   M1                                                                                
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M2 playing his move

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                 M3             M4             M5             M6     
4	   M1             M2                                                                 
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M3 playing his move

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                M4             M5             M6     
4	   M1             M2             M3                                                  
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M4 playing his move

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                               M5             M6     
4	   M1             M2             M3             M4                                   
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M5 playing his move

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                              M6     
4	   M1             M2             M3             M4             M5                    
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M6 playing his move

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	   M1             M2             M3             M4             M5             M6     
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move : a
Parzival attacked Natsunomeryu with damage of 137.5

Natsunomeryu fainted by the attack of Parzival

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     1              100.0/100.0    300.0          750.0          650.0          77.0           9.0/10

Hit Enter ...


    
1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                  M2             M3             M4             M5             M6     
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H2
Enter your Move : a
Garl_Glittergold attacked Casper with damage of 170.0

Casper fainted by the attack of Garl_Glittergold

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          600.0          550.0          40.0           7.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                                 M3             M4             M5             M6     
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H3
Enter your Move : a
Amaryllis_Astra attacked Natsunomeryu with damage of 25.0

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          55.0           5.0/10

Hit Enter ...
a

1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                                 M3             M4             M5             M6     
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H4
Enter your Move : a
Sehanine_Moonbow attacked BigBad-Wolf with damage of 87.5

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Sehanine_Moo 1              100.0/100.0    300.0          750.0          770.0          700.0          7.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                                 M3             M4             M5             M6     
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H5
Enter your Move : a
Amaryllis_Astra attacked BigBad-Wolf
Nice Job!, BigBad-Wolf defended Amaryllis_Astra fully!

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          550.0          500.0          5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                                 M3             M4             M5             M6     
5	     H1             H2             H3             H4             H5             H6   
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H6
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          600.0          500.0          440.0          5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                                 M3             M4             M5             M6H6   
5	     H1             H2             H3             H4             H5                  
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M3 playing his move

Hit Enter ...


    
1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                                                M4             M5             M6H6   
5	     H1             H2           M3H3             H4             H5                  
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M4 playing his move

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                                                               M5             M6H6   
5	     H1             H2           M3H3           M4H4             H5                  
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M5 playing his move

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                                                                              M6H6   
5	     H1             H2           M3H3           M4H4           M5H5                  
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M6 playing his move
Casper attacked Garl_Glittergold with damage of 40.0

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	                                                                              M6H6   
5	     H1             H2           M3H3           M4H4           M5H5                  
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     1              100.0/100.0    300.0          750.0          650.0          7.7            9.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	     H1                                                                       M6H6   
5	                    H2           M3H3           M4H4           M5H5                  
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H2
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          660.0          500.0          40.0           7.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	     H1             H2                                                        M6H6   
5	                                 M3H3           M4H4           M5H5                  
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H3
Enter your Move : a
Amaryllis_Astra attacked Natsunomeryu with damage of 25.0

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          500.0          55.0           5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	     H1             H2                                                        M6H6   
5	                                 M3H3           M4H4           M5H5                  
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H4
Enter your Move : a
Sehanine_Moonbow attacked BigBad-Wolf with damage of 87.5

BigBad-Wolf fainted by the attack of Sehanine_Moonbow

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Sehanine_Moo 1              100.0/100.0    300.0          750.0          770.0          700.0          9.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	     H1             H2                                                        M6H6   
5	                                 M3H3             H4           M5H5                  
6	                                                                                     
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H5
Enter your Move : u
Invalid Move, Try again!!!
Cannot move UP/DOWN when another creature near by!

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H5
Enter your Move : d

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          550.0          500.0          5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	     H1             H2                                                        M6H6   
5	                                 M3H3             H4           M5                    
6	                                                                 H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H6
Enter your Move : a
Garl_Glittergold attacked Casper with damage of 170.0

Casper fainted by the attack of Garl_Glittergold

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              66.0/100.0     110.0000000000 600.0          500.0          440.0          7.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	     H1             H2                                                          H6   
5	                                 M3H3             H4           M5                    
6	                                                                 H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M3 playing his move
Natsunomeryu attacked Amaryllis_Astra with damage of 60.0

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	                                                                                     
4	     H1             H2                                                          H6   
5	                                 M3H3             H4           M5                    
6	                                                                 H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M5 playing his move

Hit Enter ...



1	                                                                                     
2	                                                                                     
3	                                                                                     
4	     H1             H2                                                          H6   
5	                                 M3H3             H4                                 
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move : INVALID, Please enter a Character!
Try again!
Enter your Move : a
Invalid Move, Try again!!!
Opponent is far away, Save you moves!

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     1              100.0/100.0    300.0          825.0          650.0          0.700000000000 9.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	     H1                                                                              
4	                    H2                                                          H6   
5	                                 M3H3             H4                                 
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H2
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          600.0          500.0          44.0           7.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	     H1             H2                                                               
4	                                                                                H6   
5	                                 M3H3             H4                                 
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H3
Enter your Move : a
Amaryllis_Astra attacked Natsunomeryu with damage of 25.0

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              44.0/100.0     550.0          500.0          500.0          55.0           5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	     H1             H2                                                               
4	                                                                                H6   
5	                                 M3H3             H4                                 
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H4
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Sehanine_Moo 1              100.0/100.0    300.0          750.0          700.0          770.0          9.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	     H1             H2                                                               
4	                                                  H4                            H6   
5	                                 M3H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H5
Enter your Move : a
Amaryllis_Astra attacked BigBad-Wolf
Nice Job!, BigBad-Wolf defended Amaryllis_Astra fully!

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              100.0/100.0    500.0          500.0          550.0          500.0          5.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	     H1             H2                                                               
4	                                                  H4                            H6   
5	                                 M3H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H6
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              66.0/100.0     110.0000000000 660.0          500.0          40.0           7.0/10

Hit Enter ...


1	                                                                                     
2	                                                                                     
3	     H1             H2                                                          H6   
4	                                                  H4                                 
5	                                 M3H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M3 playing his move
Natsunomeryu attacked Amaryllis_Astra with damage of 60.0

Hit Enter ...


    
1	   M7             M8             M9             M10             M11             M12     
2	                                                                                     
3	     H1             H2                                                          H6   
4	                                                  H4                                 
5	                                 M3H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M5 playing his move
BigBad-Wolf attacked Amaryllis_Astra with damage of 100.0

Hit Enter ...


1	   M7             M8             M9             M10             M11             M12     
2	                                                                                     
3	     H1             H2                                                          H6   
4	                                                  H4                                 
5	                                 M3H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     1              100.0/100.0    300.0          825.0          650.0          0.700000000000 9.0/10

Hit Enter ...



1	   M7             M8             M9             M10             M11             M12     
2	     H1                                                                              
3	                    H2                                                          H6   
4	                                                  H4                                 
5	                                 M3H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H2
Enter your Move : INVALID, Please enter a Character!
Try again!
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          600.0          550.0          4.0            7.0/10

Hit Enter ...


1	   M7             M8             M9             M10             M11             M12     
2	     H1             H2                                                               
3	                                                                                H6   
4	                                                  H4                                 
5	                                 M3H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H3
Enter your Move : a
Amaryllis_Astra attacked Natsunomeryu with damage of 25.0

Natsunomeryu fainted by the attack of Amaryllis_Astra

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              0.0/100.0      605.0          500.0          500.0          55.0           7.0/10

Hit Enter ...


1	   M7             M8             M9             M10             M11             M12     
2	     H1             H2                                                               
3	                                                                                H6   
4	                                                  H4                                 
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H4
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Sehanine_Moo 1              100.0/100.0    300.0          750.0          700.0          77.0           9.0/10

Hit Enter ...


1	   M7             M8             M9             M10             M11             M12     
2	     H1             H2                                                               
3	                                                  H4                            H6   
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H5
Enter your Move : a
Amaryllis_Astra attacked BigBad-Wolf
Nice Job!, BigBad-Wolf defended Amaryllis_Astra fully!

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              0.0/100.0      550.0          500.0          550.0          500.0          5.0/10

Hit Enter ...


1	   M7             M8             M9             M10             M11             M12     
2	     H1             H2                                                               
3	                                                  H4                            H6   
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H6
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              66.0/100.0     110.0000000000 600.0          550.0          40.0           7.0/10

Hit Enter ...


1	   M7             M8             M9             M10             M11             M12     
2	     H1             H2                                                          H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M5 playing his move
BigBad-Wolf attacked Amaryllis_Astra with damage of 100.0

Hit Enter ...


1	   M7             M8             M9             M10             M11             M12     
2	     H1             H2                                                          H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M7 playing his move

Hit Enter ...



1	                  M8             M9             M10             M11             M12     
2	   M7H1             H2                                                          H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M8 playing his move

Hit Enter ...

1	                                 M9             M10             M11             M12     
2	   M7H1           M8H2                                                          H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M9 playing his move

Hit Enter ...


1	                                                M10             M11             M12     
2	   M7H1           M8H2           M9                                             H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M10 playing his move

Hit Enter ...


1	                                                               M11             M12     
2	   M7H1           M8H2           M9             M10                              H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M11 playing his move

Hit Enter ...


1	                                                                              M12     
2	   M7H1           M8H2           M9             M10             M11               H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M12 playing his move

Hit Enter ...


1	                                                                                     
2	   M7H1           M8H2           M9             M10             M11             M12H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move : a
Parzival attacked Casper with damage of 271.25

Casper fainted by the attack of Parzival

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     2              300.0/300.0    330.0          1691.25        1332.5         0.700000000000 11.0/20

Hit Enter ...


    
1	                                                                                     
2	     H1           M8H2           M9             M10             M11             M12H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H2
Enter your Move : a
Garl_Glittergold attacked Blinky
Nice Job!, Blinky defended Garl_Glittergold fully!

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              100.0/100.0    100.0          600.0          550.0          4.0            7.0/10

Hit Enter ...


1	                                                                                     
2	     H1           M8H2           M9             M10             M11             M12H6   
3	                                                  H4                                 
4	                                                                                     
5	                                   H3                                                
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H3
Enter your Move : a
Invalid Move, Try again!!!
Opponent is far away, Save you moves!

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H3
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              0.0/100.0      605.0          550.0          500.0          5.0            7.0/10

Hit Enter ...


1	                                                                                     
2	     H1           M8H2           M9             M10             M11             M12H6   
3	                                                  H4                                 
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H4
Enter your Move : a
Sehanine_Moonbow attacked Blinky
Nice Job!, Blinky defended Sehanine_Moonbow fully!

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Sehanine_Moo 1              100.0/100.0    300.0          750.0          700.0          77.0           9.0/10

Hit Enter ...


1	                                                                                     
2	     H1           M8H2           M9             M10             M11             M12H6   
3	                                                  H4                                 
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H5
Enter your Move : a
Amaryllis_Astra attacked BigBad-Wolf
Nice Job!, BigBad-Wolf defended Amaryllis_Astra fully!

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Amaryllis_As 1              0.0/100.0      605.0          500.0          550.0          500.0          5.0/10

Hit Enter ...


1	                                                                                     
2	     H1           M8H2           M9             M10             M11             M12H6   
3	                                                  H4                                 
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H6
Enter your Move : a
Garl_Glittergold attacked Natsunomeryu with damage of 70.0

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Garl_Glitter 1              66.0/100.0     110.0000000000 600.0          550.0          40.0           7.0/10

Hit Enter ...


1	                                                                                     
2	     H1           M8H2           M9             M10             M11             M12H6   
3	                                                  H4                                 
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M5 playing his move
BigBad-Wolf attacked Amaryllis_Astra with damage of 100.0

Hit Enter ...


1	                                                                                     
2	     H1           M8H2           M9             M10             M11             M12H6   
3	                                                  H4                                 
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M8 playing his move

Blinky dodged the attack by Garl_Glittergold
Blinky attacked Garl_Glittergold with damage of 390.0

Hit Enter ...


1	                                                                                     
2	     H1           M8H2           M9             M10             M11             M12H6   
3	                                                  H4                                 
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M9 playing his move

Hit Enter ...


1	                                                                                     
2	     H1           M8H2                          M10             M11             M12H6   
3	                                 M9               H4                                 
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M10 playing his move

Hit Enter ...


1	                                                                                     
2	     H1           M8H2                                         M11             M12H6   
3	                                 M9             M10H4                                 
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M11 playing his move

Hit Enter ...


1	                                                                                     
2	     H1           M8H2                                                        M12H6   
3	                                 M9             M10H4           M11                    
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Monster M12 playing his move
Natsunomeryu attacked Garl_Glittergold with damage of 40.0

Hit Enter ...


1	                                                                                     
2	     H1           M8H2                                                        M12H6   
3	                                 M9             M10H4           M11                    
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

Possible Moves [u]UP, [d]DOWN, [r]RIGHT, [l]LEFT, [m]MARKET, [q]EXIT,
[a]ATTACK, [c]CAST, [p]POTION, [e]EQUIP, [i]INFO, [b]RECALL, [t]TELEPORT
Enter move for H1
Enter your Move :
INVALID, Please enter a Character!
Try again!
Enter your Move : u

		######## Hero's ########
Name         Level          Health         Mana           Strength       Agility        Dexterity      Experience     
1 Parzival     2              300.0/300.0    330.0          750.0          1332.5         0.700000000000 11.0/20

Hit Enter ...


    
1	     H1                                                                              
2	                  M8H2                                                        M12H6   
3	                                 M9             M10H4           M11                    
4	                                   H3                                                
5	                                                                                     
6	                                                               M5H5                  
7	                                                                                     
8	                                                                                     
        L-1            L-2            L-3            L-4            L-5            L-6

Each lane (Lane-1, Lane-2, ..) column starts with 1 from left and increase one unit to left
Board colors represents the respective spaces
CaveSpace
FortressSpace
BushSpace
KoulouSpace
InaccessibleSpace
NexusSpace

######## Congratulations!!!, shubham Won this Game ########



# Objects Structure

- Terrain       Base class for Nexus and Fortress
  - Nexus       Responsible for generating monsters  (once hero enters here this heroes win)
  - Fortress    Responsible for heroes territory     (once monsters enters heroes loose)
QQ - Do we need both Nexus and Fortress ??
  
- Player        Base class for players, we have 3 heroes for one player

- Items (Product)
  - Spell
    - LightningSpell
    - IceSpell
    - FireSpell
  - Weapon
  - Armor
  - Potion

- Creature
  - Hero
  - Monster

- Lane
  - Impassible
  - Passable


- Space       I think we can drop this space and shift to Lanes (reason being lanes are also build of spaces)
  - Market
  - InAccessible
  - Common


NEW IDEAS

Observer patterns
  - Once any round finishes ... call the observer to regain some health and mana to all the live heroes
  - Once the battle ends ... call the observer to revive all the heroes who had fainted.
  - Once the battle ends ... call the observer to gain experience and gold to all those heroes who were left alive.
  - Update the Creature inventory once any hero CAST the spell or use Potion ... reason being that product is consumable


## IF GETS TIME TO IMPLEMENT  (Think this as backend stuff)
  - Controller who can add new products to the market
  - Controller who can add new Monsters to the Game
  - Controller who can add new Heroes to the Game