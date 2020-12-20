<%--
  Created by IntelliJ IDEA.
  User: bhy630
  Date: 2020/10/22
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="DeptServlet?type=toAdd">部门添加</a>
    <table>
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>状态</th>
            <th>备注</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${depts}" var="list">
            <tr>
            <td>${list.pid}</td>
            <td>${list.pname}</td>
            <td>${list.pflag}</td>
            <td>${list.premark}</td>
            <td>
                <a href="DeptServlet?type=toUpdate&pid=${list.pid}">修改</a>
                <a href="javascript:toDel('DeptServlet?type=delete&pid=${list.pid}')">删除</a>
            </td>
            </tr>
        </c:forEach>
    </table>
<script type="text/javascript">
    function toDel(url) {
        if (window.confirm("确定删除吗？")){
            window.location.href=url
        }
    }
</script>
</body>
</html>
