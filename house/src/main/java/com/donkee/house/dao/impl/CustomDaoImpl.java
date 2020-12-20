package com.donkee.house.dao.impl;

import com.donkee.house.dao.ICustomDao;
import com.donkee.house.entity.Custom;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class CustomDaoImpl implements ICustomDao {
    @Override
    public List<Custom> listAll() throws Exception {
        String sql = "select * from mycus";
        List<Custom> list = JDBCUtil2.executeQuery(sql, Custom.class);
        return list;
    }

    @Override
    public int save(Custom custom) throws Exception {
        String sql = "insert into mycus(cname,csex,ctel,ctel1,ccard) values(?,?,?,?,?)";
        int i = JDBCUtil2.executeUpdate(sql, custom.getCname(), custom.getCsex(), custom.getCtel(), custom.getCtel1(), custom.getCcard());
        return i;
    }

    @Override
    public Custom findById(Integer cusId) throws Exception {
        String sql = "select * from mycus where pid = "+cusId;
        List<Custom> list = JDBCUtil2.executeQuery(sql, Custom.class);
        if (list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int update(Custom custom) throws Exception {
        String sql = "update  mydept set cname = ?,csex = ?,ctel = ?,ctel1 = ?,ccard = ? where cid = ? ";
        int i = JDBCUtil2.executeUpdate(sql, custom.getCname(), custom.getCsex(), custom.getCtel(), custom.getCtel1(), custom.getCcard(), custom.getCid());
        return i;
    }

    @Override
    public int del(Integer cusId) throws Exception {
        String sql = "delete from mydept where pid = ?";
        int i = JDBCUtil2.executeUpdate(sql, cusId);

        return i;
    }

    @Override
    public PageInfo<Custom> findByPage(int pageNum, int pageSize) throws Exception {
        String sql = "select * from mycus";
        String sql2 = "select count(1) from mycus";
        List<Custom> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Custom.class);
        int total = JDBCUtil2.total(sql2);
        PageInfo<Custom> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(total);

        return pageInfo;
    }

    public List<Custom> listName() throws Exception {
        String sql = "select distinct cname from mycus";
        List<Custom> list = JDBCUtil2.executeQuery(sql, Custom.class);
        return list;
    }
}
