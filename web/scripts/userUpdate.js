/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var updateHandler = (function(){
    
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
        
        fillUserInfo: function(response){
            $("#username").val(response["username"]);
            $("#description").val(response["description"]);
            $("#timezone").val(response["timezone"]);
            $("#email").val(response["email"]);
        },
        
        getUserInfo: function(){            
            
            $.ajax({
                
                url: "http://localhost:8180/cs415-teamproject/UserServlet",
                method: "GET",
                dataType: "json",
                success: function(response){
                    if(response["success"] === true){
                        updateHandler.fillUserInfo(response);
                    }
                }
                
            });
            
            return false;
            
        },
        
        updateUserInfo: function(){
            
            $.ajax({
                
                url: "http://localhost:8180/cs415-teamproject/UserServlet",
                type: "PUT",
                data: $("#updateform").serialize(),
                dataType: "json",
                success: function(response){
                    if(response["success"] === true){
                        alert("Updated user successfully!");
                    }
                }
                
            });
              
            return false;
            
        },
        
        changeUserPassword: function(){
            
            if ( !updateHandler.validatePass() ) {
                alert("Please confirm your passwords before submitting");
                console.log("Please confirm your passwords before submitting");
                return false;
            }
            else {
                console.log("Congrats!!");
            }
            
            $.ajax({
                
                url: "http://localhost:8180/cs415-teamproject/UserServlet",
                type: "PUT",
                data: {"password": $("#password").val()},
                dataType: "json",
                success: function(response){
                    if(response["success"] === true){
                        alert("Updated password successfully!");
                    }
                    else{
                        alert("Something went wrong.");
                    }
                },
                error: function(){
                    alert("Something went wrong.");
                }
                
            });
              
            return false;
            
            
        },
        
        resetUserPassword: function(){
            
            if ( !updateHandler.validatePass() ) {
                alert("Please confirm your passwords before submitting");
                console.log("Please confirm your passwords before submitting");
                return false;
            }
            else {
                console.log("Congrats!!");
            }
            
            $.ajax({
                
                url: "http://localhost:8180/cs415-teamproject/UserServlet",
                type: "PUT",
                data: {"remoteuser": $("#remoteuser").val() ,"password": $("#password").val()},
                dataType: "json",
                success: function(response){
                    if(response["success"] === true){
                        alert("Updated password successfully!");
                    }
                    else{
                        alert("Something went wrong.");
                    }
                },
                error: function(){
                    alert("Something went wrong.");
                }
                
            });
              
            return false;
            
        },
        
        deleteUserAccount: function(){
            
            var result = confirm("Are you sure you want to delete your account? " +
                    "All account data will be lost!");
            
            if(result === false){
                return false;
            }
            
            $.ajax({
                
                url: "http://localhost:8180/cs415-teamproject/UserServlet",
                type: "DELETE",
                dataType: "json",
                success: function(response){
                    if(response["success"] === true){
                        alert("Account succesfully deleted!");
                        
                        window.open("http://localhost:8180/cs415-teamproject/main/logout.jsp", '_self', false);
                    }
                    else{
                        alert("Sorry. Something went wrong.");
                    }
                },
                error: function(){
                    alert("Sorry. Something went wrong.");
                }
                
            });
            
            return false;
            
        }
        
    };
    
})();


