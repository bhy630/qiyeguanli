package com.cc.dao;

import com.cc.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IProductDao {

    //根据ID查询产品
    @Select("select * from product where id=#{productId}")
    Product findById(int productId)throws Exception;

    //查询所有的产品信息
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    //添加产品
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;
}
