package com.example.test;

import java.sql.*;

public class Database {
    private String user = "user";
    private String pass = "#1#2#3";
    private String url = "jdbc:mysql://localhost/atm";
    private Statement statement;

    public Database(){
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement()
    {
        return statement;
    }
}

