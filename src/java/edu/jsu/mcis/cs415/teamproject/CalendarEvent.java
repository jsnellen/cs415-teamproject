package edu.jsu.mcis.cs415.teamproject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CalendarEvent {
    
    private Integer eventid;
    private String description;
    
    private ZonedDateTime start, stop;

    public CalendarEvent(Integer eventid, String description, ZonedDateTime start, ZonedDateTime stop) {
        
        this.eventid = eventid;
        this.description = description;
        this.start = start;
        this.stop = stop;
        
    }

    @Override
    public String toString() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        StringBuilder s = new StringBuilder();
        
        s.append(description).append(": ");
        s.append(dtf.format(start)).append(" - ").append(dtf.format(stop));
        
        return s.toString();
        
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getStop() {
        return stop;
    }

    public void setStop(ZonedDateTime stop) {
        this.stop = stop;
    }
    
}