<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    
    <head>
        
        <title>CS415 Team Project</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <link rel="stylesheet" type="text/css" href="dropdown.css">
        
    </head>
    
    <body>
        <p>Daily Event</p>
              
          <form>
            <input type="date" id="event_start" name="event_start"            
            value="2022-07-22"
            min="2018-01-01" max="2022-12-31"> Starting Date:<br>
            <input type="checkbox" id="0" name="0" value="Sunday">
            <label for="0">Sunday</label><br>
            <input type="checkbox" id="1" name="1" value="Monday">
            <label for="1">Monday</label><br>
            <input type="checkbox" id="2" name="2" value="Tuesday">
            <label for="2">Tuesday</label><br>
            <input type="checkbox" id="3" name="3" value="Wednesday">
            <label for="3">Wednesday</label><br>
            <input type="checkbox" id="4" name="4" value="Thursday">
            <label for="4">Thursday</label><br>
            <input type="checkbox" id="5" name="5" value="Friday">
            <label for="5">Friday</label><br>
            <input type="checkbox" id="6" name="6" value="Saturday">
            <label for="6">Saturday</label><br>
            <input type="date" id="event_stop" name="event_stop"            
            value="2022-07-22"
            min="2018-01-01" max="2022-12-31">Stopping Date:<br>

          </form>
 
        <p>
            <a href="create_edit_event.html">Less Options</a>
        </p>

        
        <p>
            <input type="button" value="Logout" onclick="window.open('<%= request.getContextPath() %>/main/logout.jsp', '_self', false);" />
        </p>
        
       
        
    </body>
    
</html>