package com.donkee.house.controller;

import com.donkee.house.dao.IEmpDao;
import com.donkee.house.dao.impl.EmpDaoImpl;
import com.donkee.house.entity.Emp;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/EmpController")
public class EmpController extends HttpServlet {
    private IEmpDao empDao = new EmpDaoImpl();
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
                case "login":
                    try {
                        login(request,response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "findByName":
                    try {
                        findByName(request,response);
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
                case "updatePwd":
                    try {
                        updatePwd(request,response);
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
        PageInfo<Emp> pageInfo = empDao.findByPage(pageNum, 3);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);

        response.getWriter().write(jsonStr);
    }

    protected void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/jsp/dept/dept_add.jsp").forward(request,response);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString("");
        response.getWriter().write(jsonStr);
    }

    protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int eid = Integer.parseInt(request.getParameter("eid"));
        Emp emp = empDao.findById(eid);
        response.setCharacterEncoding("utf-8");
        String jsonStr = new ObjectMapper().writeValueAsString(emp);
        response.getWriter().write(jsonStr);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int eid = Integer.parseInt(request.getParameter("emp.eid"));
        int pid = Integer.parseInt(request.getParameter("emp.myjs.jid"));
        int jid = Integer.parseInt(request.getParameter("emp.mydept.pid"));
        String ename = request.getParameter("emp.ename");
        String epsw = request.getParameter("emp.epsw");
        String erealname = request.getParameter("emp.erealname");
        String etel = request.getParameter("emp.etel");
        String eadress = request.getParameter("emp.eaddress");
        int eflag = Integer.parseInt(request.getParameter("emp.eflag"));
        String eremark = request.getParameter("emp.eremark");
        Emp emp = new Emp(eid,pid,jid,ename,epsw,erealname,etel,eadress,eflag,eremark);
        int i = empDao.update(emp);
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int eid = Integer.parseInt(request.getParameter("eid"));


        int i = empDao.del(eid);

        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

    protected void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Emp> list = empDao.listAll();
        String jsonStr = new ObjectMapper().writeValueAsString(list);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oldpsw = request.getParameter("oldpsw");
        String ename = request.getParameter("emp.ename");
        String psw = request.getParameter("emp.epsw");
        if(oldpsw==null) {
            Emp loginEmp = empDao.findByName(ename);
            int mydata = 0;
            if(loginEmp==null || !loginEmp.getEpsw().equals(psw)) {
                mydata=0;
            }else {
                mydata=1;
            }
            request.getSession().setAttribute("eid", loginEmp.getEid());
            request.getSession().setAttribute("loginUser", loginEmp);
            String jsonStr = new ObjectMapper().writeValueAsString(mydata);
            response.getWriter().write(jsonStr);
        }else {
            Emp loginEmp = (Emp) request.getSession().getAttribute("loginUser");
            String password = loginEmp.getEpsw();
            if(oldpsw.equals(password)) {
                String jsonStr = new ObjectMapper().writeValueAsString(oldpsw);
                response.getWriter().write(jsonStr);
            }
        }
    }

    private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String newpsw = request.getParameter("newpsw");
        Emp loginEmp = (Emp) request.getSession().getAttribute("loginUser");
        int eid = loginEmp.getEid();
        Emp emp = new Emp();
        emp.setEpsw(newpsw);
        emp.setEid(eid);
        int update = empDao.update(emp);
        String jsonStr = new ObjectMapper().writeValueAsString(update);
        response.getWriter().write(jsonStr);

    }
    protected void findByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ename = request.getParameter("ename");
        Emp loginEmp = empDao.findByName(ename);
        int mydata = 0;
        if (loginEmp == null){
            mydata = 0;
        }else {
            mydata = 1;
        }
        String jsonStr = new ObjectMapper().writeValueAsString(mydata);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

}
