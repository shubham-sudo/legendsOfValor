package controller;

import utility.SingletonScanner;

import java.util.Scanner;

/**
 * This is the main controller used in Main.java class
 * which will also run the game user wants to run
 */
public class MainController {
    private final Scanner scanner;
    private GameController gameController;

    /**
     * Object creation
     */
    public MainController(){
        this.scanner = SingletonScanner.getInstance();
    }

    /**
     * show the game menu and ask for input
     */
    private void gameMenu(){
        System.out.println("Hello, Welcome!!!");
        System.out.println("Which game you want to play today?");
        System.out.println("[1] Legends");

        while(true){
            int num;
            System.out.print("Enter your input : ");
            try {
                num = Integer.parseInt(this.scanner.nextLine().trim());
            }
            catch (Exception e){
                System.out.println("Please enter a Integer!");
                System.out.println("Try again!");
                continue;
            }
            if (num == 1) {  // Can use switch case if multiple games
                this.gameController = new LegendsOfValorController();
                break;
            } else {
                System.out.println("Invalid Input, Try again!");
            }
        }
    }

    /**
     * Starting point for everything
     */
    public void execute() {
        gameMenu();
        try {
            this.gameController.run();
        } catch (IllegalAccessException iae){
            iae.printStackTrace();
        }
    }
}
