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

public class CalendarEventTestFavour {
    
    // if this test fails to run, add "endorsed.classpath=" to "project.properties" file

    private DAOFactory daoFactory;

    @Before
    public void setup() {

        daoFactory = new DAOFactory("cs415.jdbc");

    }

    @Test
    public void test1GetCalendarEvent() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2017, 10, 26, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2017, 10, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2017, 10, 31, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (2017-10-26 - 2017-10-31 At 8:15 AM & 6:15 PM, 15 mins)");
        params.put("minute", Integer.toString(15));
        params.put("hour", "8,18");
        params.put("day_of_month", "26,28");
        params.put("month_of_year", Integer.toString(10));
        params.put("day_of_week", "*");
        params.put("year", Integer.toString(2017));
        params.put("duration", Long.toString(15));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test1GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(2, events.size());

    }
    
    @Test
    public void test2GetCalendarEvent() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(1979, 12, 25, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(1979, 12, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(1979, 12, 31, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (1979-12-25, All Day Christmas");
        params.put("minute", Integer.toString(0));
        params.put("hour", Integer.toString(0));
        params.put("day_of_month", "*");
        params.put("month_of_year", Integer.toString(12));
        params.put("day_of_week", "*");
        params.put("year", Integer.toString(1979));
        params.put("duration", Long.toString(1440));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        Event e1 = new Event(params);
        
        ArrayList<CalendarEvent> events = e1.toCalendarEventList(day);
        
        System.err.println("test2GetCalendarEvent() Number of Calendar Events: " + events.size());
        
        for (CalendarEvent e : events) {
        
            System.err.println(e.toString());
            
        }
        
        assertEquals(1, events.size());

    }
    
    @Test
    public void test3GetCalendarEvent() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        ZonedDateTime day = ZonedDateTime.of(2022, 5, 20, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2022, 5, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2022, 5, 31, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (Birthday Dinner, 5 PM - 9 PM)");
        params.put("minute", Integer.toString(0));
        params.put("hour", Integer.toString(17));
        params.put("day_of_month", "*");
        params.put("month_of_year", Integer.toString(5));
        params.put("day_of_week", "*");
        params.put("year", Integer.toString(2022));
        params.put("duration", Long.toString(240)); // 4 hours
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
        
        ZonedDateTime day = ZonedDateTime.of(2023, 8, 23, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active = ZonedDateTime.of(2023, 8, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2023, 8, 31, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test Event (2023-8-23, 09:30, 6 hours)");
        params.put("minute", Integer.toString(30));
        params.put("hour", Integer.toString(9));
        params.put("day_of_month", "23/25");
        params.put("month_of_year", Integer.toString(8));
        params.put("day_of_week", "*");
        params.put("year", Integer.toString(2023));
        params.put("duration", Long.toString(360));
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
    
    
}
