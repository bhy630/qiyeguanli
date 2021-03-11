package com.donkee.house.controller;

import com.donkee.house.dao.ICustomDao;
import com.donkee.house.dao.impl.CustomDaoImpl;
import com.donkee.house.entity.Custom;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CustomController")
public class CustomController extends HttpServlet {
    private ICustomDao customDao = new CustomDaoImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");
        if(type != null){
            switch (type){
                case "list":
                    try {
                        list(request,response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "toAdd":
                    toAdd(request,response);
                    break;
                case "add":
                    try {
                        add(request,response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "toUpdate":
                    try {
                        toUpdate(request,response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "update":
                    try {
                        update(request,response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "delete":
                    try {
                        delete(request,response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "listName":
                    try {
                        listName(request,response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String current = request.getParameter("current");
        int pageNum = 1;
        if (current != null) {
            pageNum = Integer.parseInt(current);
        }
        PageInfo<Custom> pageInfo = customDao.findByPage(pageNum,3);
        String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request,response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("custom.cname");
        String sex = request.getParameter("custom.csex");
        String ctel = request.getParameter("custom.ctel");
        String ctel1 = request.getParameter("custom.ctel1");
        String ccard = request.getParameter("custom.ccard");
        Custom custom = new Custom(name,sex,ctel,ctel1,ccard,ccard);
        int save = customDao.save(custom);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(save);
        response.getWriter().write(jsonStr);
    }

    protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int cid = Integer.parseInt(request.getParameter("cid"));
        Custom custom = customDao.findById(cid);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(custom);
        response.getWriter().write(jsonStr);

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int cid = Integer.parseInt(request.getParameter("custom.cid"));
        String name = request.getParameter("custom.cname");
        String sex = request.getParameter("custom.csex");
        String ctel = request.getParameter("custom.ctel");
        String ctel1 = request.getParameter("custom.ctel1");
        String ccard = request.getParameter("custom.ccard");
        Custom custom = new Custom(cid,name,sex,ctel,ctel1,ccard);
        int i = customDao.update(custom);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int cid = Integer.parseInt(request.getParameter("cid"));
        int i = customDao.del(cid);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void listName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Custom> customList = customDao.listName();
        String jsonStr = new ObjectMapper().writeValueAsString(customList);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }
}
