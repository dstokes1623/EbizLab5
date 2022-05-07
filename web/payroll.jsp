<%-- 
    Document   : payroll
    Created on : Apr 20, 2022, 8:14:18 PM
    Author     : stoke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payroll</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>    
    </head>

    <body>
        <h1>Calculate Payroll</h1>
        <p><i>${message}</i></p>
        <c:if test="${userRole.calculatePayroll}">
                <form action="payroll" metod="post">
                    <input type="hidden" name="option" value="list">
                    <label class="pad_top">Date</label>
                    <input type="text" name="date" required>
                    <br>
                    <label>&nbsp;</label>
                    <input type="submit" value="Calculate Payroll" class="margin_left">
                </form>
            </c:if>
        <br>
            <form action="payroll" metod="post">
                <input type="hidden" name="option" value="view">
                <label>Enter Employee ID<label/>
                <input type="text" name="empID" required>
                <input type="submit" value="View Payroll" class="margin_left">
            </form> 
       
    </body>
</html>
