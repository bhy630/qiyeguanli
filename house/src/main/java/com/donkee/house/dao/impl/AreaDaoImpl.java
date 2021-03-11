package com.donkee.house.dao.impl;

import com.donkee.house.dao.IAreaDao;
import com.donkee.house.entity.Area;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class AreaDaoImpl implements IAreaDao {
    @Override
    public List<Area> listAll() throws Exception {
        String sql = "select * from myarea";
        List<Area> list = JDBCUtil2.executeQuery(sql, Area.class);
//        List<Js> lists = new ArrayList<>();
//        String sql = "select * from myarea";
//        Connection conn = JDBCUtil.getConnection();
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            Area area = new Area();
//            area.setAid(rs.getInt("aid"));
//            area.setAname(rs.getString("aname"));
//            lists.add(area);
//        }
//        return lists;
        return list;
    }

    @Override
    public Area findById(Integer areaId) throws Exception {
        String sql = "select * from myarea where aid = "+areaId;
        List<Area> list = JDBCUtil2.executeQuery(sql, Area.class);
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
    public int save(Area area) throws Exception {
        String sql = "insert into myarea values(null,?)";
        int i = JDBCUtil2.executeUpdate(sql, area.getAname());

        return i;
    }

    @Override
    public int update(Area area) throws Exception {
        String sql = "update set myarea aname = ? where aid = ? ";
        int i = JDBCUtil2.executeUpdate(sql, area.getAname(), area.getAid());

        return i;
    }

    @Override
    public int del(Integer areaId) throws Exception {
        String sql = "delete from myarea where aid = ?";
        int i = JDBCUtil2.executeUpdate(sql, areaId);
        return i;
    }

    @Override
    public PageInfo<Area> findByPage(int pageNum, int pageSize) throws Exception {
        String sql = "select * from myarea";
        String sql2 = "select count(1) from myarea";
        List<Area> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Area.class);
        int total = JDBCUtil2.total(sql2);
        PageInfo<Area> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(total);

        return pageInfo;
    }
    public static void main(String[] args) throws Exception {
        PageInfo<Area> pageInfo = new AreaDaoImpl().findByPage(1,5);
        System.out.println(pageInfo);
    }
}
