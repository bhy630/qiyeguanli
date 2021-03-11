package com.donkee.house.controller;

import com.donkee.house.dao.IZhichuDao;
import com.donkee.house.dao.impl.ZhichuDaoImpl;
import com.donkee.house.entity.Zhichu;
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
@WebServlet("/ZhichuController")
public class ZhichuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IZhichuDao zhichuDao = new ZhichuDaoImpl();

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
		PageInfo<Zhichu> pageInfo = zhichuDao.findByPage(pageNum,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	

	protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		float zmoney = Float.parseFloat(request.getParameter("zmoney"));
		String ztm = request.getParameter("ztm");
		String zremark = request.getParameter("zremark");
		int eid = Integer.parseInt(request.getParameter("eid"));
		Zhichu zhichu = new Zhichu();
		zhichu.setEid(eid);
		zhichu.setZmoney(zmoney);
		zhichu.setZtm(ztm);
		zhichu.setZremark(zremark);
		int i = zhichuDao.save(zhichu);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int zid = Integer.parseInt(request.getParameter("zid"));
		Zhichu zhichu = zhichuDao.findById(zid);
		String jsonStr = new ObjectMapper().writeValueAsString(zhichu);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}

}
