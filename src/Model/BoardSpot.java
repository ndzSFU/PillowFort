package Model;

public class BoardSpot {
    private boolean isHit;
    private boolean isFort;
    private boolean isShown;

    private final int position;

    public BoardSpot(int position) {
        isHit = false;
        isFort = false;
        isShown = false;
        this.position = position;
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

    public int getPosition() {
        return position;
    }
}
