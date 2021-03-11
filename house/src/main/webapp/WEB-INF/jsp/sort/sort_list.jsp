<%--
  Created by IntelliJ IDEA.
  User: bhy630
  Date: 2020/10/22
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="SortServlet?type=toAdd">部门添加</a>
<table>
    <tr>
        <th></th>
        <th></th>
        <th></th>
    </tr>

    <c:forEach items="${sorts}" var="list">
        <tr>
            <td>${list.sid}</td>
            <td>${list.sname}</td>
            <td>
                <a href="SortServlet?type=toUpdate&sid=${list.sid}">修改</a>
                <a href="javascript:toDel('SortServlet?type=delete&sid=${list.sid}')">删除</a>
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
