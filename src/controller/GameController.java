package controller;

import utility.SingletonScanner;

import java.util.Scanner;

/**
 * An interface for providing default api and functions for running game(s)
 */
public interface GameController {
    Scanner scanner = SingletonScanner.getInstance();

    /**
     * Get an enter key from user
     */
    default void getEnter(){
        System.out.println();
        System.out.println("Hit Enter ...");
        this.scanner.nextLine();
    }

    /**
     * Get YES or NO from the user and validate
     * @return boolean true if YES, false otherwise
     */
    default boolean getUserYesOrNo(){
        String input = null;
        boolean wantToChange = false;

        while (input == null){
            System.out.print("Enter your input : ");
            try {
                input = this.scanner.nextLine().trim().toLowerCase();
            } catch (Exception e){
                System.out.println("Please enter a VALID string!");
                System.out.println("Try again!");
                continue;
            }
            if (input.equals("yes") || input.equals("y")){
                wantToChange = true;
            } else if (!input.equals("no") && !input.equals("n")) {
                input = null;
                System.out.println("Please enter a VALID string!");
                System.out.println("Try again!");
            }
        }
        return wantToChange;
    }

    /**
     * Read an integer value from the user input
     * @return integer provided by the user
     */
    default int getIntFromUser(){
        int newInt;
        while (true){
            System.out.print("Enter a Integer : ");
            try {
                newInt = Integer.parseInt(this.scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("INVALID, Please enter a Integer!");
                System.out.println("Try again!");
                continue;
            }
            break;
        }
        return newInt;
    }

    /**
     * Get a char value from the user
     * @return char value
     */
    default char getCharFromUser(){
        char newChar;
        while (true){
            System.out.print("Enter your Move : ");
            try {
                String input = this.scanner.nextLine().trim();
                if (input.length() > 1){
                    throw new Exception();
                }
                newChar = Character.toLowerCase(input.charAt(0));
            } catch (Exception e) {
                System.out.println("INVALID, Please enter a Character!");
                System.out.println("Try again!");
                continue;
            }
            break;
        }
        return newChar;
    }

    /**
     * Get string value from the user, also do validation
     * @return string read from console
     */
    default String getStringFromUser(){
        String newStr;
        while (true){
            System.out.print("Enter your input : ");
            try {
                newStr = this.scanner.nextLine().trim();
            } catch (Exception e) {
                System.out.println("INVALID, Please enter a valid string!");
                System.out.println("Try again!");
                continue;
            }
            break;
        }
        return newStr;
    }

    /**
     * abstract method used for running any game
     */
    void run() throws IllegalAccessException;
}
