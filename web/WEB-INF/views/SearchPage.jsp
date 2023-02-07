<%@page import="java.util.ArrayList"%>
<%@page import="models.entities.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
    </head>
    <body>
        <%@include file="jspf/header.jspf"%>
        <div style="display:flex">
            <%
                ArrayList<Plant> list = (ArrayList<Plant>) request.getAttribute("list");
                if (list.size() > 0) {
                    for (Plant plant : list) {
                        out.println("<div>"
                                + "<img src='resources/img/" + plant.getImgPath() + "' alt='" + plant.getDescription() + "' width='100' height='100'>"
                                + "<h3>" + plant.getName() + "</h3>"
                                + "<h2>" + plant.getPrice() + "</h2>"
                                + "</div>");
                    }
                } else {
                    out.println("No plant was found");
                }
            %>
        </div>
        <%@include file="jspf/footer.jspf"%>
        <script>
            const queryString = window.location.search;
            const urlParams = new URLSearchParams(queryString);
            const lastSearch = urlParams.get('q');
            document.getElementById("search-input").value = lastSearch;
            document.title = lastSearch + ' Good Price Products';
            console.log(lastSearch);
        </script>
    </body>
</html>
