package View;

import Model.BoardSpot;
import Model.GameBoard;

import java.util.ArrayList;

public class UI {

    UI(){}
//    void printBoard(GameBoard board){
//        ArrayList<ArrayList<BoardSpot>> spotArray = board.getBoardSpots();
//
//        System.out.print("  ");
//        for (char ch = 'A'; ch <= 'J'; ch++) {
//            System.out.print(ch + " ");
//        }
//        System.out.println();
//
//        for(int x = 0; x < 10; x++){
//            if (spotArray.size() > x) {
//                for(int y = 0; y < 10; y++){
//                    if (spotArray.get(x).size() > y) {
//                        BoardSpot spot = spotArray.get(x).get(y);
//
//                        if(!spot.isHit()){
//                            System.out.print("~ ");
//                        }
//                    }
//                }
//            }
//            System.out.println();
//        }
//    }

    void printBoard(GameBoard board){
        ArrayList<ArrayList<BoardSpot>> spotArray = board.getBoardSpots();

        System.out.print("  ");
        for (int x = 1; x <= 10; x++) {
            System.out.print(x + " ");
        }
        System.out.println();


        for(char y = 'A'; y <= 'J'; y++){
            System.out.print(y + " ");

            for(int x = 0; x < 10; x++){

                //Need to do y - 'A' to get to down to convert back to normal 0-9
                BoardSpot spot = spotArray.get(x).get(y - 'A');

                if(!spot.isHit()){
                    System.out.print("~ ");
                } else if (spot.isHit()) {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }


}
