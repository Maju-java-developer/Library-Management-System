package Connectivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    public static Connection getConnection()throws Exception{
        
        String Root = "jdbc:mysql://";
        String Host = "localhost/";
        String database = "librarymanagemnetsystem";
        
        String ServerURL = Root + Host + database;
        String username = "root";
        String password = "";
        
        Connection MyConn = DriverManager.getConnection(ServerURL, username, password);
        
        return MyConn;
    }
}