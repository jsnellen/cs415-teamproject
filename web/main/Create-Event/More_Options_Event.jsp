<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    
    <head>
        
        <title>CS415 Team Project</title>
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
        <!--<link rel="stylesheet" type="text/css" href="dropdown.css">-->
        
    </head>
    
    <body>
        <p>create/edit event</p>

          <form>
            <label for="eventname">Name of the Event:</label><br>
            <input type="text" id="eventname" name="eventname"><br>
            <label for="description">Description for Event:</label><br>
            <input type="text" id="description" name="description"><br>
            <input type="date" id="event_start" name="event_start"            
            value="2022-07-22"
            min="2018-01-01" max="2022-12-31"> Starting Date:<br>
            <input type="date" id="event_stop" name="event_stop"            
            value="2022-07-22"
            min="2018-01-01" max="2022-12-31">Stopping Date:<br>
            <label for="hour">Starting time:</label><br>
            <input type="time" id="hour" name="hour"
            min="00:00" max="23:59"><br>
            <label for="minute">Stopping Time :</label><br>
            <input type="time" id="stop_time" name="stop_time"
            min="00:00" max="23:59"><br>
            <label for="reccuring">Repeating Event?:</label><br>
            <select name="reccuring" id="reccuring">
            <option value="norepeat">Don't Repeat</option>
            <option value="repeat">Repeat</option><br>

            <!--type="hidden"-->
            <input type="submit" value="Submit">
          </form>

          <!--onclick="myFunction()"
        <div id="myDropdown" class="dropdown-content">
        -->

        <p>
          <div class="dropdown" style="float:left;">
            <button class="dropbtn">How often do you want your event to repeat</button>
            <div class="dropdown-content" style="left:0;"></div>
              <a href="<%= request.getContextPath() %>/main/Create-Event/Recurring_Events.jsp">Repeating Events</a>
            </div>
          </div>
        </p>
        <p>
            <input type="button" value="Less Options" style="float:inline-end;" onclick="window.open('<%= request.getContextPath() %>/main/#.jsp', '_self', false);" />
        </p>
        
        <p>
            <input type="button" value="Logout" style="float:inline-end;" onclick="window.open('<%= request.getContextPath() %>/main/logout.jsp', '_self', false);" />
        </p>
        
       
        
    </body>
    
</html>