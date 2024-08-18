import java.util.Scanner;

class Server_data extends StudentDAO {
    public void addData(String tableName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much data you want to entry");
        int num = Integer.parseInt(scanner.nextLine());
        while (num > 0) {
            super.addData(tableName);
            num--;
        }
    }

    public void createTable(String name) {
        super.createTable(name);
    }

    public void deleteTable(String name) {
        super.deleteTable(name);
    }

    public void showTable(String tableName) {
        super.showTable(tableName);
    }

    public void search(String tableName) {
        /*1. Risk of Exceptions:
            NumberFormatException: If the user inputs a non-numeric value when prompted to enter a number (for either the search type or roll number), the code would throw a NumberFormatException, leading to a program crash. The modification includes exception handling to prevent this crash and provide user-friendly error messages.
            NoSuchElementException: If the input is not available when the scanner.nextLine() method is called, a NoSuchElementException could be thrown. This is especially problematic if the input stream is unexpectedly empty.
        2. Scanner Closure:
            Premature Closure: The original code closes the Scanner at the end of the method. While this might seem correct, closing the Scanner that wraps System.in also closes the input stream. If you need further input from the console later in the program, it won't be available. The modification keeps the Scanner open in a broader context or closes it safely in a finally block, ensuring the program can handle further input if needed.
        3. Lack of Input Validation:
            Invalid User Choices: The original code assumes the user will always input 1 or 2 when choosing the search method. If the user enters anything else, the code does not handle it, leading to incorrect behavior. The modification adds validation to ensure the user selects a valid option.*/
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Do you want to search by roll=1 or name=2 ");

            int x = 0;
            if (scanner.hasNextLine()) {
                try {
                    x = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    return; // Exit the method on invalid input
                }
            }

            if (x == 1) {
                System.out.println("Enter roll no:");
                int roll = 0;
                if (scanner.hasNextLine()) {
                    try {
                        roll = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid roll number.");
                        return;
                    }
                }
                super.selectByRoll(tableName, roll);
            } else if (x == 2) {
                System.out.println("Enter student name:");
                String studentName = scanner.nextLine();
                super.selectByName(tableName, studentName);
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        /* Here exception may be:
        1. NumberFormatException:
            Cause: This exception is thrown when the code tries to convert a string to a number (e.g., using Integer.parseInt()), and the string is not a valid representation of a number.
            Example: If the user inputs a non-numeric string when asked for the roll number or when choosing between searching by roll or name.
        2. InputMismatchException:
            Cause: This can occur when an incorrect type of input is provided, such as if a method is expecting an integer but receives a string.
            Example: If the code were using scanner.nextInt() and the user inputs a non-integer, though this is less likely in your case because you're using scanner.nextLine() and then parsing the string.
        3. IllegalStateException:
            Cause: This exception is thrown if you try to use the Scanner after it has been closed.
            Example: This could happen if you accidentally close the Scanner before making subsequent calls to the search method.
        4. NoSuchElementException:
            Cause: This occurs if you try to read input using nextLine() or similar methods, but no more input is available.
            Example: This can happen if the input stream is exhausted or prematurely closed.
        5. SQLException:
            Cause: Although not in the try block provided, if the super.selectByRoll() or super.selectByName() methods involve database operations, they might throw an SQLException.
            Example: Issues with the database connection, malformed SQL queries, or data-related errors.*/
    }
}
