package com.donkee.house.dao.impl;

import com.donkee.house.dao.IHtDao;
import com.donkee.house.entity.Ht;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class HtDaoImpl implements IHtDao {
    @Override
    public List<Ht> listAll() throws Exception {
        String sql = "select * from myht";
        List<Ht> list = JDBCUtil2.executeQuery(sql, Ht.class);
        return list;
    }

    @Override
    public int save(Ht ht) throws Exception {
        String sql = "insert into myht(htname,htremark) values(?,?)";
        int i = JDBCUtil2.executeUpdate(sql, ht.getHtname(), ht.getHtremark());
        return i;
    }

    @Override
    public Ht findById(Integer htId) throws Exception {
        String sql = "select * from myht where htid = "+htId;
        List<Ht> list = JDBCUtil2.executeQuery(sql, Ht.class);
        if (list!=null){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int update(Ht ht) throws Exception {
        String sql = "update myht set htname =?,htremark = ? where htid = ?";
        int i = JDBCUtil2.executeUpdate(sql, ht.getHtname(), ht.getHtremark(), ht.getHtid());
        return i;
    }

    @Override
    public int del(Integer id) throws Exception {
        return 0;
    }

    @Override
    public PageInfo<Ht> findByPage(int pageNum, int pageSize) throws Exception {
        String sql = "select * from myht";
        String sql2 = "select count(1) from myht";

        List<Ht> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Ht.class);
        int total = JDBCUtil2.total(sql2);
        PageInfo<Ht> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setList(list);
        pageInfo.setTotal(total);
        return pageInfo;
    }
}
