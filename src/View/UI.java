package View;

import Model.BoardSpot;
import Model.GameBoard;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The UI class handles all interactions with the keyboard, like getting the users input hit. It also handles the board printing, and
 * the UI takes in an already instantiated board object and then uses information from the board to display it.
 */
public class UI {

    UI(){}

    public String GetUserInput(){
        Scanner StringScanner = new Scanner(System.in);
        return  StringScanner.nextLine();
    }

    private static boolean isValidLetter(char letter) {
        char lowercaseLetter = Character.toUpperCase(letter);
        return lowercaseLetter >= 'A' && lowercaseLetter <= 'J';
    }


    private static boolean isValidNumber(int number) {
        return number >= 1 && number <= 10;
    }

    private static final int minUserInput = 2;
    private static final int maxUserInput = 3;
    private static boolean isValidInput(String userInput) {

        if (userInput.length() < minUserInput || userInput.length() > maxUserInput) {
            return false;
        }

        char letterPosition = userInput.charAt(0);
        String numPositionStr = userInput.substring(1);

        if (!isValidLetter(letterPosition)) {
            return false;
        }

        try {
            int numPos = Integer.parseInt(numPositionStr);
            return isValidNumber(numPos);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getUserHit(){
        System.out.print("Enter your move: ");
        String userIn = GetUserInput();

        while (!isValidInput(userIn)) {
            System.out.print("Invalid input. Please enter a letter A-J and a number 1-10 (e.g., A5, J10): ");
            userIn = GetUserInput();
        }

        return userIn;
    }

    BoardSpot getBoardSpotFromUser(GameBoard board){
        String userSpot = getUserHit();
        BoardSpot chosenSpot = board.GetInputtedBoardSpot(userSpot);

        while(chosenSpot.isHit()){
            System.out.println("Spot already hit.");
            userSpot = getUserHit();
            chosenSpot = board.GetInputtedBoardSpot(userSpot);
        }

        return chosenSpot;
    }

    private void printRegularSpot(BoardSpot spot){
        if(!spot.isValid()){

        } else if (!spot.isHit()){
            System.out.print("~ ");
        }
        else if (spot.isHit() && spot.isFort()) {
            System.out.print("X ");
        } else{
            System.out.print("  ");
        }
    }

    private void printEndOfGameSpot(BoardSpot spot){

        if(!spot.isValid()){

        } else if (!spot.isFort()){
            System.out.print(". ");
        } else if (spot.isHit() && spot.isFort()){
            System.out.print(spot.getFortLabel()+ " ");
        }else if(!spot.isHit() && spot.isFort()){
            char upperCaseLabel = Character.toUpperCase(spot.getFortLabel());
            System.out.print(upperCaseLabel + " ");
        }
    }

    private void printCheatSpot(BoardSpot spot){
        if(!spot.isValid()){

        } else if (!spot.isFort()){
            System.out.print(". ");
        } else{
            char upperCaseFortSpot = Character.toUpperCase(spot.getFortLabel());
            System.out.print(upperCaseFortSpot+ " ");
        }
    }

    public void printBoard(GameBoard board, boolean gameDone, boolean cheat){
        ArrayList<ArrayList<BoardSpot>> spotArray = board.getBoardSpots();

        System.out.println("Game Board: ");

        System.out.print("  ");
        for (int x = 1; x <= 10; x++) {
            System.out.print(x + " ");
        }
        System.out.println();

        for(char y = 'B'; y <= 'K'; y++){
            System.out.print((char)(y - 1) + " ");

            for(int x = 1; x < 11; x++){

                //Need to do y - 'A' to get to down to convert back to normal 0-9
                BoardSpot spot = spotArray.get(x).get(y - 'A');

                if(!gameDone){
                    printRegularSpot(spot);
                }else if(cheat){
                    printCheatSpot(spot);
                }else{
                    printEndOfGameSpot(spot);
                }
            }
            System.out.println();
        }
    }
}
