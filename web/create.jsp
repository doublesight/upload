<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Doublesight</title>
</head>
<body>
<center>
    <h1>Create campaign</h1>

    <form method="post" action="CreateServlet"
          enctype="multipart/form-data">
        Select file to upload: <input type="file" name="file" size="60"/>
        <br>
        <br/> <input type="submit" value="Create"/>
    </form>
</center>
</body>
</html>