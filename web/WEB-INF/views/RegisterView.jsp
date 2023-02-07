<%-- 
    Document   : RegisterView
    Created on : Feb 5, 2023, 6:02:08 PM
    Author     : Huynh Anh Kiet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <style>
            form {
                width: 400px;
                margin: 0 auto;
                text-align: center;
            }

            input[type="text"], input[type="password"], input[type="email"], input[type="tel"] {
                padding: 10px;
                margin-top: 10px;
                width: 100%;
            }

            input[type="submit"] {
                padding: 10px 20px;
                background-color: green;
                color: white;
                border: none;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: darkgreen;
            }

            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <form action="register" name="registerForm" method="post" onsubmit="return validateForm()">
            <h2>Register</h2>
            <input type="text" name="fullName" placeholder="Full Name" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="tel" name="phoneNumber" placeholder="Phone Number" required oninput="this.value = formatPhoneNumber(this.value)">
            <input type="submit" value="Submit">
            <%    
                if(request.getAttribute("registerStatus")!=null) {
                     boolean registerStatus = (boolean) request.getAttribute("registerStatus");
                        if (!registerStatus) {
                            out.print("Something is wrong on the back-end. Hold on for maintenance");
                        }
                }
            %>
        </form>


        <script>
            function formatPhoneNumber(phoneNumber) {
                phoneNumber = phoneNumber.replace(/[^\d]/g, "");
                if (phoneNumber.length >= 9) {
                    phoneNumber = phoneNumber.replace(/(\d{3})(\d{3})(\d{3})/, "$1-$2-$3");
                }
                return phoneNumber;
            }

            function validateForm() {
                var fullName = document.forms["registerForm"]["fullName"].value;
                var email = document.forms["registerForm"]["email"].value;
                var password = document.forms["registerForm"]["password"].value;
                var phoneNumber = document.forms["registerForm"]["phoneNumber"].value;

                if (fullName == "" || email == "" || password == "" || phoneNumber == "") {
                    alert("All fields are required.");
                    return false;
                }

                var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
                if (!emailRegex.test(email)) {
                    alert("Invalid email format.");
                    return false;
                }

                var phoneNumberRegex = /^\d{3}-\d{3}-\d{3}$/;
                if (!phoneNumberRegex.test(phoneNumber)) {
                    alert("Phone number should be in the format of xxx-xxx-xxx (9 digits)");
                    return false;
                }

                return true;
            }
        </script>

    </body>
</html>

