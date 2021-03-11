package com.donkee.house.controller;

import com.donkee.house.dao.IShouruDao;
import com.donkee.house.dao.impl.ShouruDaoImpl;
import com.donkee.house.entity.Shouru;
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
@WebServlet("/ShouruController")
public class ShouruController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IShouruDao shouruDao = new ShouruDaoImpl();

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
		PageInfo<Shouru> pageInfo = shouruDao.findByPage(pageNum,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	

	protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int eid = Integer.parseInt(request.getParameter("eid"));
		float smoney = Float.parseFloat(request.getParameter("smoney"));
		String stm = request.getParameter("stm");
		String sremark = request.getParameter("sremark");
		Shouru shouru = new Shouru();
		shouru.setEid(eid);
		shouru.setSmoney(smoney);
		shouru.setStm(stm);
		shouru.setSremark(sremark);
		int i = shouruDao.save(shouru);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int sid = Integer.parseInt(request.getParameter("sid"));
		Shouru shouru = shouruDao.findById(sid);
		String jsonStr = new ObjectMapper().writeValueAsString(shouru);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}


	protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int sid = Integer.parseInt(request.getParameter("sid"));
		int eid = Integer.parseInt(request.getParameter("eid"));
		float smoney = Float.parseFloat(request.getParameter("smoney"));
		String stm = request.getParameter("stm");
		String sremark = request.getParameter("sremark");
		Shouru shouru = new Shouru();
		shouru.setSid(sid);
		shouru.setEid(eid);
		shouru.setSmoney(smoney);
		shouru.setStm(stm);
		shouru.setSremark(sremark);
		int i = shouruDao.update(shouru);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}


	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
