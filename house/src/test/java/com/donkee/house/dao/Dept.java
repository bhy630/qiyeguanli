package com.donkee.house.dao;

import com.donkee.house.dao.impl.DeptDaoImpl;
import org.junit.Test;

import java.util.List;

public class Dept {
    @Test
    public void selectAll() throws Exception {
        IDeptDao deptDao = new DeptDaoImpl();
        List<com.donkee.house.entity.Dept> list = deptDao.listAll();
        System.out.println(list);
    }
}
