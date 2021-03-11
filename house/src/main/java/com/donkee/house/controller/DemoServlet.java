package com.donkee.house.controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DemoServlet extends HttpServlet {


    //初始化
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    //销毁
    @Override
    public void destroy() {
        super.destroy();
    }

    //service方法客户端每次请求都会被调用
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    //处理客户端get请求
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    //处理客户端post请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
