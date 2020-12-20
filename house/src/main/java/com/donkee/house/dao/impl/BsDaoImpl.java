package com.donkee.house.dao.impl;

import com.donkee.house.dao.IBsDao;
import com.donkee.house.entity.Bs;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class BsDaoImpl implements IBsDao {

	@Override
	public List<Bs> listAll() throws Exception {
		String sql = "select * from mybs";
		List<Bs> list = JDBCUtil2.executeQuery(sql, Bs.class);
		return list;
	}

	@Override
	public int save(Bs bs) throws Exception {
		String sql = "insert into mybs(hid,mtime,bremark,eid) values(?,?,?,?)";
		int i = JDBCUtil2.executeUpdate(sql, bs.getHid(), bs.getMtime(), bs.getBremark(), bs.getEid());
		return i;
	}

	@Override
	public Bs findById(Integer bId) throws Exception {
		String sql = "select * from mybs where bid = "+bId;
		List<Bs> list = JDBCUtil2.executeQuery(sql, Bs.class);
		if (list!=null){
			return list.get(0);
		}else {
			return null;
		}

	}

	@Override
	public int update(Bs bs) throws SQLException {
		String sql = "update mybs set hid = ?,mtime = ?,bremark = ?,eid =?";
		int i = JDBCUtil2.executeUpdate(sql, bs.getHid(), bs.getMtime(), bs.getBremark(), bs.getEid());
		return i;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}



	@Override
	public PageInfo<Bs> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "SELECT a.*,c.haddress,c.hfh,e.erealname FROM mybs a"
				+" INNER JOIN myhouse c ON a.hid=c.hid "
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM mybs a INNER JOIN myhouse b ON a.hid=b.hid " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Bs> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Bs.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Bs> pageInfo = new PageInfo<>();
		pageInfo.setList(list);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);
		return pageInfo;
	}

}
