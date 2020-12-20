package com.donkee.house.dao;

import com.donkee.house.dao.impl.SortDaoImpl;
import org.junit.Test;

import java.util.List;

public class Sort {
    @Test
    public void selectAll() throws Exception {
        ISortDao sortDao = new SortDaoImpl();
        List<com.donkee.house.entity.Sort> list = sortDao.listAll();
        System.out.println(list);
    }
}
