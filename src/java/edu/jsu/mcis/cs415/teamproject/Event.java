package edu.jsu.mcis.cs415.teamproject;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Event {
    
    private final Integer id;
    
    private final String description;
    private final String minute, hour, dayOfMonth, monthOfYear, dayOfWeek, year;
    
    private final Long duration;
    private final ZonedDateTime utcActive, utcInactive;

    public Event(Map<String, String> p) {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        if (p.get("id") != null) {
            this.id = Integer.parseInt(p.get("id"));
        }
        else {
            this.id = null;
        }

        this.description = p.get("description");
        this.minute = p.get("minute");
        this.hour = p.get("hour");
        this.dayOfMonth = p.get("day_of_month");
        this.monthOfYear = p.get("month_of_year");
        this.dayOfWeek = p.get("day_of_week");
        this.year = p.get("year");

        this.duration = Long.parseLong(p.get("duration"));

        ZoneId zoneid = ZoneId.of("UTC");
        String active = p.get("utc_active");
        String inactive = p.get("utc_inactive");

        if ( active != null ) {
            this.utcActive = ZonedDateTime.of(LocalDateTime.parse(active, dtf), zoneid);
        }
        else {
            this.utcActive = ZonedDateTime.of(LocalDateTime.now(), zoneid);
        }

        if ( inactive != null ) {
            this.utcInactive = ZonedDateTime.of(LocalDateTime.parse(inactive, dtf), zoneid);
        }
        else {
            this.utcInactive = null;
        }
        
    }

    @Override
    public String toString() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        StringBuilder s = new StringBuilder();
        
        s.append(description).append('\t');
        s.append(minute).append('\t');
        s.append(hour).append('\t');
        s.append(dayOfMonth).append('\t');
        s.append(monthOfYear).append('\t');
        s.append(dayOfWeek).append('\t');
        s.append(year).append('\t');
        s.append(duration).append('\t');
        s.append(dtf.format(utcActive)).append('\t');
        s.append((utcInactive != null) ? dtf.format(utcInactive) : "(none)");
        
        return s.toString();
        
    }
    

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getMinute() {
        return minute;
    }

    public String getHour() {
        return hour;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public String getMonthOfYear() {
        return monthOfYear;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getYear() {
        return year;
    }

    public Long getDuration() {
        return duration;
    }

    public ZonedDateTime getUtcActive() {
        return utcActive;
    }

    public ZonedDateTime getUtcInactive() {
        return utcInactive;
    }
    
}