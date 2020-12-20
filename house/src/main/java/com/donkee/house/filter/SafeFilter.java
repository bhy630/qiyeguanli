package com.donkee.house.filter;

import com.donkee.house.entity.Emp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class SafeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) srequest;
        HttpServletResponse response = (HttpServletResponse) sresponse;
        String uri = request.getRequestURI();
        System.out.println("uri"+uri);
        Emp loginUser = (Emp) request.getSession().getAttribute("loginUser");
        if (loginUser!=null){
            chain.doFilter(request,response);
        }else if(uri.endsWith("EmpController") && request.getParameter("type").equals("login")){
            chain.doFilter(request,response);
        }else if(uri.startsWith("/images")|| uri.startsWith("/css") || uri.startsWith("/js")||uri.endsWith("index.html") ||uri.startsWith("/layer")){  //静态资源
            uri.endsWith("index.html");
            chain.doFilter(request,response);
        }else {
            response.sendRedirect("/index.html");
        }
    }

    @Override
    public void destroy() {

    }
}
