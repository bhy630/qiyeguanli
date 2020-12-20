package com.donkee.house.controller;

import com.donkee.house.dao.ISortDao;
import com.donkee.house.dao.impl.SortDaoImpl;
import com.donkee.house.entity.Sort;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SortController")
public class SortController extends HttpServlet {
    private ISortDao sortDao = new SortDaoImpl();
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
                case "listAll":
                    try {
                        listAll(request,response);
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
        PageInfo<Sort> pageInfo = sortDao.findByPage(pageNum,3);
        String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }
    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/sort/sort_add.jsp").forward(request,response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("sort.sname");
        Sort sort = new Sort(name);
        int i = sortDao.save(sort);
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

    protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int sid = Integer.parseInt(request.getParameter("sid"));
        Sort sort = sortDao.findById(sid);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(sort);
        response.getWriter().write(jsonStr);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("sort.sname");
        Sort sort = new Sort(name);
        int i = sortDao.update(sort);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int sid = Integer.parseInt(request.getParameter("sid"));
        int i = sortDao.del(sid);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Sort> jsList = sortDao.listAll();
        String jsonStr = new ObjectMapper().writeValueAsString(jsList);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }
}
