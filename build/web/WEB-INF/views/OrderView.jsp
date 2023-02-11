<%-- 
    Document   : OrderView
    Created on : Feb 11, 2023, 11:27:40 PM
    Author     : Huynh Anh Kiet
--%>

<%@page import="models.entities.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Order</title>
    </head>
    <body>
        <form action="" method="get">
            <label for="dateFilter">Filter by Order Date:</label>
            <input type="date" id="dateFilter" name="dateFilter">
            <input type="submit" value="Filter">
        </form>
        <%                ArrayList<OrderDetail> orderList = (ArrayList<OrderDetail>) request.getAttribute("orderList");
            if (orderList.size() > 0) {
                for (OrderDetail order : orderList) {
                    out.println("<div>"
                            + "<img src='resources/img/" + order.getImgPath() + "' width='100' height='100'>"
                            + "<h3>" + order.getPlantName() + "</h3>"
                            + "<h2>" + order.getPrice() + "</h2>"
                            + "</div>");
                    out.println(order.getOrderDate());
                    out.println(order.getShippingDate());
                }
            } else {
                out.println("Empty order list");
            }
        %>
    </body>
</html>
