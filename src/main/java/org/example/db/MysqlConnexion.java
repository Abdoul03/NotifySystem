package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class MysqlConnexion {
    private static volatile MysqlConnexion instance;

    public Connection con ;

    private MysqlConnexion(){
        String url = "jdbc:mysql://localhost:3306/notifications?serverTimezone=UTC";
        String user = "root";
        String passeWord = "@tuwindi2024";
        Properties properties = new Properties();
        properties.setProperty("user",user);
        properties.setProperty("password",passeWord);
        try {
            this.con = DriverManager.getConnection(url,properties);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static synchronized MysqlConnexion getInstance() {
        if (instance == null){
            instance = new MysqlConnexion();
        }
        return instance;
    }

}
