<%@page import="java.util.ArrayList"%>
<%@page import="models.entities.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <%
                if (request.getAttribute("query") != null) {
                    String searchedProduct = request.getAttribute("query").toString();
                    String nameCapitalized = "";
                    if (!searchedProduct.isEmpty() || !searchedProduct.isBlank()) {
                        String upperCase = searchedProduct.substring(0, 1).toUpperCase();
                        nameCapitalized = upperCase + searchedProduct.substring(1);
                    }
                    out.print(nameCapitalized + " Good Price Products");
                } else {
                    out.print("Good Price Products");
                }
            %>
        </title>
        <link rel="stylesheet" href="./resources/css/index.css" />

    </head>
    <body>
        <%@include file="jspf/header.jspf"%>
        <div class="flex flex-wrap justify-center">
            <%                ArrayList<Plant> list = (ArrayList<Plant>) request.getAttribute("list");
                if (list.size() > 0) {
                    for (Plant plant : list) {%>
            <!--                        out.println("<div>"
                                            + "<img src='resources/img/" + plant.getImgPath() + "' alt='" + plant.getDescription() + "' width='100' height='100'>"
                                            + "<h3>" + plant.getName() + "</h3>"
                                            + "<h2>" + plant.getPrice() + "</h2>"
                                            + "<a href='MainController?action=" + Constants.VIEW + "&id=" + plant.getId() + "'><button>View plant</button></a>"
                                            //                                + "<a href='MainController?action=" + Constants.ADDTOCART + "&id=" + plant.getId() + "'><button id='buyButton'>Buy plant</button></a>"
                                            + "</div>");-->
            <div class="flex-col justify-center">
                <img
                    class="w-48 h-48"
                    src="./resources/img/<%=plant.getImgPath()%>"
                    alt="<%=plant.getDescription()%>"
                    />
                <h3 class="text-center"><%=plant.getName()%></h3>
                <h2 class="text-center"><%=plant.getPrice()%></h2>
                <div class="text-center">
                    <a><button>View plant</button></a>
                </div>
            </div>
            <%}
                } else {
                    out.println("No plant was found");
                }
            %>
        </div>
        <%@include file="jspf/footer.jspf"%>
        <!--        <script>
                    const queryString = window.location.search;
                    const urlParams = new URLSearchParams(queryString);
                    const lastSearch = urlParams.get('q');
                    document.getElementById("search-input").value = lastSearch;
                    document.title = lastSearch + ' Good Price Products';
                    console.log(lastSearch);
                </script>-->
    </body>
</html>
