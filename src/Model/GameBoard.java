package Model;

import java.util.ArrayList;

public class GameBoard {
    ArrayList<BoardSpot> boardSpots;

    public GameBoard(){
        boardSpots = new ArrayList<>();
        for(int i = 1; i < 101; i++){
            boardSpots.add(new BoardSpot(i));
        }
    }

    public ArrayList<BoardSpot> getBoardSpots() {
        return boardSpots;
    }
}
