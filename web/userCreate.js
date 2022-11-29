var userCreate = (function (){
  
    return {
        
        isEmpty: function(str) {
            return !str.trim().length;
        },
        
        validatePass: function () {
            
            userPass = document.getElementById("password").value;
            confirmPass = document.getElementById("confirmPass").value;
            invalidPass = document.getElementById("invalidPass");
            
            if (userPass !== confirmPass) {
                 $(invalidPass).html("The passwords you entered do not match");
                 $(invalidPass).css("color", "red");
                 return false;
            }
            else {
                $(invalidPass).html("Congrats, your passwords match");
                $(invalidPass).css("color", "#018749");
                return true;
            }
        }, 
        
        createUserSubmit: function () {
            if (validatePass() === false) {
                alert("Please confirm your passwords before creating account");
                
            }
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
            
        }
        
        
    };
});

