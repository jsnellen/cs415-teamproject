
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

            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            
            HashMap<String, String> parameters = new HashMap<>();
            
            String[] pairs = p.trim().split("&");
            
            for (int i = 0; i < pairs.length; ++i) {
                String[] pair = pairs[i].split("=");
                parameters.put(pair[0], pair[1]);
            }
            
            String olduserid = request.getRemoteUser();

            // rest of servlet code goes here
            
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
