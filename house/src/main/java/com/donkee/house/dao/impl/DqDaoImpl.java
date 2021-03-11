package com.donkee.house.dao.impl;

import com.donkee.house.dao.IDqDao;
import com.donkee.house.entity.Dq;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class DqDaoImpl implements IDqDao {

	@Override
	public List<Dq> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Dq dq) throws Exception {
		return 0;
	}

	@Override
	public Dq findById(Integer id) throws Exception {
		return null;
	}

	@Override
	public int update(Dq t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}

	@Override
	public PageInfo<Dq> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "SELECT a.*,c.haddress,c.hfh,d.cname,d.ctel,e.erealname FROM mysf a"
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid"
				+" INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT count(1) FROM mysf a INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid" 
				+ " INNER JOIN myemp e ON a.eid=e.eid";
		List<Dq> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Dq.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Dq> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

}
