package View;

import Model.BoardSpot;
import Model.GameBoard;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int numOpponents = 5; // Default number
        String cheating = "";
        if (args.length > 1 && args[1] != null) {
            numOpponents = Integer.parseInt(args[1]);
        }
        if ((args.length > 2 && args[2].equals("--cheat"))) {
            cheating = args[2];
        }

        GameBoard board = new GameBoard(numOpponents, cheating);
        UI userInterface = new UI();
        GameRunner Game = new GameRunner();
        userInterface.printBoard(board);
        Game.RunGame(board);
    }
}
