package com.cc.service.impl;

import com.cc.dao.IProductDao;
import com.cc.domain.Product;
import com.cc.service.IProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;
    @Override
    public List<Product> findAll(int page,int size) throws Exception {

        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}
