
<%--
  Created by IntelliJ IDEA.
  User: bhy630
  Date: 2020/10/23
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="DeptServlet" method="post">
        <input type="hidden" name="type" value="update">
        <table>
            <tr>
                <td>编号：</td>
                <td><input type="text" name="id" value="${dept.pid}" readonly="readonly"/></td>
            </tr>
            <tr>
                <td>名称：</td>
                <td><input type="text" name="name" value="${dept.pname}"/></td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><textarea  rows="2" cols="30" name="remark" value="${dept.premark}"></textarea> </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="保存"/></td>
            </tr>
        </table>
    </form>
</body>
</html>
