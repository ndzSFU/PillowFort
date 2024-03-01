package Model;

import java.util.ArrayList;

public class Fort {

    private int damageTaken = 0;
    //ArrayList<BoardSpot> fortSpots;
    private char fortLabel;

    Fort(char fortName){
        this.fortLabel = fortName;
    }

    //Not quite sure what to put in this class yet
//    public void copySpotList(ArrayList<BoardSpot> spots){
//        this.fortSpots = spots;
//    }

    public void takeDamage(){
        damageTaken++;
    }

    public int dealDamage(){
        if(damageTaken == 1 || damageTaken == 0){
            return 20;
        } else if(damageTaken == 2){
            return 5;
        } else if (damageTaken == 3) {
            return 2;
        } else if (damageTaken == 4) {
            return 1;
        }
        return 0;
    }

    public char getFortLabel() {
        return fortLabel;
    }

    public int getDamageTaken(){
        return damageTaken;
    }
}
