
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<html>

    <head>

        <title>More Options</title>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="styles/main.css">

        <script type="text/javascript" src="scripts/jquery-3.6.1.min.js"></script>
        <script type="text/javascript" src="scripts/script.js"></script>

    </head>

    <body>

        <h1>More Options</h1>

        <p>
            <a href="#" onclick="UITest.onClickShow('dialog1')">Daily</a><br />
            <a href="#" onclick="UITest.onClickShow('dialog2')">Weekly</a><br />
            <a href="#" onclick="UITest.onClickShow('dialog3')">Monthly</a><br />
            <a href="#" onclick="UITest.onClickShow('dialog4')">Yearly</a><br />
        </p>

        <div id="overlay">

            <div id="dialog1" class="dialog">
                <h2>Daily Event</h2>
              
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
                    
              
                <div class="buttons">
                    <button onclick="UITest.onClickOK('dialog1')">OK</button>
                    <button onclick="UITest.onClickCancel('dialog1')">Cancel</button>
                </div>
            </div>

            <div id="dialog2" class="dialog">
                <h2>Weekly Event</h2>
              
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
                  <label for="everydate">Repeat every (this date)</label><br>
                  <input type="text" id="everydate" name="everydate"><br>
                  <label for="everydow">Repeat every (this specific day of the week)</label><br>
                  <input type="text" id="everydow" name="everydow"><br>
                </form>

                <div class="buttons">
                    <button onclick="UITest.onClickOK('dialog2')">OK</button>
                    <button onclick="UITest.onClickCancel('dialog2')">Cancel</button>
                </div>
            </div>

            <div id="dialog3" class="dialog">
                <h2>Monthly Event</h2>
              
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
                  <label for="everydate">Repeat every (this date)</label><br>
                  <input type="text" id="everydate" name="everydate"><br>
                  <label for="everydom">Repeat every (this specific day of the week)</label><br>
                  <input type="text" id="everydom" name="everydom"><br>
                </form>
                <div class="buttons">
                    <button onclick="UITest.onClickOK('dialog3')">OK</button>
                    <button onclick="UITest.onClickCancel('dialog3')">Cancel</button>
                </div>
            </div>

            <div id="dialog4" class="dialog">
                <h2>Yearly Event</h2>
                  
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
                  <label for="everydate">Repeat every (this date)</label><br>
                  <input type="text" id="everydate" name="everydate"><br>
                  <label for="everydoy">Repeat every (this specific day of the week)</label><br>
                  <input type="text" id="everydoy" name="everydoy"><br>
                </form>
    
                <div class="buttons">
                    <button onclick="UITest.onClickOK('dialog4')">OK</button>
                    <button onclick="UITest.onClickCancel('dialog4')">Cancel</button>
                </div>
            </div>

        </div>

    </div>

        <p>Click one of the links above to show the example dialog boxes.  The data selected in each dialog box will be shown below:</p>

        <div id="output"></div>

        <script type="text/javascript">
            $(UITest.jQueryTest());
        </script>

    </body>

</html>
