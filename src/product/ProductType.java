package product;

/**
 * An enum for different type of products of the Game
 */
public enum ProductType {
    WEAPON("Equipable"),
    ARMOR("Equipable"),
    SPELL("Castable"),
    POTION("Healable");

    private final String typeName;

    /**
     * Creates a new object
     * @param type given string type
     */
    ProductType(String type){
        this.typeName = type;
    }

    /**
     * return the type name of this enum
     * @return String
     */
    public String getTypeName() {
        return typeName;
    }
}
