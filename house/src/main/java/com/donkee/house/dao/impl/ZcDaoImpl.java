package com.donkee.house.dao.impl;

import com.donkee.house.dao.IZcDao;
import com.donkee.house.entity.Zc;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class ZcDaoImpl implements IZcDao {

	@Override
	public List<Zc> listAll() throws Exception {
		String sql = "select * from myzc";
		List<Zc> list = JDBCUtil2.executeQuery(sql, Zc.class);
		return list;
	}

	@Override
	public int save(Zc zc) throws Exception {
		String sql = "insert into myzc(ctitle,ctime,cremark) values(?,?,?)";
		int i = JDBCUtil2.executeUpdate(sql, zc.getCtitle(), zc.getCtime(), zc.getCremark());
		return i;
	}

	@Override
	public Zc findById(Integer cId) throws Exception {
		String sql = "select * from myzc where cid ="+cId;
		List<Zc> list = JDBCUtil2.executeQuery(sql, Zc.class);
		if (list!=null){
			return list.get(0);
		}else {
			return null;
		}

	}

	@Override
	public int update(Zc zc) throws SQLException {
		String sql = "update myzc set ctitle = ?,ctime = ?,cremark = ? where cid =?";
		int i = JDBCUtil2.executeUpdate(sql, zc.getCtitle(), zc.getCtime(), zc.getCremark(), zc.getCid());
		return i;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}

	@Override
	public PageInfo<Zc> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "select * from myzc";
		String sql2 = "select count(1) from myzc";
		List<Zc> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Zc.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Zc> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

}
