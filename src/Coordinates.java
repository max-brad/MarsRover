import java.text.MessageFormat;
import java.util.Objects;

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

    public int getMaxX() { return maxX; }

    public int getMinX() { return minX; }

    public int getMaxY() { return maxY; }

    public int getMinY() { return minY; }

    void setY(int y) {
        if (y < minY || y > maxY) {
            throw new IllegalArgumentException(String.format("Invalid y-coordinate: %d (min=%d, max=%d)",
                    y, minY, maxY));
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Coordinates'{'x={0}, y={1}'}'", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
