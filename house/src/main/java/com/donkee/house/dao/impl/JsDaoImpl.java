package com.donkee.house.dao.impl;

import com.donkee.house.dao.IJsDao;
import com.donkee.house.entity.Js;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class JsDaoImpl implements IJsDao {
    @Override
    public List<Js> listAll() throws Exception {
        String sql = "select * from myjs";
        List<Js> list = JDBCUtil2.executeQuery(sql, Js.class);
//        List<Js> lists = new ArrayList<>();
//        String sql = "select * from myjs";
//        Connection conn = JDBCUtil.getConnection();
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            Js js = new Js();
//            js.setJid(rs.getInt("jid"));
//            js.setJname(rs.getString("jname"));
//            lists.add(js);
//        }
//        return lists;
        return list;
    }

    @Override
    public Js findById(Integer jsId) throws Exception {
        String sql = "select * from myjs where jid = "+jsId;
        List<Js> list = JDBCUtil2.executeQuery(sql, Js.class);
        if (list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
//        Js js = new Js();
//        String sql = "select * from myjs where jid = ?";
//        Connection conn = JDBCUtil.getConnection();
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1,jsId);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            js.setJid(rs.getInt("jid"));
//            js.setJname(rs.getString("jname"));
//
//        }
//        return js;
    }

    @Override
    public int save(Js js) throws Exception {
        String sql = "insert into myjs values(null,?)";
        int i = JDBCUtil2.executeUpdate(sql, js.getJname());

        return i;
    }

    @Override
    public int update(Js js) throws Exception {
        String sql = "update set myjs jname = ? where jid = ? ";
        int i = JDBCUtil2.executeUpdate(sql, js.getJname(), js.getJid());

        return i;
    }

    @Override
    public int del(Integer jsId) throws Exception {
        String sql = "delete from myjs where jid = ?";
        int i = JDBCUtil2.executeUpdate(sql, jsId);
        return i;
    }

    @Override
    public PageInfo<Js> findByPage(int pageNum, int pageSize) throws Exception {
        String sql = "select * from myjs";
        String sql2 = "select count(1) from myjs";
        List<Js> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Js.class);
        int total = JDBCUtil2.total(sql2);
        PageInfo<Js> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(total);

        return pageInfo;
    }

    public static void main(String[] args) throws Exception {
        List<Js> jsList = new JsDaoImpl().listAll();
        System.out.println(jsList);
    }
}
