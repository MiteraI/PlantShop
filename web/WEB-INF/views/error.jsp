<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>Error Page</title>
</head>
<body>
  <h1>An error occurred:</h1>
  <pre>${requestScope.error}</pre>
</body>
</html>