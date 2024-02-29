package View;

import Model.BoardSpot;
import Model.GameBoard;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    UI(){}

    public String GetUserInput(){
        Scanner StringScanner = new Scanner(System.in);
        return  StringScanner.nextLine();
    }

    private static boolean isValidLetter(char letter) {
        return letter >= 'A' && letter <= 'J';
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

    public void printBoard(GameBoard board, boolean gameDone){
        ArrayList<ArrayList<BoardSpot>> spotArray = board.getBoardSpots();

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
                }else{
                    printEndOfGameSpot(spot);
                }



            }
            System.out.println();
        }
    }

}
