package edu.jsu.mcis.cs415.teamproject.dao;

import edu.jsu.mcis.cs415.teamproject.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class EventDAO {
    
    private final DAOFactory daoFactory;
    
    private final String QUERY_SELECT = "SELECT * FROM event WHERE id = ?";
    
    private final String QUERY_INSERT = "INSERT INTO event (description, "
            + "`minute`, `hour`, day_of_month, month_of_year, day_of_week, "
            + "`year`, duration, utc_active, utc_inactive) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private final String QUERY_UPDATE = "UPDATE event SET description = ?, "
            + "`minute` = ?, `hour` = ?, day_of_month = ?, month_of_year = ?, "
            + "day_of_week = ?, `year` = ?, duration = ?, utc_active = ?, "
            + "utc_inactive = ? WHERE id = ?";
    
    private final String QUERY_DELETE = "DELETE FROM event WHERE id = ?";
    
    EventDAO(DAOFactory dao) {
        this.daoFactory = dao;
    }
    
    public Event find(int id) throws DAOException  {
        
        Event result = null;

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = conn.prepareStatement(QUERY_SELECT);
            ps.setInt(1, id);
            
            boolean hasresults = ps.execute();

            if (hasresults) {

                rs = ps.getResultSet();
                
                if (rs.next()) {
                    
                    HashMap<String, String> params = new HashMap<>();
                    
                    params.put("id", rs.getString("id"));
                    params.put("description", rs.getString("description"));
                    params.put("minute", rs.getString("minute"));
                    params.put("hour", rs.getString("hour"));
                    params.put("day_of_month", rs.getString("day_of_month"));
                    params.put("month_of_year", rs.getString("month_of_year"));
                    params.put("day_of_week", rs.getString("day_of_week"));
                    params.put("year", rs.getString("year"));
                    params.put("duration", rs.getString("duration"));
                    params.put("utc_active", rs.getString("utc_active"));
                    params.put("utc_inactive", rs.getString("utc_inactive"));
                    
                    result = new Event(params);
                    
                }

            }

        }
        catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        finally {

            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }

        }

        return result;

    }

    public Integer create(Event event) throws DAOException  {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Integer key = null;

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet keys = null;

        try {

            ps = conn.prepareStatement(QUERY_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, event.getDescription());
            ps.setString(2, event.getMinute());
            ps.setString(3, event.getHour());
            ps.setString(4, event.getDayOfMonth());
            ps.setString(5, event.getMonthOfYear());
            ps.setString(6, event.getDayOfWeek());
            ps.setString(7, event.getYear());
            ps.setString(8, Long.toString(event.getDuration()));
            ps.setString(9, dtf.format(event.getUtcActive()));
            
            if (event.getUtcInactive() != null) {
                ps.setString(10, dtf.format(event.getUtcInactive()));
            }
            else {
                ps.setTimestamp(10, null);
            }
            
            int result = ps.executeUpdate();

            if (result == 1) {
                
                keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    key = keys.getInt(1);
                }

            }

        }
        catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        finally {

            if (keys != null) {
                try {
                    keys.close();
                    keys = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }

        }

        return key;

    }
    
    public boolean update(Event event) throws DAOException  {

        boolean result = false;

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(QUERY_UPDATE);
            ps.setString(1, event.getDescription());
            ps.setString(2, event.getMinute());
            ps.setString(3, event.getHour());
            ps.setString(4, event.getDayOfMonth());
            ps.setString(5, event.getMonthOfYear());
            ps.setString(6, event.getDayOfWeek());
            ps.setString(7, event.getYear());
            ps.setString(8, Long.toString(event.getDuration()));
            ps.setString(9, event.getUtcActive().toString());
            ps.setString(10, event.getUtcInactive().toString());
            ps.setInt(11, event.getId());
            
            int rows = ps.executeUpdate();

            if (rows == 1) {
                result = true;
            }

        }
        catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        finally {

            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }

        }

        return result;

    }
    
    public boolean delete(int id) throws DAOException {

        boolean result = false;

        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(QUERY_DELETE);
            ps.setInt(1, id);
            
            int rows = ps.executeUpdate();

            if (rows == 1) {
                result = true;
            }

        }
        catch (Exception e) {
            throw new DAOException(e.getMessage());
        }
        finally {

            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) { throw new DAOException(e.getMessage()); }
            }

        }

        return result;

    }
    
}
