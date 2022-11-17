package edu.jsu.mcis.cs415.teamproject;
import java.util.*;


public class Login {
    
    private  String username;
    private  String password;
    
    public Login (HashMap<String, String> params) {
        this.username = params.get("username");
        this.password = params.get("password");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
