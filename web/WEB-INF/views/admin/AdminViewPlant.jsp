<%-- 
    Document   : AdminViewPlant
    Created on : Mar 7, 2023, 2:14:11 PM
    Author     : Huynh Anh Kiet
--%>

<%@page import="workconstants.PlantConstants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant View</title>
    </head>
    <body>
        <%@include file="../jspf/adminheader.jspf" %>
        <c:forEach items="${requestScope.cateList}" var="cate">
            <h2>${cate.name}</h2>
            <hr/>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Change status</th>
                    <th>Delete</th>
                </tr>
                <c:forEach items="${requestScope.plantList}" var="plant">
                    <c:if test="${cate.id==plant.getCate().id}">
                        <tr>
                            <td>${plant.id}</td>
                            <td>${plant.name}</td>
                            <td> 
                                <form action="PlantAction" method="post">
                                    <input type="hidden" name="id" value="${plant.id}">
                                    <input type="hidden" name="action" value="<%=PlantConstants.PRICE%>">
                                    <input type="number" name="price" min="1" max="100" value="${plant.price}" required>
                                    <input type="submit" value="Change price">
                                </form>
                            </td>
                            <!-- Will have to implement a form in here for price change -->

                            <td>${plant.description}</td>
                            <td>${plant.getStatusInString()}</td>
                            <td>
                                <form action="PlantAction" method="post">
                                    <input type="hidden" name="id" value="${plant.id}">
                                    <input type="hidden" name="status" value="${plant.status}">
                                    <input type="hidden" name="action" value="<%=PlantConstants.CHANGE%>">
                                    <input type="submit" value="${plant.status == 1 ? "Out" : "In"}">
                                </form> 
                            </td>  
                            <td>
                                <form action="PlantAction" method="post">
                                    <input type="hidden" name="id" value="${plant.id}">
                                    <input type="hidden" name="action" value="<%=PlantConstants.DELETE%>">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
            <form action="PlantAction" method="post">
                <input type="hidden" name="action" value="<%=PlantConstants.CREATE%>">
                <input type="hidden" name="id" value="${cate.id}">

                <label>Plant Name:</label>
                <input type="text" id="plantName" name="PName" required>

                <label>Price:</label>
                <input type="number" step="0.01" id="price" name="price" required>

                <label>Description (limited to 20 characters):</label>
                <input type="text" maxlength="20" id="description" name="description" required>

                <button type="submit">New plant</button>
            </form>
            <hr/>
        </c:forEach>
    </body>
</html>
