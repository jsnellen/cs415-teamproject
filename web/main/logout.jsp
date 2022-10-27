<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
    
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
        
    </head>
    
    <body>
        <%
            session.invalidate();
            response.sendRedirect(request.getContextPath());
        %>
    </body>
    
</html>