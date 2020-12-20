package com.donkee.house.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql:///house";
    private static String user = "root";
    private static String password = "7573411490";

    public static Connection getConnection() throws Exception {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }

    public static void closeConnection(Connection conn) throws SQLException {
        conn.close();
    }
}
