package View;

import Model.BoardSpot;
import Model.GameBoard;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        UI userInterface = new UI();
        userInterface.printBoard(board);
    }
}
