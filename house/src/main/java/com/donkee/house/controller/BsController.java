package com.donkee.house.controller;

import com.donkee.house.dao.IBsDao;
import com.donkee.house.dao.impl.BsDaoImpl;
import com.donkee.house.entity.Bs;
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
@WebServlet("/BsController")
public class BsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBsDao bsDao = new BsDaoImpl();

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
				add(request, response);
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
			}
		}
	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String current = request.getParameter("current") ;	
		int pageNum= 1;
		if(current!=null) 
			pageNum=Integer.parseInt(current);
		PageInfo<Bs> pageInfo = bsDao.findByPage(pageNum,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bid = Integer.parseInt(request.getParameter("bid"));
		Bs bs = bsDao.findById(bid);
		String jsonStr = new ObjectMapper().writeValueAsString(bs);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}

}
