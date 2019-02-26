<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Students</title>
</head>
<body>
<table>
    <tr>
        <th>Name</th>
        <th>City</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.name}</td>
            <td>${student.city.name}</td>
        </tr>
    </c:forEach>
</table>
<form method="post" action="/users">
    <input type="text" name="name">
    <input type="text" name="city">
    <input type="submit">
</form>
</body>
</html>