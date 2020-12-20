package com.donkee.house.dao.impl;

import com.donkee.house.dao.IHouseDao;
import com.donkee.house.entity.House;
import com.donkee.house.util.JDBCUtil2;
import com.donkee.house.util.PageInfo;

import java.util.List;

public class HouseDaoImpl implements IHouseDao {
    @Override
    public List<House> listAll() throws Exception {
        String sql = "select * from myhouse";
        List<House> list = JDBCUtil2.executeQuery(sql, House.class);
        return list;
    }

    @Override
    public int save(House t) throws Exception {
        String sql = "insert into myhouse(sid,aid,haddress,hfh,hhx,hmj,hcx,hmoney,hwf,hdx,hsf,hmq,dkd,skd,mkd,hjp,hremark,himg,hflag) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int count = JDBCUtil2.executeUpdate(sql,t.getSid(),t.getAid(),t.getHaddress(),t.getHfh(),t.getHhx(),t.getHmj(),t.getHcx(),t.getHmoney(),t.getHwf(),t.getHdx(),t.getHsf(),t.getHmq(),t.getDkd(),t.getSkd(),t.getMkd(),t.getHjp(),t.getHremark(),t.getHimg(),t.getHflag() );
        return count;
    }

    @Override
    public House findById(Integer hId) throws Exception {
        String sql = "select * from myhouse where hid ="+hId;
        List<House> list = JDBCUtil2.executeQuery(sql, House.class);
        if(list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public int update(House house) throws Exception {
        String sql = "update myhouse set sid = ?,aid = ?,haddress = ?,hfh =?,hhx =?,hmj =?,hcx=?,hmoney=?,hwf=?,hdx=?,hsf=?,hmq=?,dkd=?,skd=?,mkd=?,hjp=?,hremark=?,himg=?,hflag=? where hid =?";
        int i = JDBCUtil2.executeUpdate(sql, house.getSid(), house.getAid(), house.getHaddress(), house.getHfh(), house.getHhx(), house.getHmj(), house.getHcx(), house.getHmoney(), house.getHwf(), house.getHdx(), house.getHsf(), house.getHmq(), house.getDkd(), house.getSkd(), house.getMkd(), house.getHjp(), house.getHremark(), house.getHimg(), house.getHflag(), house.getHid());

        return i;
    }

    @Override
    public int del(Integer hid) throws Exception {
        String sql = "delete from myhouse where hid =?";
        return JDBCUtil2.executeUpdate(sql,hid);
    }

    @Override
    public PageInfo<House> findByPage(int pageNum, int pageSize) throws Exception {
        String sql = "select a.*,b.sname,c.aname from myhouse a inner join mysort b on a.sid=b.sid"
                +" inner join myarea c on a.aid=c.aid";
        String sql2 = "select count(1) from myhouse a inner join mysort b on a.sid=b.sid" +
                " inner join myarea c on a.aid=c.aid";
        List<House> list = JDBCUtil2.findByPage(sql, pageNum, pageSize, House.class);
        int total = JDBCUtil2.total(sql2);
        PageInfo<House> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);//一定要放在总记录数之前
        pageInfo.setTotal(total);

        return pageInfo;
    }


    @Override
    public PageInfo<House> findByCondition(int pageNum, int pageSize, House house) throws Exception {
        String pub = " from myhouse a inner join myarea b on a.aid=b.aid inner join mysort c on a.sid=c.sid inner join mydj d on a.hid=d.hid inner join mycus e on d.cid=e.cid inner join myemp f on d.eid=f.eid where 1=1";
        //动态查询，根据条件动态生成SQL
        if (house!=null) {
            if (house.getSid()!=0)
                pub+=" and a.sid="+house.getSid();
            if (house.getAid()!=0)
                pub+=" and a.aid="+house.getAid();
            if (house.getHflag()!=null && !house.getHflag().equals("-1"))
                pub+=" and a.hflag="+house.getHflag();
            if(house.getHid()!=0 && house.getHid()!=-1) {
                pub+=" and a.hid="+house.getHid();
            }
        }
        String sql = "select a.*,c.sname,b.aname,d.mid,d.mdate,d.myj,d.myzj,e.cname,e.ctel,f.erealname"+pub;
        String sql2 = "select count(1)"+pub;
        List<House> list=null;
        try {
            list = JDBCUtil2.findByPage(sql, pageNum, pageSize, House.class);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo<House> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setList(list);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(JDBCUtil2.total(sql2));
        return pageInfo;

    }

}
