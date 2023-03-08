<%-- 
    Document   : LoginView
    Created on : Feb 21, 2023, 9:53:30 AM
    Author     : Huynh Anh Kiet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome ${sessionScope.loginedUser.name}</title>
    </head>
    <body>
        <a href="Home"><h1>Go to user's page</h1></a>
        <a href="AdminViewAccount"><h1>View all account</h1></a>
        <a href="AdminViewOrder"><h1>View all orders</h1></a>
        <a href="AdminViewPlant"><h1>View and update plants</h1></a>
        <a href="AdminViewCategory"><h1>View and update categories</h1></a>
    </body>
</html>
