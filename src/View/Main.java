package View;

import Model.BoardSpot;
import Model.GameBoard;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int numOpponents = 5; // Default number
        String cheating = "";


        if (args.length > 0 && args[0] != null) {
            try {
                numOpponents = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println(args[0]);
                System.err.println(args[1]);
            }
        }
        if ((args.length > 1 && args[1].equals("--cheat"))) {
            cheating = args[1];
        }

        GameBoard board = new GameBoard(numOpponents);
        UI userInterface = new UI();
        GameRunner Game = new GameRunner();
        Game.RunGame(board, cheating);
    }
}
