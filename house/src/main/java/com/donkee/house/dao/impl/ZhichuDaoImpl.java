package com.donkee.house.dao.impl;

import com.donkee.house.dao.IZhichuDao;
import com.donkee.house.entity.Zhichu;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class ZhichuDaoImpl implements IZhichuDao {

	@Override
	public List<Zhichu> listAll() throws Exception {
		String sql = "select * from myzhichu";
		List<Zhichu> list = JDBCUtil2.executeQuery(sql, Zhichu.class);
		return list;
	}

	@Override
	public int save(Zhichu zhichu) throws Exception {
		String sql = "insert into myzhichu(eid,zmoney,ztm,ztime,zremark) values(?,?,?,?,?)";
		int i = JDBCUtil2.executeUpdate(sql, zhichu.getEid(), zhichu.getZmoney(), zhichu.getZtm(), zhichu.getZtime(), zhichu.getZremark());
		return i;
	}

	@Override
	public Zhichu findById(Integer zId) throws Exception {
		String sql = "select * from myzhichu where zid ="+zId;
		List<Zhichu> list = JDBCUtil2.executeQuery(sql, Zhichu.class);
		if (list != null){
			return list.get(0);
		}else {
			return null;
		}

	}

	@Override
	public int update(Zhichu zhichu) throws SQLException {
		String sql = "update myzhichu set eid =?,zmoney = ?,ztm = ?,ztime = ?,zremark = ? where zid = ?";
		int i = JDBCUtil2.executeUpdate(sql, zhichu.getEid(), zhichu.getZmoney(), zhichu.getZtm(), zhichu.getZtime(), zhichu.getZremark(), zhichu.getZid());
		return i;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}

	@Override
	public PageInfo<Zhichu> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "SELECT a.*,e.erealname FROM myzhichu a"
				+"  INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT COUNT(1) FROM myzhichu a " 
				+ "  INNER JOIN myemp e ON a.eid=e.eid";
		List<Zhichu> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Zhichu.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Zhichu> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

}
