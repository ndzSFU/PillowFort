package View;
import Model.GameBoard;

/**
 * Main takes 0,1,2 arguments from command line, can take no args, defaults opponents to 5, 1 arg, num opponents or 2 args, num opponents
 * and --cheat. Main instantiates a GameBoard and a GameRunner and then pass The gameBoard into GameRunner, run game method.
 */
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
        GameRunner Game = new GameRunner();
        Game.RunGame(board, cheating);
    }
}
