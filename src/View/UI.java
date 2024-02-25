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

    public String getUserHit(){
        String userIn = GetUserInput();

        char letterPosition = userIn.charAt(0);
        String numPositionStr = userIn.substring(1);
        int numPos = Integer.parseInt(numPositionStr);

        while(true){
            boolean invalidInput = false;
            if(numPos > 10 || numPos < 1){
                invalidInput = true;
                System.out.println("Invalid Number Inputed for Shot position, please choose a number 1-10.");

            }

            if(letterPosition < 'A' || letterPosition > 'J'){
                invalidInput = true;
                System.out.println("Invalid letter Inputed for Shot position, please choose a letter A-J.");

            }

            if(invalidInput){
                userIn = GetUserInput();
                letterPosition = userIn.charAt(0);
                numPositionStr = userIn.substring(1);
                numPos = Integer.parseInt(numPositionStr);
            } else{
                break;
            }

        }

        return userIn;
    }

    public void printBoard(GameBoard board){
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

                if(!spot.isValid()){

                } else if (!spot.isHit()){
                    System.out.print("~ ");
                } else if (spot.isHit() && spot.isFort()) {
                    System.out.print("X ");
                } else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }


}
