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
    private final String QUERY_SELECT_USER_LOGIN = "SELECT password FROM login WHERE username=?";
    private final String QUERY_INSERT_USER_LOGIN = "INSERT INTO `login` (username, `password`) VALUES (?, SHA2(?, 512))";
    private final String QUERY_INSERT_USER_LOGIN2 = "INSERT INTO `login` (username, `password`) VALUES (?, ?)";
    private final String QUERY_INSERT_USER_ROLE = "INSERT INTO user_to_role (username, rolename) VALUES (?, ?)";
    private final String QUERY_INSERT_USER = "INSERT INTO `user` (username, description, timezone, email) VALUES (?, ?, ?, ?)";
    private final String QUERY_UPDATE_USER_PASSWORD = "UPDATE login SET " +
            "password=SHA2(?, 512) WHERE username=?";
    private final String QUERY_UPDATE_USER_ROLE = "UPDATE user_to_role SET " +
            "username=? WHERE username=?";
    private final String QUERY_UPDATE_USER = "UPDATE user SET username=?, " +
            "description=?, timezone=?, email=? WHERE username=?";
    private final String QUERY_DELETE_USER = "DELETE FROM `user` WHERE id = ?";
    private final String QUERY_DELETE_USER_ROLE = "DELETE FROM user_to_role WHERE username = ?";
    private final String QUERY_DELETE_USER_LOGIN = "DELETE FROM `login` WHERE username = ?";
    
    private final String QUERY_GET_USERID = "SELECT id FROM `user` WHERE username = ?";
    
    private final String USER_ROLENAME = "user";
   
    public User find(int id) throws DAOException{
        
        User result = null; 
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null, ps2 = null;
        ResultSet rs = null;
        
        try {
            String userid = null;
            String username = null;
            String passwordhash = null;
            String description = null;
            String timezone = null;
            String email = null;
            
            ps = conn.prepareStatement(QUERY_SELECT_USER);
            ps.setInt(1, id);
            
            boolean hasresults = ps.execute();
            
            if (hasresults) {
                rs = ps.getResultSet();
                
                if (rs.next()) {
                    
                    userid = rs.getString("id");
                    username = rs.getString("username");
                    description = rs.getString("description");
                    timezone = rs.getString("timezone");
                    email = rs.getString("email");
                    
                }
                
                
            }
            
            ps2 = conn.prepareStatement(QUERY_SELECT_USER_LOGIN);
            ps2.setString(1, username);
            
            hasresults = ps2.execute();
            
            if(hasresults){
                
                rs = ps2.getResultSet();
                
                if(rs.next()){
                    
                    passwordhash = rs.getString("password");
                    
                    HashMap<String, String> params = new HashMap<>();
                    
                    params.put("id", userid);
                    params.put("username", username);
                    params.put("passwordhash", passwordhash);
                    params.put("description", description);
                    params.put("timezone", timezone);
                    params.put("email", email);
                    
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
            if (ps2 != null) {
                try {
                    ps2.close();
                    ps2 = null;
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
    
    public Integer create (User user) throws DAOException {
        
        Integer key = null;
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null, ps2 = null, ps3 = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(QUERY_INSERT_USER_LOGIN);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            
            int result = ps.executeUpdate();
            
            if (result == 1) {
                
                ps2 = conn.prepareStatement(QUERY_INSERT_USER_ROLE);
                ps2.setString(1, user.getUsername());
                ps2.setString(2, USER_ROLENAME);

                result = ps2.executeUpdate();

                if (result ==  1) {

                    ps3 = conn.prepareStatement(QUERY_INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps3.setString(1, user.getUsername());
                    ps3.setString(2, user.getDescription());
                    ps3.setString(3, user.getTimezone().toString());
                    ps3.setString(4, user.getEmail());

                    result = ps3.executeUpdate();

                    if (result == 1) {

                        rs = ps3.getGeneratedKeys();
                        if (rs.next()) {
                            key = rs.getInt(1);
                        }
                        
                    }

                }
                
            }
          
        }
        catch (Exception e) {throw new DAOException (e.getMessage());}
        finally {
            
            if (rs != null) {
                try {
                    rs.close();
                    ps.close();
                    ps2.close();
                    ps3.close();
                    rs = null;
                    ps = null;
                    ps2 = null;
                    ps3 = null;
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
        
        return key;
        
    }
    
    
    public Integer findId(String username) throws DAOException {
        
        Integer id = null;
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            ps = conn.prepareStatement(QUERY_GET_USERID);
            ps.setString(1, username);
            
            boolean hasresults = ps.execute();
            
            if (hasresults) {
                
                rs = ps.getResultSet();
                
                if (rs.next()) {
                    
                    id = rs.getInt("id");
                    
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
        
        return id;
        
    }
    
    public boolean update(User new_user, User old_user){
        boolean result = false;
        
        String oldUsername = old_user.getUsername();
        String newUsername = new_user.getUsername();
        String oldHashedPassword = old_user.getPasswordhash();
        String newHashedPassword = new_user.getPasswordhash();
        
        try{
            
            System.err.println("Old User: ");
            System.err.println(old_user.toString());
            System.err.println();
            System.err.println("New User: ");
            System.err.println(new_user.toString());
            
            // if the user wants to keep their current password
            if(oldHashedPassword.equals(newHashedPassword)){
                // if user wants to keep current username
                if(oldUsername.equals(newUsername)){
                    result = updateUser(new_user, old_user);
                }
                // if user wants to change current username
                else{
                    result = updateUserInfo1(new_user, old_user);
                }
            }
            // if the user want to change their current password
            else if(!(oldHashedPassword.equals(newHashedPassword))){
                // if user wants to keep current username
                if(oldUsername.equals(newUsername)){
                    result = updateLoginPassword(new_user) && updateUser(new_user, old_user);
                }
                // if user want to change current username
                else{
                    result = updateUserInfo2(new_user, old_user);
                }
            }
            
            return result;
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return false;
        
    }
    
    public boolean delete(int id) throws DAOException{
        
        boolean result = false;
        
        return result;
        
    }
    
    public boolean delete(User user) throws DAOException{
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null, ps2 = null, ps3 = null;
        ResultSet rs = null;
        
        boolean result = false;
        int rows;
        
        try{
            
            ps = conn.prepareStatement(QUERY_DELETE_USER);
            ps.setInt(1, user.getId());
            
            rows = ps.executeUpdate();
            
            if(rows == 1){
                
                ps2 = conn.prepareStatement(QUERY_DELETE_USER_ROLE);
                ps2.setString(1, user.getUsername());
                rows = ps2.executeUpdate();
                
                if(rows == 1){
                    
                    ps3 = conn.prepareStatement(QUERY_DELETE_USER_LOGIN);
                    ps3.setString(1, user.getUsername());
                    rows = ps3.executeUpdate();
                    
                    if(rows == 1){
                        
                        result = true;
                        
                    }
                    
                }
                
            }
            
        }
        catch(Exception e){
            throw new DAOException (e.getMessage());
        }
        finally{
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
            if (ps2 != null) {
                try {
                    ps2.close();
                    ps2 = null;
                }
                catch (Exception e) {throw new DAOException (e.getMessage());}
            }
            if (ps3 != null) {
                try {
                    ps3.close();
                    ps3 = null;
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
    
    private boolean updateUserInfo1(User new_user, User old_user){
        
        if(insertUserLogin2(new_user)){
            if(updateUser(new_user, old_user)){
                if(updateUserToRole(new_user, old_user)){
                    if(deleteOldLoginRecord(old_user)){
                        return true;
                    }
                }
            }
        }
        else{
            System.err.println("Update process failed...");
        }
        
        return false;
    }
    
    private boolean updateUserInfo2(User new_user, User old_user){
        
        if(insertUserLogin(new_user)){
            if(updateUser(new_user, old_user)){
                if(updateUserToRole(new_user, old_user)){
                    if(deleteOldLoginRecord(old_user)){
                        return true;
                    }
                }
            }
        }
        else{
            System.err.println("Update process failed...");
        }
        
        return false;
    }
    
    private boolean insertUserLogin(User new_user){
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean result = false;
        
        try {
            
            ps = conn.prepareStatement(QUERY_INSERT_USER_LOGIN);
            ps.setString(1, new_user.getUsername());
            ps.setString(2, new_user.getPassword());
            
            int rows = ps.executeUpdate();
            
            if(rows == 1){
                result = true;
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
    
    private boolean insertUserLogin2(User new_user) throws DAOException{
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean result = false;
        
        try {
            
            ps = conn.prepareStatement(QUERY_INSERT_USER_LOGIN2);
            ps.setString(1, new_user.getUsername());
            ps.setString(2, new_user.getPasswordhash());
            
            int rows = ps.executeUpdate();
            
            if(rows == 1){
                result = true;
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
    
    private boolean updateLoginPassword(User new_user){
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean result = false;
        
        try {
            
            ps = conn.prepareStatement(QUERY_UPDATE_USER_PASSWORD);
            ps.setString(1, new_user.getPassword());
            ps.setString(2, new_user.getUsername());
            
            int rows = ps.executeUpdate();
            
            if(rows == 1){
                result = true;
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
    
    private boolean updateUserToRole(User new_user, User old_user) throws DAOException{
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean result = false;
        
        try {
            
            ps = conn.prepareStatement(QUERY_UPDATE_USER_ROLE);
            ps.setString(1, new_user.getUsername());
            ps.setString(2, old_user.getUsername());
            
            int rows = ps.executeUpdate();
            
            if(rows == 1){
                result = true;
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
    
    private boolean updateUser(User new_user, User old_user) throws DAOException{  
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean result = false;
        
        try {
            
            ps = conn.prepareStatement(QUERY_UPDATE_USER);
            ps.setString(1, new_user.getUsername());
            ps.setString(2, new_user.getDescription());
            ps.setString(3, new_user.getTimezone().toString());
            ps.setString(4, new_user.getEmail());
            ps.setString(5, old_user.getUsername());
            
            int rows = ps.executeUpdate();
            
            if(rows == 1){
                result = true;
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
    
    private boolean deleteOldLoginRecord(User old_user) throws DAOException{
        
        Connection conn = daoFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean result = false;
        
        try {
            
            ps = conn.prepareStatement(QUERY_DELETE_USER_LOGIN);
            ps.setString(1, old_user.getUsername());
            
            int rows = ps.executeUpdate();
            
            if(rows == 1){
                result = true;
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
    
}
