package Model;

import java.util.ArrayList;
import java.util.List;

public class BoardSpot extends ArrayList<BoardSpot> {
    private boolean isHit;
    private boolean isFort;
    private boolean isShown;

    private final int x_position;
    private final int y_position;

    public BoardSpot(int position_x, int position_y) {
        isHit = false;
        isFort = false;
        isShown = false;
        this.x_position = position_x;
        this.y_position = position_y;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
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
}
