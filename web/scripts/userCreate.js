var userCreate = (function () {
  
    return {
        
        isEmpty: function(str) {
            return !str.trim().length;
        },
        
        validatePass: function () {
            
            var result = false;
            
            userPass = document.getElementById("password").value;
            confirmPass = document.getElementById("confirmPass").value;
            invalidPass = document.getElementById("invalidPass");
            
            alert("Password: " + userPass + ", Confirm: " + confirmPass);
            
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
            
            alert("TEST");
            
            /*if ( !validatePass() ) {
                alert("Please confirm your passwords before creating account");
            }*/
            
            $.ajax({
                url: "/UserServlet", 
                method: "POST",
                data: $("#createUserform").serialize(),
                dataType: "html", 
                success: function (response) {
                    $("#confirmCreate").html(response);
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

