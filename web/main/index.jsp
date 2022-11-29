<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    
    <head>
        
        <title>CS415 Team Project</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 

        <style><%@include file="/Style/dropdown.css"%></style>
        
    </head>
    
    <body>
        <p>Welcome to the CS 415 Team Project</p>
              
           <div class="dropdown" style="float:left;">
            <button class="dropbtn">Calendar Options</button>
            <div class="dropdown-content" style="left:0;">
              <a href="<%= request.getContextPath() %>/main/Create-Event/create_edit_event.jsp">Create/Edit Event Page</a>
              <a href="#">Delete Event</a>
              <a href="#">Create/Edit New Calendar</a>
              <a href="#">Delete Calendar(s)</a>
            </div>
          </div>
        
        <p>
            <input type="button" value="Logout" onclick="window.open('<%= request.getContextPath() %>/main/logout.jsp', '_self', false);" />
        </p>
        
       
        
    </body>
    
</html>