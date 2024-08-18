

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class StudentDAO {
    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


    private Connection con=null;
    private Statement statement=null;

    private void connect(){
        try {
            final  String url="jdbc:mysql://localhost:3306/Student";
            final String user="root";
            final  String password="#Indrani20";
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
//    protected void createDatabase(String database) {
//        String query="CREATE DATABASE IF NOT EXISTS "+database;
//        connect();
//        try {
//            statement.executeUpdate(query);
//        }catch(SQLException e){
//            System.out.println(e.getMessage());
//        }
//        closeConnection();
//    }
protected void createTable(String tableName){

        connect();
        String query="create table  if not exists "+tableName+"( roll int primary key,studentName varchar(100)," +
                "guardianName varchar(100),address varchar(150),contact bigint ,className varchar(5),dob DATE);";
        try {
            statement.executeUpdate(query);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        closeConnection();
}
protected void deleteTable(String tableName){

    connect();
    String query="drop table if exists "+tableName;
    try {
        statement.executeUpdate(query);
    }
    catch(SQLException e){
        System.out.println(e.getMessage());
    }
    closeConnection();
}
    protected void addData(String tableName){
        if(tableName==null){
            System.out.println("Table is not created..");
            return;
        }
        connect();

        String query = "INSERT INTO "+tableName+" (roll  ,studentName,className,dob,contact,guardianName,address)" +
                "values (?,?, ?,?, ?,?,?)";

        try (PreparedStatement pstatement = con.prepareStatement(query)) {
                Student.init();

                SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date parsed = obj.parse(Student.dob);
                java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());


                pstatement.setInt(1, Student.roll);
                pstatement.setString(2, Student.studentName);
                pstatement.setString(3, Student.className);
                pstatement.setDate(4, sqlDate);
                pstatement.setLong(5, Student.contact);
                pstatement.setString(6, Student.guardianName);
                pstatement.setString(7, Student.address);

                int count = pstatement.executeUpdate();
                if (count > 0) {
                    System.out.println("Data entered successfully");
                }


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch (ParseException e) {
            System.out.println("Date format is incorrect");
        }
        closeConnection();
    }

    protected void showTable(String tableName){
        connect();
        String query="select * from "+tableName;
        try {
            ResultSet resultset = statement.executeQuery(query);
            while(resultset.next()){
                System.out.println(resultset.getInt("roll")+":"+resultset.getString("studentName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        closeConnection();
    }

    protected void selectByName(String tableName,String studentName){
        connect();
        //Don't use String.format() method as it can inject attack
        String query = "select * from " + tableName + " where studentName = ? ";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,studentName);

            ResultSet resultset =preparedStatement.executeQuery();

            while(resultset.next()){
                System.out.println(resultset.getInt("roll")+":"+resultset.getString("studentName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
        closeConnection();
    }
    }
    protected void selectByRoll(String tableName,int roll){
        connect();
        //Don't use String.format() method as it can inject attack
        String query = "select * from " + tableName + " where roll = ? ";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,roll);

            ResultSet resultset =preparedStatement.executeQuery();

            while(resultset.next()){
                System.out.println(resultset.getInt("roll")+":"+resultset.getString("studentName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
        closeConnection();
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
    static int roll;
    static String studentName;
    static String guardianName;
    static String className;
    static String address;
    static long contact;
    static String dob;
    private static Scanner scanner=null;
    static public void init(){
            scanner=new Scanner(System.in);
            System.out.print("Enter name");studentName =scanner.nextLine();
            System.out.print("Enter Guardian name :");guardianName=scanner.nextLine();
            System.out.print("Enter roll :");roll=Integer.parseInt(scanner.nextLine());
            System.out.print("Enter class name :");className=scanner.nextLine();
            System.out.print("Enter DOB(DD-MM-YYYY):");dob=scanner.nextLine();
            System.out.print("Enter address:");address=scanner.nextLine();
            System.out.print("Enter contact:");contact=Long.parseLong(scanner.nextLine());
    }
}
