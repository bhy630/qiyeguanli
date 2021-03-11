package com.donkee.house.dao.impl;

import com.donkee.house.dao.IZsrall1Dao;
import com.donkee.house.entity.Zsrall1;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class Zsrall1DaoImpl implements IZsrall1Dao {

	@Override
	public List<Zsrall1> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Zsrall1 zsrall1) throws Exception {
		return 0;
	}

	@Override
	public Zsrall1 findById(Integer hId) throws Exception {
		return null;
	}

	@Override
	public int update(Zsrall1 t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}

	@Override
	public PageInfo<Zsrall1> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "select a.*,b.sname,c.aname from myhouse a left join mysort b on a.sid=b.sid"
				+" left join myarea c on a.aid=c.aid group by a.hid";
		String sql2 = "select count(1) from myhouse a inner join mysort b on a.sid=b.sid" + 
				" inner join myarea c on a.aid=c.aid";
		List<Zsrall1> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Zsrall1.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Zsrall1> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

}
