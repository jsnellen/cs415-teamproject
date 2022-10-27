<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <title>Login Page</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>

    <body>

        <form id="loginform" name="loginform" method="POST" action="j_security_check">

            <fieldset>

                <p>
                    <label for="j_username">Username:</label>
                    <input id="j_username" name="j_username" type="text" tabindex=1 />

                    <label for="j_password">Password:</label>
                    <input id="j_password" name="j_password" type="password" tabindex=2 />                    
                </p>

                <p>
                    <input type="submit" value="Log In" tabindex=3 />
                </p>

            </fieldset>

        </form>

        <div id="login_instructions">

            <h2>How to Log In:</h2>
            <p>Enter the Username and Password that you were provided with Example Web Application #1.  See the setup instructions for this application on Canvas.</p>

        </div>

        <%
            String result = request.getParameter("result");
            if (result != null) {
        %>

        <div id="login_error" style="color: red;">

            <p>ERROR: There was a problem processing your login request.  Try entering your Username and Password again.  If it still does not work, check the Tomcat Apache server logs for errors or exceptions.</p>

        </div>

        <%
            }
        %>

    </body>

</html>