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

    public void generateForts() { // Main loop for fort generation
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
        ArrayList<BoardSpot> possibleFortSpots = new ArrayList<>();
        possibleFortSpots.add(startPoint);
        int currentFortSize = 0;

        while (currentFortSize != 5) {
            int randomPossibleFortSpotIndex = randomNum.nextInt(possibleFortSpots.size());
            startPoint = possibleFortSpots.get(randomPossibleFortSpotIndex);

            if (isAboveValid(startPoint)) {
                possibleFortSpots.add(boardSpots.get(randRow - 1).get(randCol));
            }
            if (isBelowValid(startPoint)) {
                possibleFortSpots.add(boardSpots.get(randRow + 1).get(randCol));
            }
            if (isLeftValid(startPoint)) {
                possibleFortSpots.add(boardSpots.get(randRow).get(randCol - 1));
            }
            if (isRightValid(startPoint)) {
                possibleFortSpots.add(boardSpots.get(randRow + 1).get(randCol + 1));
            }

            randomPossibleFortSpotIndex = randomNum.nextInt(possibleFortSpots.size());

            currentFortSize++;
        }




    }

    // These check if the spot next to the starting point are possible fort spots,
    // if so, they are added to an array which we will randomly pick a game spot
    // to make a part of this fort. Also checks if the spot in inside or outside the
    // game board.
    private boolean isRightValid(BoardSpot startPoint) {
        return true;
    }

    private boolean isLeftValid(BoardSpot startPoint) {
        return true;
    }

    private boolean isBelowValid(BoardSpot startPoint) {
        return true;
    }

    private boolean isAboveValid(BoardSpot startPoint) {
        return true;
    }


    public ArrayList<ArrayList<BoardSpot>> getBoardSpots() {
        return boardSpots;
    }
}
