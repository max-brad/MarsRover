public class Coordinates {
    private int x;
    private int y;
    private final int maxX;
    private final int minX;
    private final int maxY;
    private final int minY;

    public Coordinates(int x, int y, int maxX, int minX, int maxY, int minY) {
        this.maxX = maxX;
        this.minX = minX;
        this.maxY = maxY;
        this.minY = minY;
        setX(x);
        setY(y);
    }

    public Coordinates(int x, int y) { this(x, y, 5, 0, 5, 0); }

    public Coordinates(Coordinates c) { this(c.x, c.y, c.maxX, c.minX, c.maxY, c.minY); }

    public int getX() { return x; }

    void setX(int x) {
        if (x < minX || x > maxX) {
            throw new IllegalArgumentException(String.format("Invalid x-coordinate: %d (min=%d, max=%d)",
                    x, minX, maxX));
        }
        this.x = x;
    }

    public int getY() { return y; }

    void setY(int y) {
        if (y < minY || y > maxY) {
            throw new IllegalArgumentException(String.format("Invalid y-coordinate: %d (min=%d, max=%d)",
                    y, minY, maxY));
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + ", maxX=" + maxX + ", minX=" + minX + ", maxY=" + maxY +
                ", minY=" + minY + '}';
    }
}
