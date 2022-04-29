<%-- 
    Document   : payrollList
    Created on : Apr 20, 2022, 8:14:36 PM
    Author     : stoke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Payroll</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>

        <h1>Payroll</h1>

        <table>
            <tr>
                <th>Date</th>
                <th>Employee ID</th>
                <th>Gross Pay</th>
                <th>Total Deductions</th>
                <th>Net Pay</th>
            </tr>

            <c:forEach var="pay" items="${payroll}">
                <tr>
                    <td><c:out value='${pay.dateFormatted}'/></td>
                    <td class="right"><c:out value='${pay.employeeID}'/></td>
                    <td class="right"><c:out value='${pay.grossPayFormatted}'/></td>
                    <td class="right"><c:out value='${pay.totalDeductionsFormatted}'/></td>
                    <td class="right"><c:out value='${pay.netPayFormatted}'/></td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
