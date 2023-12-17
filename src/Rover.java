import java.util.regex.Pattern;


public class Rover {
    private static final Pattern VALID_COMMANDS = Pattern.compile("^[MLR]+$");
    private final Coordinates coordinates;
    private Rotatable<Heading> heading;

    public Rover(Coordinates coordinates, Rotatable<Heading> heading) {
        this.coordinates = new Coordinates(coordinates);
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
        switch (heading) {
            case Heading.N -> coordinates.setY(coordinates.getY() + 1);
            case Heading.E -> coordinates.setX(coordinates.getX() + 1);
            case Heading.S -> coordinates.setY(coordinates.getY() - 1);
            case Heading.W -> coordinates.setX(coordinates.getX() - 1);
            default -> throw new IllegalStateException("Unexpected value: " + heading);
        }
    }

    @Override
    public String toString() {
        return String.format("Rover{%s, heading=%s}", coordinates, heading);
    }
}

