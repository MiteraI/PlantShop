<%-- 
    Document   : OrderDetailView
    Created on : Feb 24, 2023, 10:07:43 AM
    Author     : Huynh Anh Kiet
--%>

<%@page import="models.entities.OrderDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail</title>
        <link href="resources/css/index.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/order.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="jspf/header.jspf"%>
        <%if (request.getAttribute("details") != null) {
                OrderDetail details = (OrderDetail) request.getAttribute("details");
        %>
        <hr />
        <div class="flex justify-between m-4">
            <div class="flex space-x-6">
                <img class="w-20 h-20" src="./resources/img/<%=details.getPlant().getImgPath()%>" alt="" />
                <div class="flex-col">
                    <h1><%=details.getPlant().getName()%></h1>
                    <h1>Number of product: <%=details.getQuantity()%></h1>
                </div>
            </div>
            <div class="flex-col space-y-4">
                <h1 class="text-right">Price: <%=details.getPrice()%>$</h1>
                <h1 class="text-right">Total: <%=details.getPrice() * details.getQuantity()%>$</h1>
            </div>
            <div class="flex-col space-y-4">
                <h1 class="text-right">Order date: <%=details.getuOrder().getOrderDate()%></h1>
                <h1 class="text-right">Shipping date: <%=details.getuOrder().getShipDate()%></h1>
            </div>
        </div >
            <div class="flex justify-between m-4">Status: <%=details.getuOrder().getStatusInString()%></div>
        <hr />
        <%    } else
                out.print("No details with this Id exists!");
        %>
    </body>
</html>
