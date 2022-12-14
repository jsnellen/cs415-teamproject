/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var updateHandler = (function(){
    
    return {
        
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
                method: "PUT",
                data: $("#updateform").serialize(),
                dataType: "json",
                success: function(response){
                    if(response["success"] === true){
                        alert("Successfully updated user!");
                    }
                }
                
            });
            
            return false;
            
        }
        
    };
    
})();


