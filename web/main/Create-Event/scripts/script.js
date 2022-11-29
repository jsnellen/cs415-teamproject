var UITest = {
    
    jQueryTest: function () {

        var p = document.createElement("p");
        $(p).html("jQuery Version: " + $().jquery);
        $("#output").append(p);

    },
    
    onClickShow: function(dialog) {
        var element = document.getElementById(dialog);
        $("#overlay").fadeIn(300);
        $(element).css("display", "block");
    },
    
    onClickOK: function(dialog) {
        
        /* Get Dialog Root Element */
        
        var element = document.getElementById(dialog);
        
        /* Get Form Data */
        
        var form = $(element).find("form").serializeArray();
        
        /* Collect Data to JSON Object */
        
        var data = {};
        for (var index in form) {
            var key = form[index].name;
            var value = form[index].value;
            data[key] = value;
        }
        
        /* Close Dialog */
        
        this.dialogClose(dialog);
        
        /* Process Data */
        
        this.process(dialog, data);
        
    },
    
    process: function(dialog, data) {
        
        if (dialog === "dialog1") {
            this.showElements(data);
        }
        else {
            this.showElements(data);
        }
        
    },
    
    onClickCancel: function(dialog) {
        this.dialogClose(dialog);
    },
    
    dialogClose: function(dialog) {
        
        /* Get Dialog Root Element */
        
        var element = document.getElementById(dialog);
        
        /* Reset Form; Hide Dialog and Overlay */
        
        $(element).find("form").trigger("reset");
        $(element).css("display", "none");
        $("#overlay").css("display", "none");
        
    },
    
    showElements: function(data) {
        
        var output = "";
        
        console.log(JSON.stringify(data));
        
        for (var key in data) {
            var value = data[key];
            output += "<p>" + key + ": " + value + "</p>";
        }
        
        $("#output").html(output);
        
    },
    
    showGreeting: function(data) {
        
        var output = "<p>Hello, ";
        output += data.firstname + " " + data.middlename + " " + data.lastname + "!</p>";
        $("#output").html(output);
        
    }
    
};