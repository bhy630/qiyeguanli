package com.donkee.house.controller;

import com.donkee.house.dao.IAreaDao;
import com.donkee.house.dao.impl.AreaDaoImpl;
import com.donkee.house.entity.Area;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AreaController")
public class AreaController extends HttpServlet {
    private IAreaDao areaDao = new AreaDaoImpl();
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
        PageInfo<Area> pageInfo = areaDao.findByPage(pageNum,3);
        String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }
    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/sort/sort_add.jsp").forward(request,response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("area.aname");
        Area area = new Area(name);
        int i = areaDao.save(area);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int aid = Integer.parseInt(request.getParameter("aid"));
        Area area = areaDao.findById(aid);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(area);
        response.getWriter().write(jsonStr);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("area.aname");
        Area area = new Area(name);
        int i = areaDao.update(area);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int aid = Integer.parseInt(request.getParameter("aid"));
        int i = areaDao.del(aid);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.getWriter().write(jsonStr);
    }

    protected void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Area> jsList = areaDao.listAll();
        String jsonStr = new ObjectMapper().writeValueAsString(jsList);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }
}
