package Model;

import java.util.ArrayList;

public class Fort {
    ArrayList<BoardSpot> fortSpots;

    //Not quite sure what to put in this class yet
    Fort(ArrayList<BoardSpot> selectedSpots){
        fortSpots = selectedSpots;
    }

    public int dealDamage(){
        if(fortSpots.size() == 4 || fortSpots.size() == 5){
            return 20;
        } else if(fortSpots.size() == 3){
            //
        }
        return 0;
    }
}
