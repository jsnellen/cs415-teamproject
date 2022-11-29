<%@page import="edu.jsu.mcis.cs415.teamproject.dao.DAOUtility"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

    <head>

        <title>Create Account</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery-3.6.1.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath() %>/scripts/userCreate.js"></script>


    </head>

    <body>

        <form id="createUserform" name="createUserform" onsubmit="return userCreate.createUserSubmit()">
            
            <fieldset>
                <legend>Create Account</legend>

                <p>
                    <input id="username" name="username" type="text" placeholder="Username" tabindex=1 required>
                    
                </p>
                <p>
                    <input id="password" name="password" type="password" placeholder="Password" tabindex=2 required/>
                    <div id="emptyPass"></div>

                </p>
                <p>    
                    <input id="confirmPass" name="confirmPass" type="password" placeholder="Confirm Password" tabindex=3 required/>
                <div id="invalidPass"></div>
                </p>
                <p>    
                    <input id="email" name="email" type="email" placeholder="Email" tabindex=4 required/>
                    <div id="emptyEmail"></div>

                </p>
                <p>
                    <input id="description" name="description" type="text" placeholder="Description" tabindex=5 required/>
                    <div id="emptyDesc"></div>

                </p>
                <p>    
                    <label for="timezone">Select your timezone: </label>
                    <%= DAOUtility.getTimeZonesListHTML() %>
                </p>
                <div id="emptyZone"></div>


                <p>
                    <input type="submit" value="Create Account" tabindex=6 />
                </p>
                <div id="confirmCreate"></div>

            </fieldset>

        </form>
                
        <div id="output"></div>

        <script type="text/javascript">
        </script>
    </body>

</html>