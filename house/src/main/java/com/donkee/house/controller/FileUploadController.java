package com.donkee.house.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUploadController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String path = this.getServletConfig().getServletContext().getRealPath("upload") + "/";   //得到web应用路径在服务器实际位置
        Map<String,String> paramsMap = new HashMap<>();

        try {
            List<FileItem> items = upload.parseRequest(request);  //解析请求，将所有数据封装到FileItem对象
            for (FileItem fileItem : items){
                if (fileItem.isFormField()){  //isFormField:是否是普通表单元素
                    String fileName = fileItem.getFieldName();  //getFieldName读取当前项的名称
                    paramsMap.put(fileName,fileItem.getString());  //getString:读取当前项的值
                    System.out.println(fileName+":"+fileItem.getString());
                }else { //文件表单域
                    String fileName = path + fileItem.getName();
                    System.out.println(fileName);
                    FileOutputStream fos = new FileOutputStream(fileName);
                    InputStream is = fileItem.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    //is.read(buffer):读取数据到buffer字节数组，返回实际读取的长度
                    while ((len = is.read(buffer))!=-1){
                        fos.write(buffer,0,len);//向输出流写入buffer[0,len]数据
                    }
                    fos.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        String title = paramsMap.get("title");
        String auth = paramsMap.get("auth");
        System.out.println(title+":"+auth);
    }
}
