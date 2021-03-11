package com.donkee.house.dao.impl;

import com.donkee.house.dao.IDjrzDao;
import com.donkee.house.entity.Djrz;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.sql.SQLException;
import java.util.List;

public class DjrzDaoImpl implements IDjrzDao {

	@Override
	public List<Djrz> listAll() throws Exception {
		String sql = "select * from mydj";
		List<Djrz> list = JDBCUtil2.executeQuery(sql, Djrz.class);
		return list;
	}

	@Override
	public int save(Djrz djrz) throws Exception {
		String sql = "insert into mydj(mdate,eid,cid,hid,mimg,myj,myzj,mflag,mbegintime) values(?,?,?,?,?,?,?,?,?)";
		int i = JDBCUtil2.executeUpdate(sql, djrz.getMdate(), djrz.getEid(), djrz.getCid(), djrz.getHid(), djrz.getMimg(), djrz.getMyj(), djrz.getMflag(), djrz.getMbegintime());
		return i;
	}

	@Override
	public Djrz findById(Integer mid) throws Exception {
		String sql = "select * from mydj where mid = "+mid;
		List<Djrz> list = JDBCUtil2.executeQuery(sql, Djrz.class);
		if (list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}


	@Override
	public int update(Djrz t) throws SQLException {
		String sql = "update mydj set mdate = ?,eid = ?,cid = ?,hid =?,mimg = ?,myj =?,myzj =?,mflag =?,mbegintime=? where mid = ?";
		int i = JDBCUtil2.executeUpdate(sql, t.getMdate(), t.getEid(), t.getCid(), t.getHid(), t.getMimg(), t.getMyj(), t.getMyzj(), t.getMflag(), t.getMbegintime());
		return i;
	}

	@Override
	public int del(Integer mid) throws Exception {
		return 0;
	}


	@Override
	public PageInfo<Djrz> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "select a.*,b.haddress,b.hfh,c.cname,c.ctel,d.erealname from mydj a inner join myhouse b on a.hid=b.hid"
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		String sql2 = "select count(1) from mydj a inner join myhouse b on a.hid=b.hid" 
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		List<Djrz> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Djrz.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Djrz> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

}
