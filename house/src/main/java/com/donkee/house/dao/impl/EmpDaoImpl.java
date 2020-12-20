package com.donkee.house.dao.impl;

import com.donkee.house.dao.IEmpDao;
import com.donkee.house.entity.Emp;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class EmpDaoImpl implements IEmpDao {
    @Override
    public List<Emp> listAll() throws Exception {
        String sql = "select * from myemp";
        List<Emp> list = JDBCUtil2.executeQuery(sql, Emp.class);
        return list;
    }

    @Override
    public int save(Emp emp) throws Exception {
        String sql = "insert into myemp(pid,jid,,ename,epsw,erealname,etel,eaddress,eflag,eremark) values(?,?,?,?,?,?,?,?,?)";
        int i = JDBCUtil2.executeUpdate(sql, emp.getPid(), emp.getJid(), emp.getEname(), emp.getEpsw(), emp.getErealname(), emp.getEtel(), emp.getEaddress(), emp.getEflag(), emp.getEremark());
        return i;

    }

    @Override
    public Emp findById(Integer eid) throws Exception {
       String sql  = "select a.*,b.pname,c.jname from myemp a inner join mydept b on a.pid=b.pid"
                +" inner join myjs c on a.jid=c.jid where eid = "+eid;
        List<Emp> list = JDBCUtil2.executeQuery(sql, Emp.class);
        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int update(Emp emp) throws Exception {
       String sql = "update myemp set pid = ?,jid = ?,ename = ?,epsw = ?,erealname = ?,etel = ?,eaddress = ?,eflag = ?,eremark = ? where eid = ?";
        int i = JDBCUtil2.executeUpdate(sql, emp.getPid(), emp.getJid(), emp.getEname(), emp.getEpsw(), emp.getErealname(), emp.getEtel(), emp.getEaddress(), emp.getEflag(), emp.getEremark(), emp.getEid());
        return i;
    }

    @Override
    public int del(Integer empId) throws Exception {
        String sql = "delete from myemp where eid = ?";
        int i = JDBCUtil2.executeUpdate(sql, empId);
        return i;
    }

    @Override
    public PageInfo<Emp> findByPage(int pageNum, int pageSize) throws Exception {
        String sql = "select a.*,b.pname,c.jname from myemp a inner join mydept b on a.pid=b.pid"
                +" inner join myjs c on a.jid=c.jid";
        String sql2 = "select count(1) from myemp a inner join mydept b on a.pid=b.pid"
                +" inner join myjs c on a.jid=c.jid";
        PageInfo<Emp> pageInfo = new PageInfo<>();
        pageInfo.setList(JDBCUtil2.findByPage(sql,pageNum,pageSize,Emp.class));
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(JDBCUtil2.total(sql2));
        return pageInfo;
    }

    @Override
    public Emp findByName(String ename) throws Exception {
        String sql = "select * from myemp where ename = ?";
        Object [] params = {ename};
        List<Emp> list = JDBCUtil2.executeQuery(sql, Emp.class, params);
        if (list.size()>0){
            return list.get(0);
        }else {
            return null;
        }

    }
}
