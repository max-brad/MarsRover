import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Rover> rovers = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Existing rovers: " + rovers);
            System.out.println("Choose an option:");
            System.out.println("1. Add a new rover");
            System.out.println("2. Select an existing rover");
            System.out.println("3. Quit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1 -> addNewRover(scanner, rovers);
                case 2 -> selectExistingRover(scanner, rovers);
                case 3 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewRover(Scanner scanner, Map<String, Rover> rovers) {
        System.out.println("Enter the rover's start point and heading in the following format: x y heading." +
                "For example, '0 1 N' means x-coordinate=0, y-coordinate=1 and heading=North.");
        try {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Heading heading = Heading.valueOf(scanner.next().toUpperCase());
            scanner.nextLine(); // Consume the newline character
            System.out.println("Enter a name for the rover:");
            String roverName = scanner.nextLine();
            if (roverName.isEmpty()) {
                System.out.println("The name cannot be empty. Please try again.");
                return;
            }
            Rover rover = new Rover(new Coordinates(x, y), heading);
            rovers.put(roverName, rover);
            System.out.println("Rover created: " + roverName);
        } catch (IllegalArgumentException e) {
            System.out.println("Please try again: " + e.getMessage());
        }
    }

    private static void selectExistingRover(Scanner scanner, Map<String, Rover> rovers) {
        System.out.println("Enter the name of the rover you want to select:");
        String roverName = scanner.nextLine();
        if (rovers.containsKey(roverName)) {
            Rover rover = rovers.get(roverName);
            System.out.println("Selected rover: " + roverName);
            moveRover(scanner, rover);
        } else {
            System.out.println("Rover not found. Please try again.");
        }
    }

    private static void moveRover(Scanner scanner, Rover rover) {
        System.out.println("Enter one or more of the following letters to command the rover: M, L and R. " +
                "M will move it forward by one. L will turn it left 90 degrees, and R will turn it right 90 degrees.");
        String commands = scanner.next().toUpperCase();
        try {
            String result = rover.move(commands);
            System.out.println("Your commands were executed successfully: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Houston, we have a problem: " + e.getMessage());
        }
    }
}
