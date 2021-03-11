package com.donkee.house.controller;

import com.donkee.house.dao.IDeptDao;
import com.donkee.house.dao.impl.DeptDaoImpl;
import com.donkee.house.entity.Dept;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeptController")
public class DeptController extends HttpServlet {
    private IDeptDao deptDao = new DeptDaoImpl();
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
                case "listAll":
                    try {
                        listAll(request,response);
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
            }
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String current = request.getParameter("current");
        int pageNum = 1;
        if (current != null) {
            pageNum = Integer.parseInt(current);
        }
        PageInfo<Dept> pageInfo = deptDao.findByPage(pageNum,3);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
        response.getWriter().write(jsonStr);
    }

    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request,response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("dept.pname");
        String remark = request.getParameter("dept.premark");
        Dept dept = new Dept(name,0,remark);
        int i = deptDao.save(dept);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pid = Integer.parseInt(request.getParameter("pid"));
        Dept dept = deptDao.findById(pid);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(dept);
        response.getWriter().write(jsonStr);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pid = Integer.parseInt(request.getParameter("dept.pid"));
        String name = request.getParameter("dept.pname");
        String remark = request.getParameter("dept.premark");
        int flag = Integer.parseInt(request.getParameter("dept.pflag"));
        Dept dept = new Dept(pid,name,flag,remark);
        int i = deptDao.update(dept);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pid = Integer.parseInt(request.getParameter("pid"));
        int i = deptDao.del(pid);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Dept> deptList = deptDao.listAll();
        String jsonStr = new ObjectMapper().writeValueAsString(deptList);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }
}
