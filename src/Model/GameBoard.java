package Model;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    ArrayList<ArrayList<BoardSpot>> boardSpots;

    public ArrayList<Fort> Forts;

    final int numOpponents;

    public GameBoard(int numOpponents, String cheat){
        this.numOpponents = numOpponents;
        boardSpots = new ArrayList<>();
        for(int x = 0; x < 12; x++){
            ArrayList<BoardSpot> row = new ArrayList<>();
            for(int y = 0; y < 12; y++){
                row.add(new BoardSpot(x, y));
            }
            boardSpots.add(row);
        }

        // Now we have number of opponents, and if we are cheating


    }

    public void generateFort() { // Main loop for fort generation
        Random randomNum = new Random();
        int randRow = randomNum.nextInt(12);
        int randCol = randomNum.nextInt(12);
        // Spot we are checking
        BoardSpot spotToCheck = boardSpots.get(randRow).get(randCol);
        while (!spotToCheck.isValid()) {
            randRow = randomNum.nextInt(12);
            randCol = randomNum.nextInt(12);
            spotToCheck = boardSpots.get(randRow).get(randCol);
        }
        BoardSpot startPoint = spotToCheck;
        checkAbove(startPoint);
        checkBelow(startPoint);
        checkLeft(startPoint);
        checkRight(startPoint);
        // Call function to determine other possible fort

    }

    private void checkRight(BoardSpot startPoint) {
    }

    private void checkLeft(BoardSpot startPoint) {
    }

    private void checkBelow(BoardSpot startPoint) {
    }

    private void checkAbove(BoardSpot startPoint) {
    }


    public ArrayList<ArrayList<BoardSpot>> getBoardSpots() {
        return boardSpots;
    }
}
