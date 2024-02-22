package Model;

import java.util.ArrayList;

public class GameBoard {
    ArrayList<ArrayList<BoardSpot>> boardSpots;

    ArrayList<Fort> Forts;

    public GameBoard(){
        boardSpots = new ArrayList<>();
        for(int x = 0; x < 10; x++){
            ArrayList<BoardSpot> row = new ArrayList<>();
            for(int y = 0; y < 10; y++){
                row.add(new BoardSpot(x, y));
            }
            boardSpots.add(row);
        }
    }


    public ArrayList<ArrayList<BoardSpot>> getBoardSpots() {
        return boardSpots;
    }
}
