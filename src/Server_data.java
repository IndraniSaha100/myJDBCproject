import java.util.Scanner;

class Server_data extends StudentDAO {
    public void addData(String tableName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much data you want to entry");
        int num = Integer.parseInt(scanner.nextLine());
        if(num==0){
            System.out.println("ok you don't want to put any data");
            return;
        }
        while (num > 0) {
            super.addData(tableName);
            num--;
        }
    }

    public void createTable(String name) {
        super.createTable(name);
    }

    public void deleteTable(String name) {
        System.out.println("Do you really want to delete the table!!press 1 for yes");
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        if(num==1) {
            super.deleteTable(name);
        }
    }

    public void showTable(String tableName) {
        super.showTable(tableName);
    }

    public void search(String tableName) {
        /*1. Risk of Exceptions:
            NumberFormatException: If the user inputs a non-numeric value when prompted to enter a number (for either the search type or roll number), the code would throw a NumberFormatException, leading to a program crash. The modification includes exception handling to prevent this crash and provide user-friendly error messages.
            NoSuchElementException: If the input is not available when the scanner.nextLine() method is called, a NoSuchElementException could be thrown. This is especially problematic if the input stream is unexpectedly empty.
        2. Scanner Closure:
            Premature Closure: The original code closes the Scanner at the end of the method. While this might seem correct, closing the Scanner that wraps System.in also closes the input stream. If you need further input from the console later in the program, it won't be available. The modification keeps the Scanner open in a broader context or closes it safely in a 'finally' block, ensuring the program can handle further input if needed.
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
    public void numberOfStudent(String tableName){
        super.numberOfStudent(tableName);
    }
    public void eraseData(String tableName){
        System.out.println("Do you really want to remove all data!!press 1 for yes");
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        if(num==1) {
            super.eraseData(tableName);
        }
    }
    public void showColumnName(String tableName){
        System.out.println("Column Names are given below");
        super.showColumnName(tableName);
    }
    public void changeSchema(String tableName){
        do{

            System.out.println("""
                    What you want to change
                    1.Add a column
                    2.Delete a column
                    3.Rename the column
                    4.Rename the table
                    5.Exit""");
            Scanner scanner = new Scanner(System.in);
            int choice = Integer.parseInt(scanner.nextLine());
            String colName;
            switch(choice){
                case 1:
                    System.out.print("Already existed ");
                    showColumnName(tableName);

                    System.out.println("Enter the name of new column");
                    colName = scanner.nextLine().trim(); // Trim to remove any leading/trailing spaces

                    if (colName.isEmpty()) {
                        System.out.println("Column name cannot be empty. Please enter a valid column name.");
                        break; // Exit or loop back to prompt again
                    }

                    System.out.println("Which type of data do you want to insert" +
                            "\n1.Number\t2.Text");
                    String typeInput = scanner.nextLine().trim();

                    if (typeInput.isEmpty()) {
                        System.out.println("Data type cannot be empty. Please enter a valid data type.");
                        break; // Exit or loop back to prompt again
                    }

                    int type;
                    try {
                        type = Integer.parseInt(typeInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number (1 for Number, 2 for Text).");
                        break; // Exit or loop back to prompt again
                    }

                    if (type == 1 || type == 2) {
                        super.createColumn(tableName, colName, type);
                    } else {
                        System.out.println("You entered a wrong choice of data type.");
                    }

                case 2:
                    showColumnName(tableName);
                    System.out.println("Which column do you want to delete");
                    colName=scanner.nextLine();
                    super.deleteColumn(tableName,colName);
                    break;
                case 3:
                    showColumnName(tableName);
                    System.out.println("Which column do you want to rename");
                    String prevName=scanner.nextLine();
                    System.out.println("Enter new Name of the column in one word(like newColumn)");
                    colName=scanner.nextLine();
                    super.renameColumnName(tableName,prevName,colName);
                    break;
                case 4:
                    System.out.println("Enter the new Name of table");
                    String newTableName=scanner.nextLine();
                    super.renameTableName(tableName,newTableName);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("you enter wrong choice");
            }
        } while(true);
    }
}
