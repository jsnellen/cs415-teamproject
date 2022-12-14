<%-- 
    Document   : updateUser
    Created on : Dec 13, 2022, 10:05:07 AM
    Author     : jayde
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.jsu.mcis.cs415.teamproject.dao.DAOUtility"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery-3.6.1.min.js"></script>
        <script type="text/javascript" src="scripts/script.js"></script>
    </head>
    <body>
        <form id="updateform" name="updateform" onsubmit="">
            <fieldset>
                <legend>Edit Account</legend>
                
                <p>
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username">
                </p>
                
                <p>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password">
                </p>
                
                <p>
                    <label for="description">Description:</label>
                    <input type="text" id="description" name="description">
                </p>
                
                <p>
                    <label for="timezone">Timezone:</label>
                    <%= DAOUtility.getTimeZonesListHTML() %>
                </p>
                
                <p>
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email">
                </p>
                
                <p>
                    <input type="submit" value="Edit Profile" tabindex="6">
                </p>
            </fieldset>
            
        </form>
        
        <p>
            <input type="button" value="Back To Home" onclick="window.open('<%= request.getContextPath() %>/main/index.jsp', '_self', false);" />
        </p>
        
        <script>
            updateHandler.getUserInfo();
        </script>
    </body>
</html>
