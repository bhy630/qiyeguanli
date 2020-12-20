package com.donkee.house.dao;

import com.donkee.house.dao.impl.JsDaoImpl;
import org.junit.Test;

import java.util.List;

public class Js {
    @Test
    public void selectAll() throws Exception {
        IJsDao jsDao = new JsDaoImpl();
        List<com.donkee.house.entity.Js> list = jsDao.listAll();
        System.out.println(list);
    }
}
