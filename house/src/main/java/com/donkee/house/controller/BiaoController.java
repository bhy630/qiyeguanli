package com.donkee.house.controller;

import com.donkee.house.dao.IBiaoDao;
import com.donkee.house.dao.impl.BiaoDaoImpl;
import com.donkee.house.entity.Biao;
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
@WebServlet("/BiaoController")
public class BiaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBiaoDao biaoDao = new BiaoDaoImpl();

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
		PageInfo<Biao> pageInfo = biaoDao.findByPage(pageNum,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	

	protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int hid = Integer.parseInt(request.getParameter("hid"));
		float dkd = Float.parseFloat(request.getParameter("dkd"));
		float skd = Float.parseFloat(request.getParameter("skd"));
		float mkd = Float.parseFloat(request.getParameter("mkd"));
		String mtime = request.getParameter("mtime");
		int eid = Integer.parseInt(request.getParameter("eid"));
		Biao biao = new Biao(hid,eid,skd,mkd,dkd,mtime);
		int i = biaoDao.save(biao);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bid = Integer.parseInt(request.getParameter("bid"));
		Biao biao = biaoDao.findById(bid);
		String jsonStr = new ObjectMapper().writeValueAsString(biao);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int bid = Integer.parseInt(request.getParameter("bid"));
		int hid = Integer.parseInt(request.getParameter("hid"));
		float dkd = Float.parseFloat(request.getParameter("dkd"));
		float skd = Float.parseFloat(request.getParameter("skd"));
		float mkd = Float.parseFloat(request.getParameter("mkd"));
		String mtime = request.getParameter("mtime");
		int eid = Integer.parseInt(request.getParameter("eid"));
		Biao biao = new Biao(bid,hid,eid,skd,mkd,dkd,mtime);
		int i = biaoDao.update(biao);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}

}
