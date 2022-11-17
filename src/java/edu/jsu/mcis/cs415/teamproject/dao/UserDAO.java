package edu.jsu.mcis.cs415.teamproject.dao;

import edu.jsu.mcis.cs415.teamproject.*;
import java.sql.*;
import java.util.*;


public class UserDAO {
    
    private final DAOFactory daoFactory;
    
    UserDAO(DAOFactory dao) {
        this.daoFactory = dao;
    }
    
    private final String QUERY_SELECT_USER = "SELECT * FROM user WHERE id = ?";
    private final String QUERY_INSERT_USER_LOGIN = "INSERT INTO `login` (username, `password`) VALUES (?, SHA2(?, 512))";
    private final String QUERY_INSERT_USER_ROLE = "INSERT INTO user_to_role (username, rolename) VALUES (?, user)";
    private final String QUERY_INSERT_USER = "INSERT INTO `user` (username, description, email, timezone) VALUES (?, ?, ?, ?)";
    private final String QUERY_UPDATE_USER_LOGIN = "";
    private final String QUERY_UPDATE_USER_ROLE = "";
    private final String QUERY_UPDATE_USER = "";
    private final String QUERY_DELETE_USER = "DELETE FROM `user` WHERE id = ?";
     private final String QUERY_DELETE_USER_ROLE = "DELETE FROM user_to_role WHERE username = ?";
    private final String QUERY_DELETE_USER_LOGIN = "DELETE FROM `login` WHERE username = ?";
   
    public User find(int id) throws DAOException{
        
        User result = null; 
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            
            ps = conn.prepareStatement(QUERY_SELECT_USER);
            ps.setInt(1, id);
            
            boolean hasresults = ps.execute();
            
            if (hasresults) {
                rs = ps.getResultSet();
                
                if (rs.next()) {
                    
                    HashMap<String, String> params = new HashMap<>();
                    
                    params.put("id", rs.getString("id"));
                    params.put("username", rs.getString("username"));
                    params.put("description", rs.getString("description"));
                    params.put("timezone", rs.getString("timezone"));
                    params.put("email", rs.getString("email"));
                    
                    result = new User(params);
                    
                }
            }
        }
        catch(Exception e) {
            throw new DAOException (e.getMessage());
        }
        finally {
            
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                }
                catch (Exception e) {throw new DAOException (e.getMessage());}
            }
            if (ps != null) {
                try {
                    ps.close();
                    ps = null;
                }
                catch (Exception e) {throw new DAOException (e.getMessage());}
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                }
                catch (Exception e) {throw new DAOException (e.getMessage());}
            }
        }
        
        return result;
    }
    
    public Integer create (Login login, User user) throws DAOException {
        
        Integer key = null;
        
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps, ps2, ps3 = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(QUERY_INSERT_USER_LOGIN, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, login.getUsername());
            ps.setString(2, login.getPassword());
            
            int result = ps.executeUpdate();
            
            if (result == 1) {
                
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    key = rs.getInt(1);
                }
            }
            ps2 = conn.prepareStatement(QUERY_INSERT_USER_ROLE, PreparedStatement.RETURN_GENERATED_KEYS);
            ps2.setString(1, user.getUsername());
            ps2.setString(2, "user");
            
            ps2.addBatch();
            //result = ps2.executeBatch();
            
            ps3 = conn.prepareStatement(QUERY_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            ps3.setString(1, user.getUsername());
            ps3.setString(2, user.getDescription());
            ps3.setString(3, user.getTimezone().toString());
            ps3.setString(4, user.getEmail());
            
          
        }
        catch (Exception e) {throw new DAOException (e.getMessage());} 
        return key;
    }
}
