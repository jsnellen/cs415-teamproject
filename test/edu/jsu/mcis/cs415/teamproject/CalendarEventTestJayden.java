package edu.jsu.mcis.cs415.teamproject;

import edu.jsu.mcis.cs415.teamproject.dao.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CalendarEventTestJayden {
    
    // if this test fails to run, add "endorsed.classpath=" to "project.properties" file

    private DAOFactory daoFactory;

    @Before
    public void setup() {

        daoFactory = new DAOFactory("cs415.jdbc");

    }

    @Test
    public void test1GetCalendarEvent() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2022, 11, 3, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2022, 11, 30, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (2022-11-03 Hourly, 30 mins)");
        params.put("minute", Integer.toString(0));
        params.put("hour", "*");
        params.put("day_of_month", Integer.toString(3));
        params.put("month_of_year", Integer.toString(11));
        params.put("day_of_week", "*");
        params.put("year", Integer.toString(2022));
        params.put("duration", Long.toString(30));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test1GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(24, events.size());

    }
    
    @Test
    public void test2GetCalendarEvent() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2022, 11, 3, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2022, 11, 30, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (2022-11, 6:00 and 12:00 Every Thursday, 60 mins)");
        params.put("minute", Integer.toString(0));
        params.put("hour", "6,12");
        params.put("day_of_month", "*");
        params.put("month_of_year", Integer.toString(11));
        params.put("day_of_week", "4");
        params.put("year", Integer.toString(2022));
        params.put("duration", Long.toString(60));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test2GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(2, events.size());

    }
    
    @Test
    public void test3GetCalendarEvent() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2022, 11, 24, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2022, 11, 30, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (Thanksgiving Holiday, all day)");
        params.put("minute", Integer.toString(0));
        params.put("hour", Integer.toString(0));
        params.put("day_of_month", "*");
        params.put("month_of_year", Integer.toString(11));
        params.put("day_of_week", "4#4");
        params.put("year", Integer.toString(2022));
        params.put("duration", Long.toString(86400)); // 24 hours
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test3GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(1, events.size());

    }
    
    @Test
    public void test4GetCalendarEvent() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2022, 11, 3, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2022, 11, 30, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (2022-11-03, 12:30, 2 hours)");
        params.put("minute", Integer.toString(30));
        params.put("hour", Integer.toString(12));
        params.put("day_of_month", Integer.toString(3));
        params.put("month_of_year", Integer.toString(11));
        params.put("day_of_week", "*");
        params.put("year", Integer.toString(2022));
        params.put("duration", Long.toString(120));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test4GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(1, events.size());

    }
    
    @Test
    public void test5GetCalendarEvents() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2022, 12, 25, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2022, 12, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2022, 12, 30, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (Christmas Day, all day)");
        params.put("minute", Integer.toString(0));
        params.put("hour", Integer.toString(0));
        params.put("day_of_month", Integer.toString(25));
        params.put("month_of_year", Integer.toString(12));
        params.put("day_of_week", "*");
        params.put("year", Integer.toString(2022));
        params.put("duration", Long.toString(1439));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test5GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(1, events.size());
        
    }
    
    @Test
    public void test6GetCalendarEvents() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2023, 9, 4, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2023, 9, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2023, 9, 30, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (Labor Day, all day)");
        params.put("minute", Integer.toString(0));
        params.put("hour", Integer.toString(0));
        params.put("day_of_month", "*");
        params.put("month_of_year", Integer.toString(9));
        params.put("day_of_week", "1#1");
        params.put("year", Integer.toString(2023));
        params.put("duration", Long.toString(1439));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test6GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(1, events.size());
        
    }
    
    @Test
    public void test7GetCalendarEvents(){
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2023, 3, 10, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2023, 3, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2023, 3, 30, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (Bachelor Party, 5 hours)");
        params.put("minute", Integer.toString(0));
        params.put("hour", Integer.toString(21));
        params.put("day_of_month", Integer.toString(10));
        params.put("month_of_year", Integer.toString(3));
        params.put("day_of_week", "*");
        params.put("year", Integer.toString(2023));
        params.put("duration", Long.toString(300));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test7GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(1, events.size());
        
    }
    
    
}
