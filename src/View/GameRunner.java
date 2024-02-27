package View;

import Model.BoardSpot;
import Model.GameBoard;

public class GameRunner {
    UI userInterface = new UI();

    private final int oppWinCondition = 2500;

    private int oppPoints = 0;
    public void RunGame(GameBoard board){
        while(oppPoints < oppWinCondition){
            String userSpot = userInterface.getUserHit();
            BoardSpot chosenSpot = board.GetInputtedBoardSpot(userSpot);
            while(chosenSpot.isHit()){
                System.out.println("Spot already hit.");
                userSpot = userInterface.getUserHit();
                chosenSpot = board.GetInputtedBoardSpot(userSpot);
            }
            chosenSpot.Hit();
            userInterface.printBoard(board);
        }
    }
}
