package com.donkee.house.dao;

import com.donkee.house.util.PageInfo;

import java.util.List;

public interface IBaseDao<T>{
    //查询所有
    List<T> listAll() throws Exception;
    //保存
    int save(T t) throws Exception;
    //根据id读取
    T findById(Integer id) throws Exception;
    //修改
    int update(T t) throws Exception;
    //删除
    int del(Integer id) throws Exception;

    //分页查询
    PageInfo<T> findByPage(int pageNum,int pageSize) throws Exception;
}
