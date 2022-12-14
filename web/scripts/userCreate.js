var userCreate = (function () {
  
    return {
        
        validatePass: function () {
            
            var result = false;
            
            userPass = document.getElementById("password").value;
            confirmPass = document.getElementById("confirmPass").value;
            invalidPass = document.getElementById("invalidPass");
            
            if (userPass !== confirmPass) {
                 $(invalidPass).html("The passwords you entered do not match");
                 $(invalidPass).css("color", "red");
            }
            else {
                $(invalidPass).html("Congrats, your passwords match");
                $(invalidPass).css("color", "#018749");
                result = true;
            }
            
            return result;
            
        }, 
        
        createUserSubmit: function () {
            
            if ( !userCreate.validatePass() ) {
                alert("Please confirm your passwords before creating account");
                console.log("Please confirm your passwords before creating account");
                return false;
            }
            else {
                console.log("Congrats!!");
            }
            
            $.ajax({
                url: "CreateUserServlet", 
                method: "POST",
                data: $("#createUserform").serialize(),
                dataType: "json", 
                success: function (response) {
                    
                    if (response["success"] === true)
                        $("#confirmCreate").html("Welcome!  Click <a href=\"login.jsp\">here</a> to log in.");
                    else
                        $("#confirmCreate").html(JSON.stringify(response));
                    
                }
                
            });
            
            return false;
            
        },
        
        jQueryTest: function () {
            
            alert("TEST");

            var p = document.createElement("p");
            $(p).html("jQuery Version: " + $().jquery);
            $("#output").append(p);

        }
        
        
    };
    
})();

