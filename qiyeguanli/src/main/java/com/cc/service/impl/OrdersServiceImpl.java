package com.cc.service.impl;

import com.cc.dao.IOrdersDao;
import com.cc.domain.Orders;
import com.cc.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {

        //参数pageNum是页码值   参数pagesize  代表是每页显示的条数
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(int ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
