package com.donkee.house.controller;

import com.donkee.house.dao.IDjsfDao;
import com.donkee.house.dao.impl.DjsfDaoImpl;
import com.donkee.house.entity.Djsf;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/DjsfController")
public class DjsfController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IDjsfDao djsfDao = new DjsfDaoImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		if (type != null) {
			switch (type) {
			case "list":
				try {
					list(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "add":
				try {
					add(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "toUpdate":
				try {
					toUpdate(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "update":
				update(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			default:
				break;
			}
		}
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String current = request.getParameter("current") ;	
		int pageNum= 1;
		if(current!=null) 
			pageNum=Integer.parseInt(current);
		PageInfo<Djsf> pageInfo = djsfDao.findByPage(pageNum,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	

	protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int mid = Integer.parseInt(request.getParameter("mid"));
		int eid = Integer.parseInt(request.getParameter("eid"));
		float myzj = Float.parseFloat(request.getParameter("myzj"));
		String mbegintime = request.getParameter("mbegintime");
		Djsf djsf = new Djsf();
		djsf.setMid(mid);
		djsf.setEid(eid);
		djsf.setMyzj(myzj);
		djsf.setMbegintime(mbegintime);
		int i = djsfDao.save(djsf);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.getWriter().write(jsonStr);
	}

	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int mid = Integer.parseInt(request.getParameter("mid"));
		Djsf djsf = djsfDao.findById(mid);
		response.setCharacterEncoding("utf-8");
		String jsonStr = new ObjectMapper().writeValueAsString(djsf);
		response.getWriter().write(jsonStr);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}

}
