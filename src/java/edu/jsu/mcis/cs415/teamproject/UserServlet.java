
package edu.jsu.mcis.cs415.teamproject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.jsu.mcis.cs415.teamproject.dao.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.time.ZoneId;
import java.util.*;
import javax.servlet.ServletContext;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAOFactory daoFactory = null;
        
        ServletContext context = request.getServletContext();
 
        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/html; charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()){
       
            int id = Integer.parseInt(request.getParameter("id"));
            
            UserDAO u_dao = daoFactory.getUserDAO();
            out.println(u_dao.find(id));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) { 
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        
        DAOFactory daoFactory = null;
        BufferedReader br = null;
        
        ServletContext context = request.getServletContext();
        
        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json; charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            UserDAO u_dao = daoFactory.getUserDAO();

            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            
            HashMap<String, String> parameters = new HashMap<>();
            
            String[] pairs = p.trim().split("&");
            
            for (int i = 0; i < pairs.length; ++i) {
                String[] pair = pairs[i].split("=");
                parameters.put(pair[0], pair[1]);
            }
            
            //String olduserid = request.getRemoteUser(); //returns user login
            String olduserid = "dbuser"; //test
            String newusername = parameters.get("username");
            String newpassword = parameters.get("password");
            String description = parameters.get("description");
            String timezone = parameters.get("timezone");
            String email = parameters.get("email");
            

            // rest of servlet code goes here
            User oldUserInfo = null;
            User newUserInfo = null;
            
            // fill old data
            Integer id = u_dao.findId(olduserid);
            oldUserInfo = u_dao.find(id);
            
            // fill new data
            HashMap<String, String> newUserParameters = new HashMap<>();
            
            // If user wants to change info but keep password
            if(newpassword == null){
                newUserParameters.put("passwordhash", oldUserInfo.getPasswordhash());
                
                if(newusername != null){
                    newUserParameters.put("username", newusername);
                }
                else{
                    newUserParameters.put("username", oldUserInfo.getUsername());
                }

                if(description != null){
                    newUserParameters.put("description", description);
                }
                else{
                    newUserParameters.put("description", oldUserInfo.getDescription());
                }

                if(timezone != null){
                    newUserParameters.put("timezone", timezone);
                }
                else{
                    newUserParameters.put("timezone", oldUserInfo.getTimezone().toString());
                }

                if(email != null){
                    newUserParameters.put("email", email);
                }
                else{
                    newUserParameters.put("email", oldUserInfo.getEmail());
                }
                
                newUserInfo = new User(newUserParameters);
            }
            // If user wants to change password
            else if(newpassword != null){
                newUserParameters.put("password", newpassword);
                
                if(newusername != null){
                    newUserParameters.put("username", newusername);
                }
                else{
                    newUserParameters.put("username", oldUserInfo.getUsername());
                }

                if(description != null){
                    newUserParameters.put("description", description);
                }
                else{
                    newUserParameters.put("description", oldUserInfo.getDescription());
                }

                if(timezone != null){
                    newUserParameters.put("timezone", timezone);
                }
                else{
                    newUserParameters.put("timezone", oldUserInfo.getTimezone().toString());
                }

                if(email != null){
                    newUserParameters.put("email", email);
                }
                else{
                    newUserParameters.put("email", oldUserInfo.getEmail());
                }
                
                newUserInfo = new User(newUserParameters);
            }
            
            out.println(u_dao.update(newUserInfo, oldUserInfo));
            
        }
        catch (Exception e) { e.printStackTrace(); }
        finally {
            
            if (br != null) {
                try { br.close(); } catch (Exception e) { e.printStackTrace(); }
            }
        }
        
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        DAOFactory daoFactory = null;
        BufferedReader br = null;
        
        ServletContext context = request.getServletContext();
        
        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        }
        else {
            daoFactory = (DAOFactory) context.getAttribute("daoFactory");
        }
        
        response.setContentType("application/json; charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            UserDAO u_dao = daoFactory.getUserDAO();

            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            
            HashMap<String, String> parameters = new HashMap<>();
            
            String[] pairs = p.trim().split("&");
            
            for (int i = 0; i < pairs.length; ++i) {
                String[] pair = pairs[i].split("=");
                parameters.put(pair[0], pair[1]);
            }
            
            // String remoteuser = request.getRemoteUser();
            String remoteuser = parameters.get("username");
            
            Integer id = u_dao.findId(remoteuser);
            User userInfo = u_dao.find(id);
            
            out.println(u_dao.delete(userInfo));
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        finally {
            
        }
        
    }
    
    @Override
    public String getServletInfo() {
        return "User Servlet";
    }

}
