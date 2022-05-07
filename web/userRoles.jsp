<%-- 
    Document   : userRoles
    Created on : May 5, 2022, 9:48:41 PM
    Author     : stoke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User Roles</h1>

        <table>
            <tr>
                <th>User Role</th>
                <th>Update User Roles</th>
                <th>Calculate Payroll</th>
                <th>View Payroll</th>
                <th>View/Edit All Timecards</th>
                <th>View/Edit Own Timecards</th>
            </tr>

            <c:forEach var="ur" items="${userRoles}">
                <tr>
                    <td><c:out value='${ur.userType}'/></td>
                    <td class="right"><c:out value='${ur.updateUserRoles}'/></td>
                    <td class="right"><c:out value='${ur.calculatePayroll}'/></td>
                    <td class="right"><c:out value='${ur.viewPayroll}'/></td>
                    <td class="right"><c:out value='${ur.allTimecards}'/></td>
                    <td class="right"><c:out value='${ur.ownTimecards}'/></td>
                    <td>
                        <form action="UserRole" method="post">
                            <input type="hidden" name="option" value="update">
                            <input type="hidden" name="userTypeID" value="${ur.userTypeID}">
                            <input type="submit" value="Update">
                        </form>
                    </td>
                    <td>
                        <form action="UserRole" method="post">
                            <input type="hidden" name="option" value="delete">
                            <input type="hidden" name="userTypeID" value="${ur.userTypeID}">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
  
        <form action="UserRole" method="post">
            <input type="hidden" name="option" value="add">
            <input type="submit" value="Add User Role">
        </form>

        <form action="main.jsp" method="post">
            <input type="hidden" name="option" value="return">
            <input type="submit" value="Return to Main Menu">
        </form>
    </body>
</html>
