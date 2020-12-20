package com.donkee.house.controller;

import com.donkee.house.dao.IZsrall1Dao;
import com.donkee.house.dao.impl.Zsrall1DaoImpl;
import com.donkee.house.entity.Zsrall1;
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
@WebServlet("/Zsrall1Controller")
public class Zsrall1Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IZsrall1Dao zsrall1Dao = new Zsrall1DaoImpl();

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
				toUpdate(request, response);
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
		PageInfo<Zsrall1> pageInfo = zsrall1Dao.findByPage(pageNum,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	


	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}


	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}

}
