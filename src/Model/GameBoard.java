package Model;

import java.util.ArrayList;
import java.util.Random;



public class GameBoard {

    final int MAX_CELLS_PER_FORT = 5;
    ArrayList<ArrayList<BoardSpot>> boardSpots;

    public ArrayList<Fort> Forts = new ArrayList<>();

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

    public static char convertNumberToChar(int number) {
        // Convert the number to a character using ASCII values
        return (char) ('a' + number);
    }

    private void generateAllForts(int numOpponents) {
        boolean succesfullyMadeFort = false;
        // Now we have number of opponents, and if we are cheating
        for (int fortsMade = 0; fortsMade < numOpponents; fortsMade++) {

            char fortName = convertNumberToChar(fortsMade);
            Fort newFort = new Fort(fortName);

            // Failed to generate fort
            succesfullyMadeFort = generateFortAndReturnStatus(newFort, fortName);

            int fortCreationAttempts = 0;
            while(!succesfullyMadeFort && fortCreationAttempts < 100) {
                succesfullyMadeFort = generateFortAndReturnStatus(newFort, fortName);
                fortCreationAttempts++;
            }

            if(fortCreationAttempts == 100){
                throw new RuntimeException("Could find a valid placement for all forts.");
            }

            Forts.add(newFort);

        }

    }

    int convertLetterToNum(char letter){
        char upperCaseLetter = Character.toUpperCase(letter);
        return switch (upperCaseLetter) {
            case 'A' -> 1;
            case 'B' -> 2;
            case 'C' -> 3;
            case 'D' -> 4;
            case 'E' -> 5;
            case 'F' -> 6;
            case 'G' -> 7;
            case 'H' -> 8;
            case 'I' -> 9;
            case 'J' -> 10;
            default -> -1;
        };
    }

    public BoardSpot GetInputtedBoardSpot(String userIn){
        int xPos = Integer.parseInt(userIn.substring(1));
        int yPos = convertLetterToNum(userIn.charAt(0));

        return boardSpots.get(xPos).get(yPos);
    }

    public boolean generateFortAndReturnStatus(Fort newFort, char fortName) { // Main loop for fort generation
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
            b.setFortLabel(fortName);
        }

//        newFort.copySpotList(selectedFortSpots);

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

    public ArrayList<Fort> getForts(){
        return Forts;
    }

    public Fort getFort(char targetFort){
        for(Fort f: Forts){
            if(f.getFortLabel() == targetFort){
                return f;
            }
        }

        return null;
    }

    public int numWorkingForts(){
        int numWorkingForts = 0;
        for(Fort f: Forts){
            if(f.getDamageTaken() < 5){
                numWorkingForts++;
            }
        }
        return numWorkingForts;
    }

    public boolean allFortsDamaged(){
        int numDamagedCells = 0;
        for(Fort f: Forts){
            numDamagedCells+=f.getDamageTaken();
        }

        return numDamagedCells == Forts.size() * MAX_CELLS_PER_FORT;
    }
}
