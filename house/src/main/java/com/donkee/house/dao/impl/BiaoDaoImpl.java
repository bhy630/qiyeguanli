package com.donkee.house.dao.impl;

import com.donkee.house.dao.IBiaoDao;
import com.donkee.house.entity.Biao;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class BiaoDaoImpl implements IBiaoDao {

	@Override
	public List<Biao> listAll() throws Exception {
		String sql = "select * from mybiao";
		List<Biao> list = JDBCUtil2.executeQuery(sql, Biao.class);
		return list;
	}

	@Override
	public int save(Biao biao) throws Exception {
		String sql = "insert into mybiao(hid,dkd,skd,mkd,mtime,eid) values(?,?,?,?,?,?)";
		int i = JDBCUtil2.executeUpdate(sql, biao.getHid(), biao.getDkd(), biao.getSkd(), biao.getMkd(), biao.getMtime(), biao.getEid());
		return i;
	}

	@Override
	public Biao findById(Integer bId) throws Exception {
		String sql = "select * from mybiao where bid = "+bId;
		List<Biao> list = JDBCUtil2.executeQuery(sql, Biao.class);
		if (list!=null){
			return list.get(0);
		}else {
			return null;
		}

	}


	@Override
	public int update(Biao biao) throws SQLException {
		String sql = "update mybiao set hid = ?,dkd = ?,skd = ?,mkd = ?,mtime = ?,eid = ? where bid = ?";
		int i = JDBCUtil2.executeUpdate(sql, biao.getHid(), biao.getDkd(), biao.getSkd(), biao.getMkd(), biao.getMtime(), biao.getEid(), biao.getBid());
		return i;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}

	@Override
	public PageInfo<Biao> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "SELECT a.*,c.haddress,c.hfh,e.erealname FROM mybiao a"
				+" INNER JOIN myhouse c ON a.hid=c.hid "
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM mybiao a INNER JOIN myhouse b ON a.hid=b.hid " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Biao> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Biao.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Biao> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

}
