package utility;

import java.util.Random;

/**
 * Utility class helps in printing data in table formats
 */
public class Utility {

    /**
     * Pad spaces if required or trim the content to show it in table
     * @param data data to use
     * @return string
     */
    public static String paddedString(String[] data){
        StringBuilder newData = new StringBuilder();
        for (String d : data){
            if (d != null) {
                if (d.length() > 20){
                    newData.append(d, 0, 19);
                }else {
                    newData.append(d).append(new String(new char[20 - d.length()]).replace("\0", " "));
                }
            } else {
                newData.append(new String(new char[20]).replace("\0", " "));
            }
        }
        return newData.toString();
    }

    /**
     * Roll a die to get the random value
     * @return float value
     */
    public static float rollDice(){
        return ((new Random().nextInt(100)) + 1);
    }

}
