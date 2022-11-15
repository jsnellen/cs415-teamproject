package edu.jsu.mcis.cs415.teamproject;

import java.time.ZoneId;
import java.util.HashMap;

public class User {
    
    private final Integer id;
    private final String username, description, email;
    private final ZoneId timezone;
    
    public User(HashMap<String, String> params) {
        
        this.id = Integer.parseInt(params.get("id"));
        this.username = params.get("username");
        this.description = params.get("description");
        this.email = params.get("email");
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

    public ZoneId getTimezone() {
        return timezone;
    }
    
}
