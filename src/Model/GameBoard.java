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

    int convertLetterToNum(char letter){
        int yPos = -1;
        switch(letter){
            case 'A':
                yPos = 1;
                break;
            case 'B':
                yPos = 2;
                break;
            case 'C':
                yPos = 3;
                break;
            case 'D':
                yPos = 4;
                break;
            case 'E':
                yPos = 5;
                break;
            case 'F':
                yPos = 6;
                break;
            case 'G':
                yPos = 7;
                break;
            case 'H':
                yPos = 8;
                break;
            case 'I':
                yPos = 9;
                break;
            case 'J':
                yPos = 10;
                break;
        }
        return yPos;
    }

    private BoardSpot GetInputtedBoardSpot(String userIn){
        int xPos = Integer.parseInt(userIn.substring(1));
        int yPos = convertLetterToNum(userIn.charAt(0));

        return boardSpots.get(xPos).get(yPos);
    }

    public void HitBoard(String userIn){
        BoardSpot chosenSpot = GetInputtedBoardSpot(userIn);

        chosenSpot.Hit();
    }

    public void generateForts() { // Main loop for fort generation
        Random randomNum = new Random();
        int randRow = randomNum.nextInt(12);
        int randCol = randomNum.nextInt(12);
        // Spot we are checking
        BoardSpot spotToCheck = boardSpots.get(randRow).get(randCol);
        // Is a valid game spot and not in a fort already
        while (!spotToCheck.isValid() && !spotToCheck.isFort()) {
            randRow = randomNum.nextInt(12);
            randCol = randomNum.nextInt(12);
            spotToCheck = boardSpots.get(randRow).get(randCol);
        }
        BoardSpot startPoint = spotToCheck;
        ArrayList<BoardSpot> possibleFortSpots = new ArrayList<>();
        possibleFortSpots.add(startPoint);
        int currentFortSize = 0;

        while (currentFortSize != 5) {
            // Picks a random possibleFort spot from the ArrayList of
            // Board Spots that are available to be a fort spot.
            int randomPossibleFortSpotIndex = randomNum.nextInt(possibleFortSpots.size());
            startPoint = possibleFortSpots.get(randomPossibleFortSpotIndex);


            // Checks if all spots around the current spot can be a fort spot, and if so,
            // adds it to the list of possible spots.
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

            possibleFortSpots.remove(randomPossibleFortSpotIndex);

            if (possibleFortSpots.isEmpty()) {
                break;
            }

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
