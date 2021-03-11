<%--
  Created by IntelliJ IDEA.
  User: bhy630
  Date: 2020/10/29
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
    <script src="js/jquery-1.8.3.js"></script>
    <script src="layer/layer.js"></script>
    <SCRIPT type="text/javascript">        //////////////权限控制！！！//////////////		//var $userLevel=parseInt("");//获取登陆者的权限等级		var $webName="/pro";		//$(function(){		//	var tools=$(".tools");		//	if($userLevel!=-1){		//		tools.hide();		//	}		//});</SCRIPT>
</head>
<body>        <!-- 位置信息 -->
<div class="place"><span>位置：</span>
    <ul class="placeul">
        <li><a href="main/right.html">首页</a></li>
        <li><a href="part/all.html">部门管理</a></li>
        <li><a href="part/all.html">基本内容</a></li>
    </ul>
</div>        <!-- 内容表格信息 -->
<div class="rightinfo">
    <div class="tools">
        <ul class="toolbar">
            <li class="addPart"><span><img src="images/t01.png"/> </span>添加</li>
        </ul>
        <ul class="toolbar">
            <li class="delPart"><span><img src="images/t01.png"/> </span>已经撤销部门</li>
        </ul>
        <ul class="toolbar">
            <li class="backPart" style="display: none;"><span><img src="images/t01.png"/> </span>查询部门</li>
        </ul>
    </div>
    <table class="tablelist">
    <thead><tr align='center'><th width='10%' align='center'>编号</th><th width='25%' align='center'>部门名称</th><th width='35%' align='center'>部门备注</th><th width='15%' align='center'>已撤销部门</th><th width='15%' align='center'>操作</th></tr></thead>
    <c:forEach items="${depts }" var="list">
        <tr>
            <td>${list.pid }</td>
            <td>${list.pname }</td>
            <td>${list.pflag }</td>
            <td>${list.premark}</td>
            <td><a href="DeptServlet?type=toUpate&pid=${list.pid }">修改</a>
                <a href="javascript:toDelete('DeptServlet?type=delete&pid=${list.pid}')">删除</a></td>
        </tr>
    </c:forEach>
    </table>
    <div class="pagin">
        <div class="message"></div>
    </div>
</div>
</body>
</html>