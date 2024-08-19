import java.util.Scanner;

class MainClass{
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Server_data serverData=new Server_data();
        do{
            System.out.println("For storing student's information");
            System.out.println("""
                    1.Create a table
                    2.Delete a table
                    3.Insert Data on the table
                    4.Search for a student
                    5.Show the table(only roll and student name)
                    6.Counting the student's data already inserted
                    7.Erase the table's data
                    8.Change the schema of the table(add/delete/rename column,rename table
                    9.Exit""");
            int choice= Integer.parseInt(scanner.nextLine());
            String name;
            switch(choice){
                case 1:
                    System.out.println("Provide the name for creating table");
                    name=scanner.nextLine();
                    serverData.createTable(name);
                    break;
                case 2:
                    System.out.println("Provide the name for deleting table");
                    name=scanner.nextLine();
                    serverData.deleteTable(name);
                    break;
                case 3:
                    System.out.println("Provide the table name where you want to insert data");
                    name=scanner.nextLine();
                    serverData.addData(name);
                    break;
                case 4:
                    System.out.println("Provide the table name from where you want to search data");
                    name=scanner.nextLine();
                    serverData.search(name);
                    break;
                case 5:
                    System.out.println("Provide the table name to change schema");
                    name=scanner.nextLine();
                    serverData.showTable(name);
                    break;
                case 6:
                    System.out.println("Provide the table name to count the data already inserted");
                    name=scanner.nextLine();
                    serverData.numberOfStudent(name);
                    break;
                case 7:
                    System.out.println("Provide the table name from where you want to erase the all data inserted in the table");
                    name=scanner.nextLine();
                    serverData.eraseData(name);
                    break;
                case 8:
                    System.out.println("Provide the table name to show data");
                    name=scanner.nextLine();
                    serverData.changeSchema(name);
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("you enter wrong choice");
            }
        }while(true);
    }

}
