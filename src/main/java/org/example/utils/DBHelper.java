package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBHelper {
    private static String username;
    private static String password;
    private static String url;
    static {
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/DBConfig.properties");
            properties.load(fileInputStream);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){
       Connection connection1 = null;
        try {
            connection1 = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection1;
    }
    public static void closeConnection(ResultSet rs,Connection conn,Statement st){
        try {
            if(rs!=null) {
                rs.close();
            }
            if(st!=null) {
                st.close();
            }
            if(conn!=null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
