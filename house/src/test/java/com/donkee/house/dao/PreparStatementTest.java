package com.donkee.house.dao;

import com.donkee.house.util.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PreparStatementTest {
    @Test
    public void statementTest() throws Exception {
        Connection conn = new JDBCUtil().getConnection();
        String sql = "select * from myemp where ename = ? and epsw = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        String ename = "admin";
        String psw = "admin";
        ps.setString(1,ename);
        ps.setString(2,psw);

        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            System.out.println("登陆成功!");
        }else{
            System.out.println("登录失败！");
        }
        conn.close();
    }
}
