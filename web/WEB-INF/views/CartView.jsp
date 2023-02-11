<%-- 
    Document   : CartView
    Created on : Feb 11, 2023, 6:19:30 PM
    Author     : Huynh Anh Kiet
--%>

<%@page import="controller.Constants"%>
<%@page import="java.util.HashMap"%>
<%@page import="models.entities.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <div style="display:flex">
            <%@include file="jspf/header.jspf"%>
            <%
                HashMap<Plant, Integer> cart = (HashMap) request.getAttribute("cart");
                if (!cart.isEmpty() && cart != null) { //Not empty and not null
                    for (Plant plant : cart.keySet()) {
                        out.println("<div>"
                                + "<img src='resources/img/" + plant.getImgPath() + "' alt='" + plant.getDescription() + "' width='100' height='100'>"
                                + "<h3>" + plant.getName() + "</h3>"
                                + "<h2>" + plant.getPrice() + "</h2>"
                                + "<h2>Number of plant: " + cart.get(plant) + "</h2>"
                                + "<a href='MainController?action=" + Constants.VIEW + "&id=" + plant.getId() + "'><button>View plant</button></a>"
                                //                                + "<a href='MainController?action=" + Constants.ADDTOCART + "&id=" + plant.getId() + "'><button id='buyButton'>Buy plant</button></a>"
                                + "</div>");       
                    }
                    out.print("");
                } else {
                    out.println("No plant was found");
                }
            %>
            <form action="<%=Constants.PURCHASE%>" method="post">
                <input type="submit" value="Purchase">
            </form>
        </div>
    </body>
</html>
