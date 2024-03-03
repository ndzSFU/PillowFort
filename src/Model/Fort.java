package Model;


/**
 * The Fort class represents the opposing fort which the player must play against. Forts hold relevant data pertaining to the opponents points
 * in the game. A fort is connected to 5 chosen spots through its label/name. Each boardSpot which is chosen to have a fort in it, also has the name
 * /label of the fort which occupies it's spot. So the Fort object which is named, has its name stored in the BoardSpots it's supposed to occupy.
 * The fort mainly contains the number of time's it's been hit and uses that to calculate the logic pertaining to the opponent points system.
 */
public class Fort {

    private int damageTaken = 0;

    final private char fortLabel;

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
