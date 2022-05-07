<%-- 
    Document   : updateUserRole
    Created on : May 6, 2022, 5:56:31 PM
    Author     : stoke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>Update Timecard Values</h1>
        <p><i>${message}</i></p>
        <form action="UserRole" method="post">
            <input type="hidden" name="option" value="save">
            <label class="pad_top">User Role</label>
            <input type="text" name="userType" value="${updateUserRole.userType}" >
            <label class="pad_top">Update User Roles</label>
            <input type="checkbox" name="updateUserRoles" value="true" >
            <label>Calculate Payroll</label>
            <input type="checkbox" name="calculatePayroll" value="true">
            <label>View Payroll</label>
            <input type="checkbox" name="viewPayroll" value="true">
            <label>View/Edit All Timecards</label>
            <input type="checkbox" name="allTimecards" value="true">
            <label>View/Edit Own Timecards</label>
            <input type="checkbox" name="ownTimecards" value="true">
            <br>
            <label>&nbsp;</label>
            <input type="submit" value="Save" class="margin_left">
        </form>
            <label>&nbsp;</label>
            <form action="userRoles.jsp">
            <input type="submit" value="Cancel">
        </form>
    </body>
</html>
