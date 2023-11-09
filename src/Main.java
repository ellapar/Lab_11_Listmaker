import java.util.ArrayList;
import java.util.Scanner;

class Main {
    private static ArrayList<String> itemList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        char choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = getRegExString("[AaDdPpQq]");

            switch (choice) {
                case 'A':
                case 'a':
                    addItem();
                    break;
                case 'D':
                case 'd':
                    deleteItem();
                    break;
                case 'P':
                case 'p':
                    printList();
                    break;
                case 'Q':
                case 'q':
                    if (getYNConfirm("Are you sure you want to quit?")) {
                        System.out.println("Exiting program. Goodbye!");
                        System.exit(0);
                    }
                    break;
            }
        } while (true);
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit");
    }

    private static void addItem() {
        System.out.print("Enter the item to add: ");
        String item = scanner.nextLine();
        itemList.add(item);
        System.out.println("Item added successfully.");
    }

    private static void deleteItem() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }

        System.out.println("Current List:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }

        int itemNumber = getRangedInt("Enter the item number to delete: ", 1, itemList.size());
        itemList.remove(itemNumber - 1);
        System.out.println("Item deleted successfully.");
    }

    private static void printList() {
        if (itemList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Current List:");
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + ". " + itemList.get(i));
        }
    }

    private static char getRegExString(String pattern) {
        char choice;
        do {
            System.out.print("Enter choice: ");
            String input = scanner.nextLine();
            if (input.matches(pattern)) {
                choice = input.charAt(0);
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } while (true);
        return choice;
    }

    private static int getRangedInt(String prompt, int min, int max) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            value = scanner.nextInt();
            scanner.nextLine(); // consume the newline
            if (value < min || value > max) {
                System.out.println("Value out of range. Please enter a number between " + min + " and " + max + ".");
            } else {
                break;
            }
        } while (true);
        return value;
    }

    private static boolean getYNConfirm(String prompt) {
        String response;
        do {
            System.out.print(prompt + " (Y/N): ");
            response = scanner.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                return true;
            } else if (response.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        } while (true);
    }
}
