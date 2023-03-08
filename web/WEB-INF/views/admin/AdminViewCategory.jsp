<%-- 
    Document   : AdminViewCategory
    Created on : Mar 7, 2023, 1:22:41 PM
    Author     : Huynh Anh Kiet
--%>

<%@page import="workconstants.CategoryConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category View</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Category's ID</th>
                <th>Name</th>
                <th>Change name</th>
                <th>Delete</th>
            </tr>
            <c:if test="${requestScope.cateList != null}">
                <c:forEach items="${requestScope.cateList}" var="cate">
                    <tr>
                        <td>${cate.id}</td>
                        <td>${cate.name}</td>
                        <td>
                            <form id="nameChangeForm" action="CategoryAction" method="post">
                                <input type="hidden" name="id" value="${cate.id}">
                                <input type="hidden" name="action" value="<%=CategoryConstants.CHANGE%>">
                                <input type="text" required name="name" placeholder="Fruit">
                                <input type="submit" value="Change name">
                            </form>
                        </td>
                        <td>
                            <form id="nameChangeForm" action="CategoryAction" method="post">
                                <input type="hidden" name="id" value="${cate.id}">
                                <input type="hidden" name="action" value="<%=CategoryConstants.DELETE%>">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
        <form action="CategoryAction?action=<%=CategoryConstants.CREATE%>" method="post">
            <input type="text" required name="name" placeholder="Fruit">
            <input type="submit" value="Create">
        </form>
    </body>
</html>
