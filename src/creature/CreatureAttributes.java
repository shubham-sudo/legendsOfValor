package creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * An enum which helps in comparison for increase and decrease of attributes
 */
public enum CreatureAttributes {
    HP,
    MP,
    STRENGTH,
    DEXTERITY,
    EXPERIENCE,
    AGILITY;

    /**
     * for printing these attributes in console
     * @param attributesAffected set of attributes to show
     * @return convert all attributes in string separated with '/'
     */
    public static String flatAttributesAffected(Set<CreatureAttributes> attributesAffected){
        List<String> stringList = new ArrayList<>();
        for(CreatureAttributes creatureAttributes : attributesAffected){
            stringList.add(creatureAttributes.toString());
        }
        return String.join("/", stringList);
    }
}
