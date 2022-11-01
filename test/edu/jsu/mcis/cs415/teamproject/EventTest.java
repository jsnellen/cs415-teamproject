package edu.jsu.mcis.cs415.teamproject;

import edu.jsu.mcis.cs415.teamproject.dao.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class EventTest {
    
    // if this test fails to run, add "endorsed.classpath=" to "project.properties" file

    private DAOFactory daoFactory;

    @Before
    public void setup() {

        daoFactory = new DAOFactory("cs415.jdbc");

    }

    @Test
    public void test1ConstructEvent() {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        String expected = "Test ConstructEvent\t23\t1\t2\t11\t*\t*\t60\t2022-11-01 00:00:00\t2022-11-30 23:59:59";
        
        /* Create New Event Object */
        
        ZonedDateTime active = ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive = ZonedDateTime.of(2022, 11, 30, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test ConstructEvent");
        params.put("minute", Integer.toString(23));
        params.put("hour", Integer.toString(1));
        params.put("day_of_month", Integer.toString(2));
        params.put("month_of_year", Integer.toString(11));
        params.put("day_of_week", "*");
        params.put("year", "*");
        params.put("duration", Long.toString(60));
        params.put("utc_active", dtf.format(active));
        params.put("utc_inactive", dtf.format(inactive));
        
        /* Check Event Construction */
        
        Event e1 = new Event(params);
        
        assertEquals(expected, e1.toString());

    }
    
    @Test
    public void test2CreateInsertDeleteEvent() {
        
        EventDAO dao = daoFactory.getEventDAO();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        String expected = "Test CreateInsertDeleteEvent\t34\t12\t2\t1\t0\t2022\t30\t2022-01-01 00:00:00\t(none)";
        
        /* Create New Event */
        
        ZonedDateTime active = ZonedDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test CreateInsertDeleteEvent");
        params.put("minute", Integer.toString(34));
        params.put("hour", Integer.toString(12));
        params.put("day_of_month", Integer.toString(2));
        params.put("month_of_year", Integer.toString(1));
        params.put("day_of_week", Integer.toString(0));
        params.put("year", Integer.toString(2022));
        params.put("duration", Long.toString(30));
        params.put("utc_active", dtf.format(active));
        
        /* Check Event Construction */
        
        Event e1 = new Event(params);
        
        assertEquals(expected, e1.toString());
        
        /* Check Event Insertion and Retrieval */
        
        int id = dao.create(e1);
        System.err.println("New Event ID: " + id);
        
        Event e2 = dao.find(id);
        
        assertEquals(e1.toString(), e2.toString());
        
        /* Check Event Deletion */
        
        System.err.println("Deleting Event ID: " + id);
        
        boolean result = dao.delete(id);
        
        assertEquals(true, result);

    }
    
    @Test
    public void test3CreateInsertUpdateDeleteEvent() {
        
        EventDAO dao = daoFactory.getEventDAO();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        String expected_original = "Test CreateInsertUpdateDeleteEventOriginal\t0\t0\t*\t10\t0\t*\t30\t2022-10-01 00:00:00\t(none)";
        String expected_update = "Test CreateInsertUpdateDeleteEventUpdate\t30\t12\t15\t11\t*\t2023\t60\t2022-10-31 23:59:59\t2022-11-30 23:59:59";
        
        /* Create New Event Object */
        
        ZonedDateTime active_original = ZonedDateTime.of(2022, 10, 1, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime active_update = ZonedDateTime.of(2022, 10, 31, 23, 59, 59, 0, ZoneId.of("UTC"));
        ZonedDateTime inactive_update = ZonedDateTime.of(2022, 11, 30, 23, 59, 59, 0, ZoneId.of("UTC"));
        
        HashMap<String, String> params = new HashMap<>();
        
        params.put("description", "Test CreateInsertUpdateDeleteEventOriginal");
        params.put("minute", Integer.toString(0));
        params.put("hour", Integer.toString(0));
        params.put("day_of_month", "*");
        params.put("month_of_year", Integer.toString(10));
        params.put("day_of_week", "0");
        params.put("year", "*");
        params.put("duration", Long.toString(30));
        params.put("utc_active", dtf.format(active_original));
        params.put("utc_inactive", null);
        
        /* Check Event Construction */
        
        Event e1 = new Event(params);
        
        assertEquals(expected_original, e1.toString());
        
        /* Check Event Insertion and Retrieval */
        
        int id = dao.create(e1);
        System.err.println("New Event ID: " + id);
        
        Event e2 = dao.find(id);
        
        assertEquals(e1.toString(), e2.toString());
        
        /* Check Event Update and Retrieval */
        
        e2.setDescription("Test CreateInsertUpdateDeleteEventUpdate");
        e2.setMinute(Integer.toString(30));
        e2.setHour(Integer.toString(12));
        e2.setDayOfMonth(Integer.toString(15));
        e2.setMonthOfYear(Integer.toString(11));
        e2.setDayOfWeek("*");
        e2.setYear("2023");
        e2.setDuration(60L);
        e2.setUtcActive(active_update);
        e2.setUtcInactive(inactive_update);
        
        assertEquals(expected_update, e2.toString());
        
        dao.update(e2);
        
        Event e3 = dao.find(id);
        
        assertEquals(e2.toString(), e3.toString());
        
        /* Check Event Deletion */
        
        System.err.println("Deleting Event ID: " + id);
        
        boolean result1 = dao.delete(id);
        
        assertEquals(true, result1);
        
    }
    
}
