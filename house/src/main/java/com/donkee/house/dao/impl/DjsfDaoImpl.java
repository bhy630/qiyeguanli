package com.donkee.house.dao.impl;

import com.donkee.house.dao.IDjsfDao;
import com.donkee.house.entity.Djsf;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class DjsfDaoImpl implements IDjsfDao {

	@Override
	public List<Djsf> listAll() throws Exception {
		String sql = "select * from mysf";
		List<Djsf> list = JDBCUtil2.executeQuery(sql, Djsf.class);
		return list;
	}

	@Override
	public int save(Djsf djsf) throws Exception {
		String sql ="insert into mysf(mid,eid,myzj,mbegintime) values(?,?,?,?)";
		int i = JDBCUtil2.executeUpdate(sql, djsf.getMid(), djsf.getEid(), djsf.getMyzj(), djsf.getMbegintime());
		return i;
	}

	@Override
	public Djsf findById(Integer yid) throws Exception {
		String sql = "select * from mysf where yid="+yid;
		List<Djsf> list = JDBCUtil2.executeQuery(sql, Djsf.class);
		if (list.size()>0) {
			return list.get(0);
		} else {
			return null;
		}
	}


	@Override
	public int update(Djsf djsf) throws SQLException {
		String sql = "update mysf set mid = ?,eid = ?,myzj = ?,Mbegintime = ? where yid = ?";
		int i = JDBCUtil2.executeUpdate(sql, djsf.getMid(), djsf.getEid(), djsf.getMyzj(), djsf.getMbegintime(), djsf.getYid());
		return i;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}

	@Override
	public PageInfo<Djsf> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "SELECT a.*,c.haddress,c.hfh,d.cname,d.ctel,e.erealname FROM mysf a"
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid"
				+" INNER JOIN myemp e ON a.eid=e.eid";
		String sql2 = "SELECT count(1) FROM mysf a INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid" 
				+ " INNER JOIN myemp e ON a.eid=e.eid";
		List<Djsf> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Djsf.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Djsf> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

}
