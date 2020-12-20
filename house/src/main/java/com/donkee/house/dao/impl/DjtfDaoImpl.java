package com.donkee.house.dao.impl;

import com.donkee.house.dao.IDjtfDao;
import com.donkee.house.entity.Djtf;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class DjtfDaoImpl implements IDjtfDao {

	@Override
	public List<Djtf> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Djtf djtf) throws Exception {
		return 0;
	}

	@Override
	public Djtf findById(Integer id) throws Exception {
		return null;
	}

	@Override
	public int update(Djtf t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int del(Integer id) throws Exception {
		return 0;
	}


	@Override
	public PageInfo<Djtf> findByPage(int pageNum, int pageSize) throws Exception {
		String sql = "SELECT a.*,c.haddress,c.hfh,d.cname,d.ctel,b.mdate,b.mbegintime FROM mytf a"
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid";
		String sql2 = "SELECT count(1) FROM mytf a" 
				+" INNER JOIN mydj b ON a.mid=b.mid INNER JOIN myhouse c ON b.hid=c.hid INNER JOIN mycus d ON b.cid=d.cid";
		List<Djtf> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, Djtf.class);
		int total = JDBCUtil2.total(sql2);
		PageInfo<Djtf> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(pageNum);
		pageInfo.setList(list);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotal(total);

		return pageInfo;
	}

}
