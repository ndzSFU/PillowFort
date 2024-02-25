package View;

import Model.GameBoard;

public class GameRunner {
    UI UserInterface = new UI();

    private final int oppWinCondition = 2500;

    private int oppPoints = 0;
    public void RunGame(GameBoard board){
        while(oppPoints < oppWinCondition && board.Forts.size() > 0){
            String userSpot = UserInterface.getUserHit();
        }
    }
}
