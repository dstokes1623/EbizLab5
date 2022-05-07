<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Successful</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>    
    </head>
    <body>
        <h1>ABC Corporation Payroll System</h1>
        <h2>Welcome ${employee.firstName}</h2>
        <h3>Select an option</h3>
        <p>${message}</p>
        <form action="timecard" method="post">
            <label>Update Timecards</label>
            <input type="hidden" name="option" value="list">
            <input type="submit" value="Select">
        </form>
        <br>
        <form action="payroll" method="post">
            <label>Payroll</label>
            <input type="hidden" name="option" value="payroll">
            <input type="submit" value="Select">
        </form>
  
    <br>
    <c:if test="${userRole.updateUserRoles}">
        <form action="UserRole" method="post">
            <label>Update User Roles</label>
            <input type="hidden" name="option" value="users">
            <input type="submit" value="Select">
        </form>
    </c:if>
    </body>
</html>