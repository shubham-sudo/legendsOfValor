package utility;

import java.util.Scanner;

/**
 * Single scanner object across the game
 */
public class SingletonScanner {
    private static Scanner _scanner;

    private SingletonScanner(){
    }

    /**
     * return instance of the Scanner class
     * @return Scanner object
     */
    public static Scanner getInstance(){
        if (_scanner == null){
            _scanner = new Scanner(System.in);
        }
        return _scanner;
    }
}
