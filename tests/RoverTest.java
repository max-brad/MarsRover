import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {

    private Coordinates coordinates;
    private Rotatable<Heading> heading;
    private Rover rover;

    @BeforeEach
    void setUp() {
        Rover.occupiedCoordinates = new HashSet<>();
        heading = Heading.N;
        coordinates = new Coordinates(0, 0);
        rover = new Rover(coordinates, heading);
    }

    @Test
    public void testValidCommands() {
        assertDoesNotThrow(() -> rover.move("MMRML"));
    }

    @Test
    public void testInvalidCommands() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> rover.move("ABCDE"));
        assertEquals("Only the letters M, L or R are valid.", exception.getMessage());
    }

    @Test
    public void testMoveForward() {
        String result = rover.move("MMMMM");
        assertEquals("Rover{Coordinates{x=0, y=5}, heading=N}", result);
    }

    @Test
    public void testTurnLeft() {
        String result = rover.move("L");
        // Verify that the heading is rotated anticlockwise
        assertEquals("Rover{Coordinates{x=0, y=0}, heading=W}", result);
    }

    @Test
    public void testTurnRight() {
        String result = rover.move("R");
        assertEquals("Rover{Coordinates{x=0, y=0}, heading=E}", result);
    }

    @Test
    public void testMultipleCommands() {
        String result = rover.move("RMMMMMLMMMMM");
        assertEquals("Rover{Coordinates{x=5, y=5}, heading=N}", result);
    }

    @Test
    public void testMoveOutOfBounds() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> rover.move("MMMMMM"));
        assertEquals("Invalid y-coordinate: 6 (min=0, max=5)", exception.getMessage());
    }

    @Test
    void testMoveToOccupiedCoordinates() {
/*
        Create a new Rover north of the current rover (0,1 & 0,0). Then, try to move the 0,0 rover to the occupied
        position of 0,1.
*/
        System.out.println(rover);
        Rover rover2 = new Rover(new Coordinates(0, 1, 5, 0, 5, 0), Heading.E);
        System.out.println(rover2);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> rover.move("M"));
        assertEquals("The following coordinates are already occupied: Coordinates{x=0, y=1}",
                exception.getMessage());
    }
}
