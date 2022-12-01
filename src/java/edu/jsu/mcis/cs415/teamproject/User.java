package edu.jsu.mcis.cs415.teamproject;

import java.time.ZoneId;
import java.util.HashMap;

public class User {
    
    private final Integer id;
    private final String username, description, email, password;
    private final ZoneId timezone;
    
    public User (HashMap<String, String> params) {

        String id = params.get("id");
        
        if (id != null)
            this.id = Integer.parseInt(id);
        else
            this.id = null;
        
        this.username = params.get("username");
        this.description = params.get("description");
        this.email = params.get("email");
        this.password = params.get("password");
        this.timezone = ZoneId.of(params.get("timezone"));
        
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ZoneId getTimezone() {
        return timezone;
    }
    
    @Override
    public String toString() {
        
        StringBuilder s = new StringBuilder();
        
        s.append("Username: ").append(username).append('\n');
        s.append("Description: ").append(description).append('\n');
        s.append("Email: ").append(email).append('\n');
        s.append("Time Zone: ").append(timezone).append('\n');
        s.append("Password: ").append(password).append('\n');
        
        return s.toString().trim();
        
    }
    
}
