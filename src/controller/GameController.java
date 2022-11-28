package controller;

import utility.SingletonScanner;

import java.util.Scanner;

/**
 * An interface for providing default api and functions for running game(s)
 */
public interface GameController extends GameInputManager {

    /**
     * abstract method used for running any game
     */
    void run() throws IllegalAccessException;
}
