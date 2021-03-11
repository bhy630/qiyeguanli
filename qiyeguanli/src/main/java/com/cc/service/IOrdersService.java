package com.cc.service;

import com.cc.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(int ordersId) throws Exception;
}
