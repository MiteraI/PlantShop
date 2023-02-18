<%-- 
    Document   : UserInfoView
    Created on : Feb 17, 2023, 9:05:28 AM
    Author     : Huynh Anh Kiet
--%>

<%@page import="workconstants.AccountConstants"%>
<%@page import="workconstants.ControllerConstants"%>
<%@page import="models.entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome <%Account account = (Account) session.getAttribute("loginedUser");
            out.print(account.getName());%></title>
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
        <h1>Hello <%=account.getName()%></h1>
        <h1>Your role is <%=account.getRole() == AccountConstants.USER ? "User" : "Admin"%></h1>
        <h1>Your phone number is <%=account.getPhone()%></h1>
        <br />
        <hr />
        <br />
        <form class="form" action="MainController?action=<%=ControllerConstants.CHANGEACCOUNT%>" method="post" onsubmit="return validateFormName()">
            <h2>Change your name</h2>
            <input type="text" id="name" name="<%=AccountConstants.CHANGENAME%>" placeholder="John">
            <input type="hidden" name="mode" value="<%=AccountConstants.CHANGENAME%>">
            <input type="submit" value="Commit" name="action">
            <p class="error" id="errorName"></p>
        </form>
        <form class="form" action="MainController?action=<%=ControllerConstants.CHANGEACCOUNT%>" method="post" onsubmit="return validateFormPhone()">
            <h2>Change your phone number</h2>
            <input type="text" id="phone" name="<%=AccountConstants.CHANGEPHONE%>" placeholder="333-333-333" oninput="this.value = formatPhoneNumber(this.value)">
            <input type="hidden" name="mode" value="<%=AccountConstants.CHANGEPHONE%>">
            <input type="submit" value="Commit" name="action">
            <p class="error" id="errorPhone"></p>
        </form>
        <form class="form" action="MainController?action=<%=ControllerConstants.CHANGEACCOUNT%>" method="post" onsubmit="return validateFormEmail()">
            <h2>Change your email</h2>
            <input type="text" id="email" name="<%=AccountConstants.CHANGEEMAIL%>" placeholder="abc@mail.com">
            <input type="hidden" name="mode" value="<%=AccountConstants.CHANGEEMAIL%>">
            <input type="submit" value="Commit" name="action">
            <p class="error" id="errorEmail"></p>
        </form>
        <form class="form" action="MainController?action=<%=ControllerConstants.CHANGEACCOUNT%>" method="post" onsubmit="return validateFormPassword()">
            <h2>Change your password</h2>
            <input type="text" id="password" name="<%=AccountConstants.CHANGEPASSWORD%>" placeholder="12345">
            <input type="hidden" name="mode" value="<%=AccountConstants.CHANGEPASSWORD%>">
            <input type="submit" value="Commit" name="action">
            <p class="error" id="errorPassword"></p>
        </form>
        <script>
            var errorName = document.getElementById("errorName");
            var errorPhone = document.getElementById("errorPhone");
            var errorPassword = document.getElementById("errorPassword");
            var errorEmail = document.getElementById("errorEmail");
            
            function formatPhoneNumber(phoneNumber) {
                phoneNumber = phoneNumber.replace(/[^\d]/g, "");
                if (phoneNumber.length >= 9) {
                    phoneNumber = phoneNumber.replace(/(\d{3})(\d{3})(\d{3})/, "$1-$2-$3");
                }
                return phoneNumber;
            }

            function validateFormEmail() {
                var email = document.getElementById("email").value;
                var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
                if (!emailPattern.test(email)) {
                    errorEmail.innerHTML = "Invalid email format";
                    return false;
                }
                return true;
            }
            function validateFormPassword() {
                var password = document.getElementById("password").value;
                if (!password) {
                    errorPassword.innerHTML = "Password cannot be empty";
                    return false;
                }
                return true;
            }
            function validateFormName() {
                var name = document.getElementById("name").value;
                if (!name) {
                    errorName.innerHTML = "Name cannot be empty";
                    return false;
                }
                return true;
            }
            function validateFormPhone() {
                var phoneNumber = document.getElementById("phone").value;
                var phoneNumberRegex = /^\d{3}-\d{3}-\d{3}$/;
                if (!phoneNumber) {
                    errorPhone.innerHTML = "Phone number cannot be empty";
                    return false;
                }
                if (!phoneNumberRegex.test(phoneNumber)) {
                    alert("Phone number should be in the format of xxx-xxx-xxx (9 digits)");
                    return false;
                }
                return true;
            }
        </script>
    </body>
</html>
