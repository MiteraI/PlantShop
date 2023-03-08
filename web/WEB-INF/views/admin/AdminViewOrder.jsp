<%@page import="workconstants.OrderConstants"%>
<%@page import="models.entities.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sessionScope.loginedUser.getName()}'s Orders</title>
        <link href="resources/css/index.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/order.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <%@include file="../jspf/header.jspf"%>
        <div class="flex-col m-10">
            <form action="" method="get">
                <label for="dateFilter">Filter by Order Date:</label>
                <input type="date" id="dateFilter" name="dateFilter">
                <input type="submit" value="Filter">
            </form>
            <br />
            <h1>PENDING ORDERS</h1>
            <hr />
            <%
                ArrayList<OrderDetail> pendingOrderList = (ArrayList<OrderDetail>) request.getAttribute("pendingOrderList");
                if (pendingOrderList.size() > 0) {
                    for (OrderDetail order : pendingOrderList) {%>
            <hr />
            <div class="flex justify-between m-4">
                <div class="flex space-x-6">
                    <img class="w-20 h-20" src="./resources/img/<%=order.getPlant().getImgPath()%>" alt="" />
                    <div class="flex-col">
                        <h1><%=order.getPlant().getName()%></h1>
                        <h1>Number of product: <%=order.getQuantity()%></h1>
                    </div>
                </div>
                <div>
                    <h1>User's ID: <%=order.getuOrder().getAccID()%></h1>
                    <h1>Order date: <%=order.getuOrder().getOrderDate()%></h1>
                    <h1>Shipping date: <%=order.getuOrder().getShipDate()%></h1>
                </div>
                <div class="flex-col space-y-4">
                    <h1 class="text-right">Price: <%=order.getPrice()%>$</h1>
                    <h1 class="text-right">Total: <%=order.getPrice() * order.getQuantity()%>$</h1>
                    <div>
                        <form method="post" action="OrderAction">
                            <input type="hidden" name="action" value="<%=OrderConstants.CANCEL%>">
                            <input type="hidden" name="id" value="<%=order.getuOrder().getuOrderID()%>">
                            <input type="hidden" name="pid" value="<%=order.getPlant().getId()%>">
                            <button type="submit">Cancel order</button>
                        </form>
                        <form method="post" action="OrderAction">
                            <input type="hidden" name="action" value="<%=OrderConstants.COMPLETE%>">
                            <input type="hidden" name="id" value="<%=order.getuOrder().getuOrderID()%>">
                            <input type="hidden" name="pid" value="<%=order.getPlant().getId()%>">
                            <button type="submit">Complete order</button>
                        </form>
                    </div>
                </div>
            </div>
            <hr />
            <% }
                } else {
                    out.println("Empty order list");
                }
            %>
            <hr />
            <br />    
            <h1>SUCCESSFUL ORDERS</h1>
            <hr />
            <%
                ArrayList<OrderDetail> completedOrderList = (ArrayList<OrderDetail>) request.getAttribute("completedOrderList");
                if (completedOrderList.size() > 0) {
                    for (OrderDetail order : completedOrderList) {%>
            <hr />
            <div class="flex justify-between m-4">
                <div class="flex space-x-6">
                    <img class="w-20 h-20" src="./resources/img/<%=order.getPlant().getImgPath()%>" alt="" />
                    <div class="flex-col">
                        <h1><%=order.getPlant().getName()%></h1>
                        <h1>Number of product: <%=order.getQuantity()%></h1>
                    </div>
                </div>
                <div>
                    <h1>User's ID: <%=order.getuOrder().getAccID()%></h1>
                    <h1>Order date: <%=order.getuOrder().getOrderDate()%></h1>
                    <h1>Shipping date: <%=order.getuOrder().getShipDate()%></h1>
                </div>
                <div class="flex-col space-y-4">
                    <h1 class="text-right">Price: <%=order.getPrice()%>$</h1>
                    <h1 class="text-right">Total: <%=order.getPrice() * order.getQuantity()%>$</h1>
                    <div>
                        <a href="ViewOrderDetail?id=<%=order.getOrderDetailID()%>">View details</a>
                    </div>
                </div>
            </div>
            <hr />
            <% }
                } else {
                    out.println("Empty order list");
                }
            %>
            <hr />
            <br />
            <h1>CANCELED ORDERS</h1>
            <hr />
            <%
                ArrayList<OrderDetail> canceledOrderList = (ArrayList<OrderDetail>) request.getAttribute("canceledOrderList");
                if (canceledOrderList.size() > 0) {
                    for (OrderDetail order : canceledOrderList) {
            %>

            <hr />
            <div class="flex justify-between m-4">
                <div class="flex space-x-6">
                    <img class="w-20 h-20" src="./resources/img/<%=order.getPlant().getImgPath()%>" alt="" />
                    <div class="flex-col">
                        <h1><%=order.getPlant().getName()%></h1>
                        <h1>Number of product: <%=order.getQuantity()%></h1>
                    </div>
                </div>
                <div>
                    <h1>User's ID: <%=order.getuOrder().getAccID()%></h1>
                    <h1>Order date: <%=order.getuOrder().getOrderDate()%></h1>
                    <h1>Shipping date: <%=order.getuOrder().getShipDate()%></h1>
                </div>
                <div class="flex-col space-y-4">
                    <h1 class="text-right">Price: <%=order.getPrice()%>$</h1>
                    <h1 class="text-right">Total: <%=order.getPrice() * order.getQuantity()%>$</h1>
                    <div>
                        <form method="post" action="OrderAction">
                            <input type="hidden" name="action" value="<%=OrderConstants.REORDER%>">
                            <input type="hidden" name="id" value="<%=order.getuOrder().getuOrderID()%>">
                            <input type="hidden" name="pid" value="<%=order.getPlant().getId()%>">
                            <button type="submit">Re-order</button>
                        </form>
                        <a href="ViewOrderDetail?id=<%=order.getOrderDetailID()%>">View details</a>
                    </div>
                </div>
            </div>
            <hr />
            <% }
                } else {
                    out.println("Empty order list");
                }
            %>
            <hr />
        </div>
    </body>
</html>
