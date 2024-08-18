

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

class MainClass{
    public static void main(String[] args) {
        Server_data sd=new Server_data();
       // sd.createDatabase("Student");
        sd.addData();
    }
}
