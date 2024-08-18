
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class MainClass{
    public static void main(String[] args) {
        StudentDAO sd=new StudentDAO();
       // sd.createDatabase("Student");
        sd.addData();
    }
}
class StudentDAO {
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    private String url="jdbc:mysql://localhost:3306/Student";
    private String user="root";
    private  String password="#Indrani20";
    private Connection con=null;
    private Statement statement=null;
    private Scanner scanner=null;
    private void connect(){
        scanner= new Scanner(System.in);
        try {
            con = DriverManager.getConnection(url, user, password);
            statement = con.createStatement();
            String query="USE Student";
            statement.executeUpdate(query);
            query = "SET SQL_SAFE_UPDATES = 0 ";
            statement.executeUpdate(query);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
//    public void createDatabase(String database) {
//        String query="CREATE DATABASE IF NOT EXISTS "+database;
//        connect();
//        try {
//            statement.executeUpdate(query);
//        }catch(SQLException e){
//            System.out.println(e.getMessage());
//        }
//        closeConnection();
//    }


    public void addData(){
        connect();
        String query = "INSERT INTO Student (roll,name,className,dob,contact)" +
                "values (?,?,?,?,?)";
        try (PreparedStatement pstatement = con.prepareStatement(query)) {
            System.out.println("How much data you want to entry");
            int num= Integer.parseInt(scanner.nextLine());
            while(num>0){
                System.out.print("Enter name");String name=scanner.nextLine();
                System.out.print("Enter roll");int roll=Integer.parseInt(scanner.nextLine());
                System.out.print("Enter class name");String className=scanner.nextLine();
                System.out.print("Enter DOB(DD-MM-YYYY)");String dob=scanner.nextLine();
                System.out.print("Enter contact");long contact=Long.parseLong(scanner.nextLine());

                /****  ***/
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date parsed = format.parse(dob);
                java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());


                pstatement.setInt(1, roll);
                pstatement.setString(2,name);
                pstatement.setString(3,className);
                pstatement.setDate(4, sqlDate);
                pstatement.setLong(5, contact);
                int count=pstatement.executeUpdate();
                if(count>0){
                    System.out.println("Data entered successfully");
                }
                num--;
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch (ParseException e) {
            System.out.println("Date format is incorrect");
        }
    }
    private void closeConnection(){
        try{
            if( statement != null)
                    statement.close();
            if( con != null)
                    con.close();
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
class Student{
    int roll;
    String name;

    int className;
    String dob;
    long contact;
}