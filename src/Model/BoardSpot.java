package Model;

import java.util.ArrayList;
import java.util.List;


/**
 * BoardSpot object holds all relevant information pertaining to a specific spot/square on the board. A single BoardSpot hold
 * information including weather or not this spot has been hit, if there's a fort connected to this spot, if the spot can house a fort,
 * if the spot is in bounds, the spot's position( x and y coordinates), and the label of the fort which has been placed in this spot.
 */
public class BoardSpot extends ArrayList<BoardSpot> {
    private boolean isHit;
    private boolean isFort;
    private boolean isPossibleFort;

    final private boolean isValid;

    private final int x_position;
    private final int y_position;

    private char fortLabel;

    public BoardSpot(int position_x, int position_y) {
        this.isHit = false;
        this.isFort = false;

        this.x_position = position_x;
        this.y_position = position_y;
        if(position_x == 0 || position_x == 11 || position_y == 0 || position_y == 11){
            this.isValid = false;
            this.isPossibleFort = false;
        } else{
            this.isValid = true;
            this.isPossibleFort = true;
        }
    }

    public boolean isHit() {
        return isHit;
    }

    public void Hit() {
        isHit = true;
    }

    public boolean isFort() {
        return isFort;
    }

    public void setFort(boolean fort) {
        isFort = fort;
    }

    public int getY_position() {
        return y_position;
    }

    public int getX_position() {
        return x_position;
    }

    @Override
    public List<BoardSpot> reversed() {
        return super.reversed();
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isPossibleFort() {
        return isPossibleFort;
    }

    public void setPossibleFort(boolean possibleFort) {
        isPossibleFort = possibleFort;
    }

    public char getFortLabel() {
        return fortLabel;
    }

    public void setFortLabel(char fortLabel) {
        this.fortLabel = fortLabel;
    }
}
