package com.donkee.house.controller;

import com.donkee.house.dao.IHouseDao;
import com.donkee.house.dao.impl.HouseDaoImpl;
import com.donkee.house.entity.House;
import com.donkee.house.util.PageInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class DeptServlet
 */
@WebServlet("/HouseController")
public class HouseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IHouseDao houseDao = new HouseDaoImpl();

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
				case "list2":
				try {
					list2(request, response);
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
			case "listAll":
				try {
					listAll(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		PageInfo<House> pageInfo = houseDao.findByPage(pageNum,3);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload =new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> fileList = new ArrayList<>();
		fileList = upload.parseRequest(request);
		String targetPath = this.getServletContext().getRealPath("upload") + "/";
		String fileName;
		String himg = "";
		Map<String,String> paramsMap = new HashMap<>();
		for(FileItem item : fileList){
			if (!item.isFormField()){//文件域
				fileName = item.getName();
				himg+="upload/"+fileName+"、";
				File saveFile = new File(targetPath + fileName);
				item.write(saveFile);
			}else {
				paramsMap.put(item.getFieldName(),item.getString("utf-8"));
			}
		}
		himg = himg.substring(0,himg.lastIndexOf("、"));
		String sid = paramsMap.get("house.sid");
		String aid = paramsMap.get("house.aid");
		String haddress = paramsMap.get("house.haddress");
		String hfh = paramsMap.get("house.hfh");
		String hhx = paramsMap.get("house.hhx");
		String hmj = paramsMap.get("house.hmj");
		String hcx = paramsMap.get("house.hcx");
		String hmoney = paramsMap.get("house.hmoney");
		String hwf = paramsMap.get("house.hwf");
		String hdx = paramsMap.get("house.hdx");
		String hsf = paramsMap.get("house.hsf");
		String hmq = paramsMap.get("house.hmq");
		String dkd = paramsMap.get("house.dkd");
		String skd = paramsMap.get("house.skd");
		String mkd = paramsMap.get("house.mkd");
		String hjp = paramsMap.get("house.hjp");
		String hremark = paramsMap.get("house.hremark");
		House house = new House();
		house.setSid(Integer.parseInt(sid));
		house.setAid(Integer.parseInt(aid));
		house.setHaddress(haddress);
		house.setHfh(hfh);
		house.setHhx(hhx);
		house.setHmj(hmj);
		house.setHcx(hcx);
		house.setHmoney(Float.parseFloat(hmoney));
		house.setHwf(Float.parseFloat(hwf));
		house.setHdx(Float.parseFloat(hdx));
		house.setHsf(Float.parseFloat(hsf));
		house.setHmq(Float.parseFloat(hmq));
		house.setDkd(Float.parseFloat(dkd));
		house.setSkd(Float.parseFloat(skd));
		house.setMkd(Float.parseFloat(mkd));
		house.setHjp(hjp);
		house.setHremark(hremark);
		house.setHimg(himg);
		house.setHflag("0");
		int i = houseDao.save(house);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}


	protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int hid = Integer.parseInt(request.getParameter("hid"));
		House house = houseDao.findById(hid);
		response.setCharacterEncoding("utf-8");
		String jsonStr = new ObjectMapper().writeValueAsString(house);
		response.getWriter().write(jsonStr);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload =new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<FileItem> fileList = new ArrayList<>();
		fileList = upload.parseRequest(request);
		String targetPath = this.getServletContext().getRealPath("upload") + "/";
		String fileName;
		String himg = "";
		Map<String,String> paramsMap = new HashMap<>();
		for(FileItem item : fileList){
			if (!item.isFormField()){//文件域
				fileName = item.getName();
				himg+="upload/"+fileName+"、";
				File saveFile = new File(targetPath + fileName);
				item.write(saveFile);
			}else {
				paramsMap.put(item.getFieldName(),item.getString("utf-8"));
			}
		}
		himg = himg.substring(0,himg.lastIndexOf("、"));
		int hid = Integer.parseInt(paramsMap.get("house.hid"));
		int sid = Integer.parseInt(paramsMap.get("house.sid"));
		int aid = Integer.parseInt(paramsMap.get("house.aid"));
		String haddress =paramsMap.get("house.haddress");
		String hfh = paramsMap.get("house.hfh");
		String hhx = paramsMap.get("house.hhx");
		String hmj = paramsMap.get("house.hmj");
		String hcx = paramsMap.get("house.hcx");
		Float hmoney = Float.parseFloat(paramsMap.get("house.hmoney")) ;
		Float hwf = Float.parseFloat(paramsMap.get("house.hwf"));
		Float hdx = Float.parseFloat(paramsMap.get("house.hdx"));
		Float hsf = Float.parseFloat(paramsMap.get("house.hsf"));
		Float hmq = Float.parseFloat(paramsMap.get("house.hmq"));
		Float dkd = Float.parseFloat(paramsMap.get("house.dkd"));
		Float skd = Float.parseFloat(paramsMap.get("house.skd"));
		Float mkd = Float.parseFloat(paramsMap.get("house.mkd"));
		String hjp = paramsMap.get("house.hjp");
		String hremark = paramsMap.get("house.hremark");
		House house = new House();
		house.setHid(hid);
		house.setSid(sid);
		house.setAid(aid);
		house.setHaddress(haddress);
		house.setHfh(hfh);
		house.setHhx(hhx);
		house.setHmj(hmj);
		house.setHcx(hcx);
		house.setHmoney(hmoney);
		house.setHwf(hwf);
		house.setHdx(hdx);
		house.setHsf(hsf);
		house.setHmq(hmq);
		house.setDkd(dkd);
		house.setSkd(skd);
		house.setMkd(mkd);
		house.setHjp(hjp);
		house.setHremark(hremark);
		house.setHimg(himg);
		house.setHflag("0");
		int i = houseDao.update(house);
		String jsonStr = new ObjectMapper().writeValueAsString(i);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	}

	protected void list2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String current = request.getParameter("current");
		int pageNum = 1;
		if (current!=null)
			pageNum = Integer.parseInt(current);
		String sid = request.getParameter("sid");
		String aid = request.getParameter("aid");
		String zt = request.getParameter("zt");
		House house = new House();
		house.setSid(Integer.parseInt(sid));
		house.setAid(Integer.parseInt(aid));
		house.setHflag(zt);
		PageInfo<House> pageInfo = houseDao.findByCondition(pageNum, 5, house);
		String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);

	}

	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<House> houseList = houseDao.listAll();
		String jsonStr = new ObjectMapper().writeValueAsString(houseList);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonStr);
	}
}
