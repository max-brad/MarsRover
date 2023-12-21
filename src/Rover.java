import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;


public class Rover {
    private static final Pattern VALID_COMMANDS = Pattern.compile("^[MLR]+$");
    private Coordinates coordinates;
    private Rotatable<Heading> heading;

    static Set<Coordinates> occupiedCoordinates = new HashSet<>();

    public Rover(Coordinates coordinates, Rotatable<Heading> heading) {
        this.coordinates = new Coordinates(coordinates);
        occupiedCoordinates.add(this.coordinates);
        this.heading = heading;
    }

    public String move(String commands) {
        if (!VALID_COMMANDS.matcher(commands).matches()) {
            throw new IllegalArgumentException("Only the letters M, L or R are valid.");
        }
        for (char command : commands.toCharArray()) {
            switch (command) {
                case 'M' -> moveForward();
                case 'L' -> turnLeft();
                case 'R' -> turnRight();
            }
        }
        return toString();
    }

    private void turnLeft() {
        heading = heading.rotateAnticlockwise();
    }

    private void turnRight() {
        heading = heading.rotateClockwise();
    }

    private void moveForward() {
        int newX = coordinates.getX();
        int newY = coordinates.getY();
        switch (heading) {
            case Heading.N -> newY += 1;
            case Heading.E -> newX += 1;
            case Heading.S -> newY -= 1;
            case Heading.W -> newX -= 1;
            default -> throw new IllegalStateException("Unexpected value: " + heading);
        }
        Coordinates newCoordinates = new Coordinates(newX, newY, coordinates.getMaxX(), coordinates.getMinX(),
                coordinates.getMaxY(), coordinates.getMinY());
        if (occupiedCoordinates.contains(newCoordinates)) {
            throw new IllegalArgumentException("The following coordinates are already occupied: " + newCoordinates);
        }
        occupiedCoordinates.remove(coordinates);
        occupiedCoordinates.add(newCoordinates);
        coordinates = newCoordinates;
    }

    @Override
    public String toString() {
        return String.format("Rover{%s, heading=%s}", coordinates, heading);
    }
}

