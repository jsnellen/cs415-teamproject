<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <title>Create Account</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

    </head>

    <body>

        <form id="createUserform" name="createUserform" method="POST">
            
            <fieldset>
                <legend>Create Account</legend>


                <p>
                    <label for="j_username">Username:</label>
                    <input id="j_username" name="j_username" type="text" tabindex=1 />

                    <label for="j_password">Password:</label>
                    <input id="j_password" name="j_password" type="password" tabindex=2 />                    
                </p>

                <p>
                    <input type="submit" value="Create Account" tabindex=3 />
                </p>

            </fieldset>

        </form>


    </body>

</html>