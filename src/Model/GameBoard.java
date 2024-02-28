package Model;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    ArrayList<ArrayList<BoardSpot>> boardSpots;

    public ArrayList<Fort> Forts;

    final int numOpponents;

    public GameBoard(int numOpponents, String cheat){
        this.numOpponents = numOpponents;
        this.boardSpots = new ArrayList<>();
        for(int x = 0; x < 12; x++){
            ArrayList<BoardSpot> row = new ArrayList<>();
            for(int y = 0; y < 12; y++){
                row.add(new BoardSpot(x, y));
            }
            this.boardSpots.add(row);
        }

        generateAllForts(numOpponents);

    }

    private void generateAllForts(int numOpponents) {
        boolean succesfullyMadeFort = false;
        // Now we have number of opponents, and if we are cheating
        for (int fortsMade = 0; fortsMade < numOpponents; fortsMade++) {

            // Failed to generate fort
            succesfullyMadeFort = generateFortAndReturnStatus();
            while(!succesfullyMadeFort) {
                succesfullyMadeFort = generateFortAndReturnStatus();
            }
        }

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

    public BoardSpot GetInputtedBoardSpot(String userIn){
        int xPos = Integer.parseInt(userIn.substring(1));
        int yPos = convertLetterToNum(userIn.charAt(0));

        return boardSpots.get(xPos).get(yPos);
    }

    public boolean generateFortAndReturnStatus() { // Main loop for fort generation
        Random randomNum = new Random();
        int randRow = randomNum.nextInt(12);
        int randCol = randomNum.nextInt(12);

        // Spot we are checking
        BoardSpot spotToCheck = getBoardSpots().get(randCol).get(randRow);

        // Is a valid game spot and not in a fort already
        while (!spotToCheck.isValid() || spotToCheck.isFort()) {
            randRow = randomNum.nextInt(12);
            randCol = randomNum.nextInt(12);
            spotToCheck = getBoardSpots().get(randCol).get(randRow);
        }

        // Two arrays, one for the actual fort, and one for spots to add
        BoardSpot startPoint = spotToCheck;
        ArrayList<BoardSpot> possibleFortSpots = new ArrayList<>();
        ArrayList<BoardSpot> selectedFortSpots = new ArrayList<>();

        startPoint.setPossibleFort(false);
        possibleFortSpots.add(startPoint);

        while (selectedFortSpots.size() != 5) {
            // Picks a random possibleFort spot from the ArrayList of
            // Board Spots that are available to be a fort spot.
            int randomPossibleFortSpotIndex = randomNum.nextInt(possibleFortSpots.size());
            startPoint = possibleFortSpots.get(randomPossibleFortSpotIndex);

            selectedFortSpots.add(startPoint);
            possibleFortSpots.remove(randomPossibleFortSpotIndex);

            int randRowStart = startPoint.getY_position();
            int randColStart = startPoint.getX_position();

            // Checks if all spots around the current spot can be a fort spot, and if so,
            // adds it to the list of possible spots.
            if (isAboveValid(randRowStart, randColStart)) {
                possibleFortSpots.add(getBoardSpots().get(randColStart).get(randRowStart - 1));
                possibleFortSpots.getLast().setPossibleFort(false);
            }
            if (isBelowValid(randRowStart, randColStart)) {
                possibleFortSpots.add(getBoardSpots().get(randColStart).get(randRowStart + 1));
                possibleFortSpots.getLast().setPossibleFort(false);
            }
            if (isLeftValid(randRowStart, randColStart)) {
                possibleFortSpots.add(getBoardSpots().get(randColStart - 1).get(randRowStart));
                possibleFortSpots.getLast().setPossibleFort(false);
            }
            if (isRightValid(randRowStart, randColStart)) {
                possibleFortSpots.add(getBoardSpots().get(randColStart + 1).get(randRowStart));
                possibleFortSpots.getLast().setPossibleFort(false);
            }

            // No possible fort can be made
            if (possibleFortSpots.isEmpty()) {
                break;
            }
        }

        if (selectedFortSpots.size() != 5) {
            return false;
        }

        // Set game board spots to be a fort
        for (BoardSpot b : selectedFortSpots) {
            int x_pos = b.getX_position();
            int y_pos = b.getY_position();
            getBoardSpots().get(x_pos).get(y_pos).setFort(true);
         }

        return true;
    }

    // Updated methods to include row and column parameters
    private boolean isRightValid(int randRow, int randCol) {
        BoardSpot spotToTheRight = getBoardSpots().get(randCol + 1).get(randRow);
        return spotToTheRight.isValid() && spotToTheRight.isPossibleFort();
    }

    private boolean isLeftValid(int randRow, int randCol) {
        BoardSpot spotToTheLeft = getBoardSpots().get(randCol - 1).get(randRow);
        return spotToTheLeft.isValid() && spotToTheLeft.isPossibleFort();
    }

    private boolean isBelowValid(int randRow, int randCol) {
        BoardSpot spotBelow = getBoardSpots().get(randCol).get(randRow + 1);
        return spotBelow.isValid() && spotBelow.isPossibleFort();
    }

    private boolean isAboveValid(int randRow, int randCol) {
        BoardSpot spotAbove = getBoardSpots().get(randCol).get(randRow - 1);
        return spotAbove.isValid() && spotAbove.isPossibleFort();
    }

    public ArrayList<ArrayList<BoardSpot>> getBoardSpots() {
        return boardSpots;
    }
}
