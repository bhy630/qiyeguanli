package com.donkee.house.controller;

import com.donkee.house.dao.IHtDao;
import com.donkee.house.dao.impl.HtDaoImpl;
import com.donkee.house.entity.Ht;
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

@WebServlet("/HtController")
public class HtController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");
        if (type!=null){
            switch (type){
                case "list":
                    try {
                        list(request,response);
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
                case "add":
                    try {
                        add(request,response);
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

            }
        }
    }
    private IHtDao htDao = new HtDaoImpl();
    protected void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String current = request.getParameter("current");
        int pageNum = 1;
        if (current != null)
            pageNum = Integer.parseInt(current);
        PageInfo<Ht> pageInfo = htDao.findByPage(pageNum, 3);
        String jsonStr = new ObjectMapper().writeValueAsString(pageInfo);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> fileList = new ArrayList<>();
        fileList = upload.parseRequest(request);
        String targetPath = this.getServletContext().getRealPath("upload") + "/";
        String fileName;
        String himg = "";
        Map<String, String> paramsMap = new HashMap<>();
        for (FileItem item : fileList) {
            if (!item.isFormField()) {//文件域
                fileName = item.getName();
                himg += "upload/" + fileName + "、";
                File saveFile = new File(targetPath + fileName);
                item.write(saveFile);
            } else {
                paramsMap.put(item.getFieldName(), item.getString("utf-8"));
            }
        }
        String htname = paramsMap.get("ht.htname");
        Ht ht = new Ht();
        ht.setHtname(htname);
        ht.setHtremark(himg);
        int i = htDao.save(ht);
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

    protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int hid = Integer.parseInt(request.getParameter("hid"));
        Ht ht = htDao.findById(hid);
        String jsonStr = new ObjectMapper().writeValueAsString(ht);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> fileList = new ArrayList<>();
        fileList = upload.parseRequest(request);
        String targetPath = this.getServletContext().getRealPath("upload") + "/";
        String fileName;
        String himg = "";
        Map<String, String> paramsMap = new HashMap<>();
        for (FileItem item : fileList) {
            if (!item.isFormField()) {//文件域
                fileName = item.getName();
                himg += "upload/" + fileName + "、";
                File saveFile = new File(targetPath + fileName);
                item.write(saveFile);
            } else {
                paramsMap.put(item.getFieldName(), item.getString("utf-8"));
            }
        }
        int htid = Integer.parseInt(paramsMap.get("ht.htid"));
        String htname = paramsMap.get("ht.htname");
        Ht ht = new Ht();
        ht.setHtid(htid);
        ht.setHtname(htname);
        ht.setHtremark(himg);
        int i = htDao.update(ht);
        String jsonStr = new ObjectMapper().writeValueAsString(i);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jsonStr);
    }

}
