package Model;

import java.util.ArrayList;

public class Fort {

    private int damageTaken = 0;

    private char fortLabel;

    Fort(char fortName){
        this.fortLabel = fortName;
    }

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
