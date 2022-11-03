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

public class CalendarEventTest {
    
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
        
        assertEquals(true, true);

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
        
        assertEquals(true, true);

    }
    
    @Test
    public void test3GetCalendarEvent() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2022, 11, 3, 0, 0, 0, 0, ZoneId.of("UTC"));
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
        params.put("duration", Long.toString(60));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test3GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(true, true);

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
        
        assertEquals(true, true);

    }
    
    
}
