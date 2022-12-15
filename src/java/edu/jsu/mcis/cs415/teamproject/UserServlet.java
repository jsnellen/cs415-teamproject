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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        response.setContentType("application/json; charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()){
            
            UserDAO u_dao = daoFactory.getUserDAO();
            
            String username = request.getRemoteUser();
            
            int id = u_dao.findId(username);
            
            // Encode user information in JSON format and return to client
            User userInfo = u_dao.find(id);
            JSONObject json = new JSONObject();
            json.put("success", userInfo != null);
            json.put("username", userInfo.getUsername());
            json.put("passwordhash", userInfo.getPasswordhash());
            json.put("description", userInfo.getDescription());
            json.put("timezone", userInfo.getTimezone().toString());
            json.put("email", userInfo.getEmail());
            out.println(JSONValue.toJSONString(json));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            doGet(request, response);
        } catch (IOException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                if(pair.length > 1){
                    parameters.put(pair[0], pair[1]);
                }
                else{
                    parameters.put(pair[0], "");
                }
            }
            
            String olduserid = request.getRemoteUser(); //returns user login
            
            if(olduserid == null){
                olduserid = parameters.get("remoteuser");
            }
            
            String newusername = parameters.get("username");
            String newpassword = parameters.get("password");
            String newdescription = parameters.get("description");
            String newtimezone = parameters.get("timezone");
            String newemail = parameters.get("email");
            

            // rest of servlet code goes here
            User oldUserInfo = null;
            User newUserInfo = null;
            
            // fill old data
            Integer id = u_dao.findId(olduserid);
            oldUserInfo = u_dao.find(id);
            
            String oldusername = oldUserInfo.getUsername();
            String oldpasswordhash = oldUserInfo.getPasswordhash();
            String olddescription = oldUserInfo.getDescription();
            String oldtimezone = oldUserInfo.getTimezone().toString();
            String oldemail = oldUserInfo.getEmail();
            
            // fill new data
            HashMap<String, String> newUserParameters = new HashMap<>();
            
            // If user wants to change info but keep password
            if(newpassword == null || "".equals(newpassword.trim())){
                newUserParameters.put("passwordhash", oldpasswordhash);
                
                if(newusername != null){
                    newUserParameters.put("username", newusername);
                }
                else{
                    newUserParameters.put("username", oldusername);
                }

                if(newdescription != null){
                    newUserParameters.put("description", newdescription);
                }
                else{
                    newUserParameters.put("description", olddescription);
                }

                if(newtimezone != null){
                    newUserParameters.put("timezone", newtimezone);
                }
                else{
                    newUserParameters.put("timezone", oldtimezone);
                }

                if(newemail != null){
                    newUserParameters.put("email", newemail);
                }
                else{
                    newUserParameters.put("email", oldemail);
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
                    newUserParameters.put("username", oldusername);
                }

                if(newdescription != null){
                    newUserParameters.put("description", newdescription);
                }
                else{
                    newUserParameters.put("description", olddescription);
                }

                if(newtimezone != null){
                    newUserParameters.put("timezone", newtimezone);
                }
                else{
                    newUserParameters.put("timezone", oldtimezone);
                }

                if(newemail != null){
                    newUserParameters.put("email", newemail);
                }
                else{
                    newUserParameters.put("email", oldemail);
                }
                
                newUserInfo = new User(newUserParameters);
            }
            
            JSONObject json = new JSONObject();
            json.put("success", u_dao.update(newUserInfo, oldUserInfo));
            out.println(JSONValue.toJSONString(json));
            
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
            
            if(br.readLine() != null){
                String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            
                HashMap<String, String> parameters = new HashMap<>();

                String[] pairs = p.trim().split("&");

                for (int i = 0; i < pairs.length; ++i) {
                    String[] pair = pairs[i].split("=");
                    parameters.put(pair[0], pair[1]);
                }
            }
            
            String remoteuser = request.getRemoteUser();
            
            Integer id = u_dao.findId(remoteuser);
            User userInfo = u_dao.find(id);
            
            JSONObject json = new JSONObject();
            json.put("success", u_dao.delete(userInfo));
            out.println(JSONValue.toJSONString(json));
            
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
