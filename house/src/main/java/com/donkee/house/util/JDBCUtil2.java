package com.donkee.house.util;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtil2 {
    private static String driver = null;
    private static String url = null;
    private static String user = null;
    private static String pwd = null;

    static {
        Properties pro = new Properties();
        try {
            pro.load(JDBCUtil2.class.getClassLoader().getResourceAsStream("db.properties"));
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            pwd = pro.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(Connection conn) throws SQLException {
        conn.close();
    }
    public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
        if (conn != null) {
            conn.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (rs != null) {
            rs.close();
        }
    }
    public static void closeAll(Connection conn, PreparedStatement pstmt) throws SQLException {
        if (conn != null) {
            conn.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
    }
    public static int executeUpdate(String sql, Object...params) throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = getConn();
        pstmt = conn.prepareStatement(sql);
        setParamValues(pstmt,params);
        count = pstmt.executeUpdate();

        closeAll(conn,pstmt);
        return count;
    }

    public static <T> List<T> executeQuery(String sql,Class<T> clazz,Object...params) throws Exception {
        List<T> list = new ArrayList<>();
        Connection conn = null;
        conn = getConn();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        setParamValues(pstmt,params);
        ResultSet rs = pstmt.executeQuery();
        //ResultSetMetaData保存的是结果集(ResultSet)中的列的信息
        ResultSetMetaData rsmd = rs.getMetaData();
        //getColumnCount：返回结果集中的总列数
        int clnCount = rsmd.getColumnCount();
        //while循环遍历结果集涨所有的记录(行)
        while(rs.next()){
            T entity = null;

            entity = clazz.newInstance();
            for (int i = 0; i < clnCount; i++) {
                String clnName = rsmd.getColumnName(i+1);
                Object clnValue = rs.getObject(clnName);
                //System.out.println(clnName+"\t");.var

                Field field = clazz.getDeclaredField(clnName.toLowerCase());
                field.setAccessible(true);
                field.set(entity,clnValue);
            }
            list.add(entity);
        }
        closeConn(conn);
        return list;
    }

    public static <T> List<T> findByPage(String sql,int pageNum,int pageSize,Class<T> clazz,Object...params) throws Exception {
        int from =(pageNum-1)*pageSize;
        sql += " limit "+from+","+pageSize;
        return executeQuery(sql,clazz,params);
    }

    public static int total(String sql,Object...params) throws SQLException {
        int total = 0;
        Connection conn =null;
        conn = getConn();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        setParamValues(pstmt,params);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        total = rs.getInt(1);
        return total;
    }

    private static void setParamValues(PreparedStatement pstmt, Object... params) throws SQLException {
        if(params != null && params.length > 0){
            for (int i = 0; i < params.length;i++){
                pstmt.setObject(i+1,params[i]);
            }
        }
    }
}
