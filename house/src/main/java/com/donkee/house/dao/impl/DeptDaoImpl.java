package com.donkee.house.dao.impl;

import com.donkee.house.dao.IDeptDao;
import com.donkee.house.entity.Dept;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class DeptDaoImpl implements IDeptDao {
    @Override
    public List<Dept> listAll() throws Exception {
        String sql = "select * from mydept";
        List<Dept> list = JDBCUtil2.executeQuery(sql, Dept.class);
//        List<Dept> lists = new ArrayList<>();
//        String sql = "select * from mydept";
//        Connection conn = JDBCUtil.getConnection();
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            Dept dept = new Dept();
//            dept.setPid(rs.getInt("pid"));
//            dept.setPname(rs.getString("pname"));
//            dept.setPflag(rs.getInt("pflag"));
//            dept.setPremark(rs.getString("premark"));
//            lists.add(dept);
//        }
//        return lists;
        return list;
    }

    @Override
    public Dept findById(Integer deptId) throws Exception {
        String sql = "select * from mydept where pid = "+deptId;
        List<Dept> list = JDBCUtil2.executeQuery(sql, Dept.class);
        if (list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
//        Dept dept = new Dept();
//        String sql = "select * from mydept where pid = ?";
//        Connection conn = JDBCUtil.getConnection();
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1,deptId);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//            dept.setPid(rs.getInt("pid"));
//            dept.setPname(rs.getString("pname"));
//            dept.setPflag(rs.getInt("pflag"));
//            dept.setPremark(rs.getString("premark"));
//        }
//        return dept;
    }

    @Override
    public int save(Dept dept) throws Exception {
        String sql = "insert into mydept(pname,pflag,premark) values(?,?,?)";
        int i = JDBCUtil2.executeUpdate(sql, dept.getPname(), dept.getPflag(), dept.getPremark());
        return i;
    }

    @Override
    public int update(Dept dept) throws Exception {
        String sql = "update  mydept set pname = ?,pflag = ?,premark = ? where pid = ? ";
        int i = JDBCUtil2.executeUpdate(sql, dept.getPname(), dept.getPflag(), dept.getPremark(), dept.getPid());

        return i;
    }

    @Override
    public int del(Integer deptId) throws Exception {
        String sql = "delete from mydept where pid = ?";
        int i = JDBCUtil2.executeUpdate(sql,deptId );

        return i;
    }

    @Override
    public PageInfo<Dept> findByPage(int pageNum, int pageSize) throws Exception {
        String sql = "select * from mydept";
        String sql2 = "select count(1) from mydept";
        List<Dept> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Dept.class);
        int total = JDBCUtil2.total(sql2);
        PageInfo<Dept> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(total);

        return pageInfo;
    }

    public static void main(String[] args) throws Exception {
        PageInfo<Dept> pageInfo = new DeptDaoImpl().findByPage(1,5);
        System.out.println(pageInfo);
    }
}
