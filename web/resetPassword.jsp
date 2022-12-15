<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery-3.6.1.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/scripts/userCreate.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/scripts/userUpdate.js"></script>
        <title>Reset Password</title>
    </head>
    <body>
        <form id="resetPassword" name="resetPassword" onsubmit="updateHandler.resetUserPassword()">
            <fieldset>
                <legend>Reset Password</legend>
                <p>
                    <input id="remoteuser" name="remoteuser" type="text" placeholder="Enter Username" tabindex=1 required>
                </p>
                <p>
                    <input id="password" name="password" type="password" placeholder="Enter New Password" tabindex=2 required/>
                </p>
                <span id="passLen"></span>
                <p>    
                    <input id="confirmPass" name="confirmPass" type="password" placeholder="Confirm New Password" tabindex=3 required onkeyup="userCreate.validatePass(); return false;"/>
                </p>
                    <span id="invalidPass"></span>
                <p>
                    <input type="submit" value="Reset Password" tabindex=4 />
                </p>
                <span id="confirmCreate"></span>
            </fieldset>
        </form>
        <p id="loginRedirect">
            Remember Password? <a href="login.jsp">Log in</a>
        </p>
        
        <p>
            <a href="login.jsp">Back To Login</a>
        </p>
    </body>
</html>
