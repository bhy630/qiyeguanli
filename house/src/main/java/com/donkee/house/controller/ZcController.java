package com.donkee.house.controller;

import com.donkee.house.dao.IZcDao;
import com.donkee.house.dao.impl.ZcDaoImpl;
import com.donkee.house.entity.Zc;
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
@WebServlet("/ZcController")
public class ZcController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IZcDao zcDao = new ZcDaoImpl();

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
				try {
					update(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		PageInfo<Zc> pageInfo = zcDao.findByPage(pageNum,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	

	protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ctitle = request.getParameter("ctitle");
		String cremark = request.getParameter("cremark");
		Zc zc = new Zc();
		zc.setCtitle(ctitle);
		zc.setCremark(cremark);
		int i = zcDao.save(zc);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);

	}


	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cid = Integer.parseInt(request.getParameter("cid"));
		Zc zc = zcDao.findById(cid);
		String jsonStr = new ObjectMapper().writeValueAsString(zc);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}


	protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cid = Integer.parseInt(request.getParameter("cid"));
		String ctitle = request.getParameter("ctitle");
		String cremark = request.getParameter("cremark");
		Zc zc = new Zc();
		zc.setCid(cid);
		zc.setCtitle(ctitle);
		zc.setCremark(cremark);
		int i = zcDao.update(zc);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);

	}


	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}

}
