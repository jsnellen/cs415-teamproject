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
    
}
