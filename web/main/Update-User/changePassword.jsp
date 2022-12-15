<%-- 
    Document   : changePassword
    Created on : Dec 14, 2022, 8:58:20 PM
    Author     : jayde
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery-3.6.1.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/scripts/userUpdate.js"></script>
    </head>
    <body>
        <form id="changepasswordform" name="changepasswordform" onsubmit="updateHandler.changeUserPassword()">
            <fieldset>
                <legend>Change Password</legend>
                <p>
                    <label for="password"></label>
                    <input type="password" id="password" name="password" placeholder="New Password" tabindex=2 required>
                </p>
                <p>
                    <label for="confirmPass"></label>
                    <input type="password" id="confirmPass" name="confirmPass" placeholder="Confirm New Password" tabindex=3 required onkeyup="updateHandler.validatePass(); return false;">
                </p>
                    <span id="invalidPass"></span>
                <p>
                    <input type="submit" value="Save" tabindex=4>
                </p>
                    <span id="confirmCreate"></span>
            </fieldset>
            
            <p>
            <input type="button" value="Back" onclick="window.open('updateUser.jsp', '_self', false);" />
        </p>
        </form>
    </body>
</html>
