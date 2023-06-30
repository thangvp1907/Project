package utils;

import java.sql.*;
public class ConnectData {
    public static Connection getConnect(){
        String port = "jdbc:mysql://127.0.0.1/facility_management";
        String user = "root";
        String pass = "thangabc";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(port,user,pass);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

}
