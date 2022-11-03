package edu.jsu.mcis.cs415.teamproject;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Event {
    
    private Integer id;
    
    private String description;
    private String minute, hour, dayOfMonth, monthOfYear, dayOfWeek, year;
    
    private Long duration;
    private ZonedDateTime utcActive, utcInactive;
    
    private final int MIN_MINUTE = 0, MAX_MINUTE = 59;
    private final int MIN_HOUR = 0, MAX_HOUR = 23;
    private final int MIN_DAY_OF_MONTH = 1, MAX_DAY_OF_MONTH = 31;
    private final int MIN_MONTH = 1, MAX_MONTH = 12;
    private final int MIN_DAY_OF_WEEK = 1, MAX_DAY_OF_WEEK = 7;
    private final int MIN_YEAR = 1970, MAX_YEAR = 2099;
    
    private final String SEP_STEP = "/", SEP_RANGE = "-", SEP_LIST = ",";
    private final String SEP_DOW_ORDINAL = "#", WILDCARD = "*", LAST_DOW = "L";

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
    
    public ArrayList<CalendarEvent> toCalendarEventList(ZonedDateTime day) {
        
        ArrayList<CalendarEvent> calendarEvents = new ArrayList<>();
        
        day = day.withHour(0).withMinute(0).withSecond(0).withNano(0);
        
        Set<Integer> years = expand(MIN_YEAR, MAX_YEAR, year);
        Set<Integer> months = expand(MIN_MONTH, MAX_MONTH, monthOfYear);
        Set<Integer> days = expand(day, dayOfMonth, dayOfWeek);
        Set<Integer> hours = expand(MIN_HOUR, MAX_HOUR, hour);
        Set<Integer> minutes = expand(MIN_MINUTE, MAX_MINUTE, minute);
        
        if (years.contains(day.getYear())
                && months.contains(day.getMonthValue())
                && days.contains(day.getDayOfMonth())) {
            
            for (int h : hours) {
                
                for (int m : minutes) {
                    
                    ZonedDateTime start = day.withHour(h).withMinute(m);
                    ZonedDateTime stop = start.plus(duration, ChronoUnit.MINUTES);
                    
                    calendarEvents.add(new CalendarEvent(id, description, start, stop));
                    
                }
                
            }
            
        }
        
        return calendarEvents;
        
    }
    
    /*
        Expand an Event expression into a numeric collection of values.  (Useful
        for expanding a list of years, months, days of the month, hours, minutes,
        or days of the week.)
    */
    
    private Set expand(int min, int max, String expression) {
        
        HashSet<Integer> set = new HashSet<>();
        
        // split expression into parts corresponding to individual items
        
        String[] parts = expression.split(SEP_LIST + "+");
        
        // process individual list items
        
        for (String part : parts) {
            
            // set deafult step value; trim any leading/trailing whitespace
            
            int step = 1;
            part = part.trim();
            
            // if a different step value was specified, use it instead
            
            if (part.contains(SEP_STEP)) {
                int index = part.indexOf(SEP_STEP);
                step = Integer.parseInt(part.substring(index + 1).trim());
                part = part.substring(0, index).trim();
            }
            
            // if item is a wildcard, expand to full range of values
            
            if (part.contains(WILDCARD)) {
                set.addAll(getSequence(min, max, step));
            }
            
            // if item is a range, expand to the specified range
            
            else if (part.contains(SEP_RANGE)) {
                int index = part.indexOf(SEP_RANGE);
                int begin = Integer.parseInt(part.substring(0, index).trim());
                int end = Integer.parseInt(part.substring(index + 1).trim());
                set.addAll(getSequence(Math.max(min, begin), Math.min(max, end), step));
            }
            
            // if item is a single value, add value to collection (if within range)
            
            else if (part.matches("^[0-9]+$")) {
                int val = Integer.parseInt(part);
                if ((val >= min) && (val <= max)) {
                    set.add(val);
                }
            }
            
        }
        
        // return collection
        
        return set;
        
    }
    
    /*
        Expand a pair of Event day-of-month and day-of-week expressions into a
        numeric collection of values.  (Useful for combining day-of-month and
        day-of-week expressions into a corresponding list of days of the month.)
    */
    
    private Set expand(ZonedDateTime zdt, String domExpression, String dowExpression) {

        int daysInMonth = YearMonth.of(zdt.getYear(), zdt.getMonth()).lengthOfMonth();
        Set dom = expand(MIN_DAY_OF_MONTH, daysInMonth, domExpression);
        
        // create sets for days-of-week (dow) and corresponding calendar days (weekdays)
        
        HashSet<Integer> weekdays = new HashSet<>();
        HashSet<Integer> dow = new HashSet<>();
        
        // split days-of-week expression into parts corresponding to individual items
        
        String[] parts = dowExpression.split(SEP_LIST + "+");
        
        // process individual list items
        
        for (String part : parts) {
            
            // trim whitespace

            part = part.trim();
            
            // if item specifies an ordinal weekday number, add corresponding day-of-month to set
            
            if (part.contains(SEP_DOW_ORDINAL)) {
                int index = part.indexOf(SEP_DOW_ORDINAL);
                DayOfWeek day = DayOfWeek.values()[Integer.parseInt(part.substring(0, index).trim()) - 1];
                int ordinal = Integer.parseInt(part.substring(index + 1).trim());
                dow.add(zdt.with(TemporalAdjusters.dayOfWeekInMonth(ordinal, day)).getDayOfMonth());
            }
            
            // if item specifies a last weekday number, add corresponding day-of-month to set
            
            else if (part.contains(LAST_DOW)) {
                int index = part.indexOf(LAST_DOW);
                DayOfWeek day = DayOfWeek.values()[Integer.parseInt(part.substring(0, index).trim()) - 1];
                dow.add(zdt.with(TemporalAdjusters.lastInMonth(day)).getDayOfMonth());
            }
            
            // if item contains an ordinary expression, expand to collection of days-of-week
            
            else {
                weekdays.addAll(expand(MIN_DAY_OF_WEEK, MAX_DAY_OF_WEEK, part));
            }
            
        }
        
        // convert days-of-week to corresponding days-of-month; add to collection
        
        for (Integer e : weekdays) {
            DayOfWeek day = DayOfWeek.values()[e - 1];
            dow.addAll(getWeekdaysInMonth(zdt, day));
        }
        
        // is both DOM and DOW restricted?
        
        boolean domRestricted = !(domExpression.contains(WILDCARD));
        boolean dowRestricted = !(dowExpression.contains(WILDCARD));
        
        // if so, take union of both sets; if not, take intersection of both sets
        
        if (domRestricted && dowRestricted) {
            dom.addAll(dow);
        }
        else {
            dom.retainAll(dow);
        }
        
        // return collection
        
        return dom;
        
    }
    
    /*
        Expand a specific day of the week to the corresponding days of the month.
    */
    
    private Set getWeekdaysInMonth(ZonedDateTime zdt, DayOfWeek weekday) {
        
        HashSet<Integer> set = new HashSet<>();
        
        ZonedDateTime zdt_this = zdt.with(TemporalAdjusters.firstDayOfMonth());
        zdt_this = zdt_this.withHour(0).withMinute(0).withSecond(0).withNano(0);
        
        ZonedDateTime zdt_next = zdt_this.plusMonths(1);
        
        zdt_this = zdt_this.with(TemporalAdjusters.nextOrSame(weekday));
        
        while (zdt_this.isBefore(zdt_next)) {
            set.add(zdt_this.getDayOfMonth());
            zdt_this = zdt_this.with(TemporalAdjusters.next(weekday));
        }
        
        return set;
        
    }
    
    /*
        Generates a sequence of values from "min" to "max" (inclusive); useful
        for field expansion.
    */
    
    private List getSequence(int min, int max, int step) {
        
        List<Integer> values = new ArrayList(((max - min) / step) + 1);
        for(int i = min; i <= max; values.add(i), i += step);
        return values;
        
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDayOfMonth(String dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setMonthOfYear(String monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setUtcActive(ZonedDateTime utcActive) {
        this.utcActive = utcActive;
    }

    public void setUtcInactive(ZonedDateTime utcInactive) {
        this.utcInactive = utcInactive;
    }
    
}