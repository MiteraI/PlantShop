<%-- 
    Document   : PlantsShowcase
    Created on : Feb 5, 2023, 10:26:06 AM
    Author     : Huynh Anh Kiet
--%>

<%@page import="workconstants.ControllerConstants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.entities.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
        <link href="resources/css/index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="jspf/header.jspf"%>
        <div class="flex flex-wrap justify-center">
            <%
                ArrayList<Plant> list = (ArrayList<Plant>) request.getAttribute("list");
                if (list.size() > 0) {
                    for (Plant plant : list) {
            %>
            <div class="flex-col justify-center">
                <img
                    class="w-48 h-48"
                    src="./resources/img/<%=plant.getImgPath()%>"
                    alt="<%=plant.getDescription()%>"
                    />
                <h3 class="text-center"><%=plant.getName()%></h3>
                <h2 class="text-center"><%=plant.getPrice()%></h2>
                <div class="text-center">
                    <a href='MainController?action=<%=ControllerConstants.VIEW%>&id=<%=plant.getId()%>'><button class="border-2 bg-slate-200 rounded-lg px-4 py-1">View plant</button></a>                
                </div>
            </div>
            <%}
                } else {
                    out.println("No plant was found");
                }
            %>          
        </div>      
        <%@include file="jspf/footer.jspf"%>
        <script>

        </script>
    </body>
</html>
