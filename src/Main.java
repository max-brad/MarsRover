import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the rover's start point and heading in the following format: x y heading. " +
                "For example, '0 1 N' means x-coordinate=0, y-coordinate=1 and heading=North.");
        try {
            Scanner scanner = new Scanner(System.in);
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Heading heading = Heading.valueOf(scanner.next().toUpperCase());
            Rover rover = new Rover(new Coordinates(x, y), heading);
            System.out.println("Rover created: " + rover);
            System.out.println("Enter one or more of the following letters to command the rover: M, L and R. M will " +
                    "move it forward by one. L will turn it left 90 degrees, and R will turn it right 90 degrees.");
            String commands = scanner.next().toUpperCase();
            String result = rover.move(commands);
            System.out.println("Your commands were executed successfully: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Please try again: " + e.getMessage());
        }
    }
}