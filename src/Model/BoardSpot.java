package Model;

import java.util.ArrayList;
import java.util.List;

public class BoardSpot extends ArrayList<BoardSpot> {
    private boolean isHit;
    private boolean isFort;
    private boolean isShown;
    private boolean isPossibleFort;

    private boolean isValid;

    private final int x_position;
    private final int y_position;

    public BoardSpot(int position_x, int position_y) {
        this.isHit = false;
        this.isFort = false;
        this.isShown = false;
        this.isPossibleFort = false;
        this.x_position = position_x;
        this.y_position = position_y;
        if(position_x == 0 || position_x == 11 || position_y == 0 || position_y == 11){
            this.isValid = false;
        } else{
            this.isValid = true;
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

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
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
}
