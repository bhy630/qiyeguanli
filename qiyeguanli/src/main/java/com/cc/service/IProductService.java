package com.cc.service;

import com.cc.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IProductService {

    //查询所有订单信息
    List<Product> findAll(int page,int size) throws Exception;

    //添加产品
    void save(Product product) throws Exception;
}
