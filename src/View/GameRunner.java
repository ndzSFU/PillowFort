package View;

import Model.GameBoard;

public class GameRunner {
    UI userInterface = new UI();

    private final int oppWinCondition = 2500;

    private int oppPoints = 0;
    public void RunGame(GameBoard board){
        while(oppPoints < oppWinCondition){
            String userSpot = userInterface.getUserHit();
            userInterface.printBoard(board);
        }
    }
}
