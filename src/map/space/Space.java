package map.space;

import creature.Creature;


/**
 * Space interface for all type of spaces
 */
public interface Space {
    String ANSI_RESET = "\u001B[0m";
    String EMPTY_SPACE = "   ";  // TODO: Add something on special space to recognize.
    String CREATURE_CHARACTER_SPACE = "  ";

    boolean isSafeToOccupy(Creature creature);

    void occupy(Creature creature) throws IllegalAccessException;

    void vacant(Creature creature);

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
