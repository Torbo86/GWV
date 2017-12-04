package Blatt03.objectives;

public class Position {

    private int x = 0;
    private int y = 0;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return den x value of the position
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y value of the position
     */
    public int getY() {
        return y;
    }

    /**
     * @param x the new x value of the position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the new y value of the position
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position pos2 = (Position) obj;
            return
                    (pos2.getX() == this.getX()) &&
                            (pos2.getY() == this.getY());

        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getX() + "," + getY();
    }
}
