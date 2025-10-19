package com.example.crudapp.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbcon;

    private Connection conn;

    private DbConnection() throws SQLException, ClassNotFoundException {
        //need to load Driver class to the ram
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Create connection with mysql db server
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_10", "root", "root");
    }

    public static DbConnection DbConnection() throws SQLException, ClassNotFoundException {
        if (dbcon==null){
            dbcon=new DbConnection();
        }
        return dbcon;
    }

    public Connection getConn(){
        return conn;
    }
}
