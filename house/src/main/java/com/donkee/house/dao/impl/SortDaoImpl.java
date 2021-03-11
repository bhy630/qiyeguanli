package com.donkee.house.dao.impl;

import com.donkee.house.dao.ISortDao;
import com.donkee.house.entity.Sort;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class SortDaoImpl implements ISortDao {
    @Override
    public List<Sort> listAll() throws Exception {
        String sql = "select * from mysort";
        List<Sort> list = JDBCUtil2.executeQuery(sql, Sort.class);
//        List<Sort> lists = new ArrayList<>();
//        String sql = "select * from mysort";
//        Connection conn = JDBCUtil.getConnection();
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            Sort sort = new Sort();
//            sort.setSid(rs.getInt("sid"));
//            sort.setSname(rs.getString("sname"));
//            lists.add(sort);
//        }
//        return lists;
        return list;
    }

    @Override
    public Sort findById(Integer sortId) throws Exception {
        String sql = "select * from mysort where sid = "+sortId;
        List<Sort> list = JDBCUtil2.executeQuery(sql, Sort.class);
        if (list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
//        Sort sort = new Sort();
//        String sql = "select * from mydept where pid = ?";
//        Connection conn = JDBCUtil.getConnection();
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1,sortId);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            sort.setSid(rs.getInt("sid"));
//            sort.setSname(rs.getString("sname"));
//        }
//        return sort;
    }

    @Override
    public int save(Sort sort) throws Exception {
        String sql = "insert into mysort values(null,?)";
        int i = JDBCUtil2.executeUpdate(sql, sort.getSname());

        return i;
    }

    @Override
    public int update(Sort sort) throws Exception {
        String sql = "update set mysort sname = ? where Sid = ? ";
        int i = JDBCUtil2.executeUpdate(sql, sort.getSname(), sort.getSid());
        return i;
    }

    @Override
    public int del(Integer sortId) throws Exception {
        String sql = "delete from mysort where sid = ?";
        int i = JDBCUtil2.executeUpdate(sql, sortId);
        return i;
    }

    @Override
    public PageInfo<Sort> findByPage(int pageNum, int pageSize) throws Exception {
        String sql = "select * from mysort";
        String sql2 = "select count(1) from mysort";
        List<Sort> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Sort.class);
        int total = JDBCUtil2.total(sql2);
        PageInfo<Sort> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(total);

        return pageInfo;
    }
}
