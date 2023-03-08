<%-- 
    Document   : LoginView
    Created on : Feb 5, 2023, 6:02:22 PM
    Author     : Huynh Anh Kiet
--%>

<%@page import="workconstants.ControllerConstants"%>
<%@ taglib prefix="c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login Page</title>
        <link href="resources/css/index.css" rel="stylesheet" type="text/css"/>

        <style>
            .form-container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            .form {
                background-color: #f2f2f2;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px #ccc;
            }

            .form input[type="text"], .form input[type="password"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 20px;
                border: none;
                border-radius: 5px;
            }

            .form input[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .form input[type="submit"]:hover {
                background-color: #3e8e41;
            }

            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <form class="form" action="MainController" method="post" onsubmit="return validateForm()">
                <h2>Login</h2>
                <input type="text" id="email" name="email" placeholder="Email">
                <input type="password" id="password" name="password" placeholder="Password">
                <input type="submit" value="<%=ControllerConstants.LOGIN%>" name="action">
                <p class="error" id="error"></p>
                <%
                    if (request.getAttribute("loginStatus") != null) {
                        boolean loginStatus = (boolean) request.getAttribute("loginStatus");
                        if (!loginStatus) {
                            out.print("Account not found. Wrong password or email.</br>");
                            out.print("Not registed? <a href='register'>Click here!</a>");
                        }
                    }
                %>
            </form>
        </div>
        <script>
            function validateForm() {
                var email = document.getElementById("email").value;
                var password = document.getElementById("password").value;
                var error = document.getElementById("error");
                var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
                if (!emailPattern.test(email)) {
                    error.innerHTML = "Invalid email format";
                    return false;
                }
                if (!password) {
                    error.innerHTML = "Password cannot be empty";
                    return false;
                }
                return true;
            }
            <c:if test="${requestScope.loginedUser == false}">
            alert("You have to login first!")
            </c:if>
            <c:if test="${requestScope.isBlocked == true}">
            alert("Your account is blocked!")
            </c:if>
        </script>
    </body>
</html>
