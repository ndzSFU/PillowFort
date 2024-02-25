package Model;

import java.util.ArrayList;

public class GameBoard {
    ArrayList<ArrayList<BoardSpot>> boardSpots;

    public ArrayList<Fort> Forts;

    final int numOpponents;

    public GameBoard(int numOpponents , String cheat){
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


    public ArrayList<ArrayList<BoardSpot>> getBoardSpots() {
        return boardSpots;
    }
}
