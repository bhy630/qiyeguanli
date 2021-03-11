package com.donkee.house.dao.impl;

import com.donkee.house.dao.IZsrall2Dao;
import com.donkee.house.entity.Zsrall2;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class Zsrall2DaoImpl implements IZsrall2Dao {

	@Override
	public List<Zsrall2> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Zsrall2 zsrall2) throws Exception {
		return 0;
	}

	@Override
	public Zsrall2 findById(Integer id) throws Exception {
		return null;
	}

	@Override
	public int update(Zsrall2 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}

	@Override
	public PageInfo<Zsrall2> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "select a.*,b.haddress,b.hfh,c.cname,c.ctel,d.erealname from mydj a inner join myhouse b on a.hid=b.hid"
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		String sql2 = "select count(1) from mydj a inner join myhouse b on a.hid=b.hid" 
				+" inner join mycus c on a.cid=c.cid inner join myemp d on a.eid=d.eid";
		List<Zsrall2> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Zsrall2.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Zsrall2> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

	

}
