package com.donkee.house.dao.impl;

import com.donkee.house.dao.IShouruDao;
import com.donkee.house.entity.Shouru;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class ShouruDaoImpl implements IShouruDao {

	@Override
	public List<Shouru> listAll() throws Exception {
		String sql = "select * from myshouru";
		List<Shouru> list = JDBCUtil2.executeQuery(sql, Shouru.class);
		return list;
	}

	@Override
	public int save(Shouru shouru) throws Exception {
		String sql = "insert into myshouru(eid,smoney,stm,stime,sremark) values(?,?,?,?,?)";
		int i = JDBCUtil2.executeUpdate(sql, shouru.getEid(), shouru.getSmoney(), shouru.getStm(), shouru.getStime(), shouru.getSremark());
		return i;
	}

	@Override
	public Shouru findById(Integer sId) throws Exception {
		String sql = "select * from myshouru where sid = "+sId;
		List<Shouru> list = JDBCUtil2.executeQuery(sql, Shouru.class);

		if (list!=null){
			return list.get(0);
		}else {
			return null;
		}

	}

	@Override
	public int update(Shouru shouru) throws SQLException {
		String sql = "update myshouru set eid = ?,smoney = ?,stm = ?,stime = ?,sremark = ? where sid = ?";
		int i = JDBCUtil2.executeUpdate(sql, shouru.getEid(), shouru.getSmoney(), shouru.getStm(), shouru.getStime(), shouru.getSremark(), shouru.getSid());
		return i;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}

	@Override
	public PageInfo<Shouru> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "SELECT a.*,e.erealname FROM myshouru a"
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM myshouru a " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Shouru> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Shouru.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Shouru> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

}
