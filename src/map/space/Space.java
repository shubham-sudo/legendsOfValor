package map.space;

import creature.Creature;


/**
 * Space interface for all type of spaces
 */
public interface Space {
    String ANSI_RESET = "\u001B[0m";
    String EMPTY_SPACE = "     ";
    String CREATURE_CHARACTER_SPACE = "  ";

    /**
     * Check if space is free and can be occupied by this creature
     * @param creature creature object
     * @return boolean
     */
    boolean isSafeToOccupy(Creature creature);

    /**
     * Occupy the space if it is safe to occupy
     * @param creature creature who want to occupy
     * @throws IllegalAccessException If invalid operation
     */
    void occupy(Creature creature) throws IllegalAccessException;

    /**
     * Vacant this space
     * @param creature creature who is moving
     */
    void vacant(Creature creature);

    /**
     * Check if opponent is nearby
     * @param creature creature object
     * @return boolean
     */
    boolean hasOpponent(Creature creature);

    /**
     * Get the opponent creature nearby
     * @param creature creature object to check opponent
     * @return opponent Creature
     */
    Creature getOpponent(Creature creature);

    /**
     * Return the background color for this particular space
     * @return String
     */
    String bgColor();

    /**
     * if creature is on this space return text with creature character
     * else return empty padded spaces to not break the board structure.
     * @return String
     */
    String displayValue();

    /**
     * Get the display value for this space
     * which will be used on board
     *
     * @param hero hero if present on space
     * @param monster monster if present on space
     * @return String
     */
    static String getValue(Creature hero, Creature monster) {
        if (hero == null && monster == null){
            return EMPTY_SPACE;
        } else {
            String[] displayVal = new String[]{
                    hero != null ? hero.displayValue() : CREATURE_CHARACTER_SPACE,
                    monster != null ? monster.displayValue() : CREATURE_CHARACTER_SPACE
            };
            return String.join(" ", displayVal);
        }
    }
}
