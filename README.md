# Legends Of Valor
Legends of Valor isa MOBA (multiplayer online battle arena)-like game. The player will control a team of 3 heroes who will attempt to fight their way through to the monsters’ Nexus.The heroes win if any of them reach the monsters’ Nexus. The heroes lose if any monster reaches the heroes’ Nexus.

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
