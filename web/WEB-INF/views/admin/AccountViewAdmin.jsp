<%-- 
    Document   : AccountView
    Created on : Feb 24, 2023, 11:16:03 AM
    Author     : Huynh Anh Kiet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin View Account</title>
    </head>
    <body>
        <%@include file="../jspf/adminheader.jspf" %>
        <table>
            <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:if test="${requestScope.accountList != null }">
                <c:forEach items="${requestScope.accountList}" var="account">
                    <tr>
                        <td>${account.name}</td>
                        <td>${account.phone}</td>
                        <td>${account.getStatusInString()}</td>
                        <td>
                            <form action="StatusSwitch" method="post">
                                <input type="hidden" name="id" value="${account.accID}">
                                <input type="hidden" name="status" value="${account.status}">
                                <input type="submit" value="${account.status == 1 ? "Block" : "Unblock"}">
                            </form> 
                        </td>  
                    </tr>
                </c:forEach>
            </c:if>
        </table>

    </body>
</html>
