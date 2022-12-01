
package edu.jsu.mcis.cs415.teamproject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.jsu.mcis.cs415.teamproject.dao.*;
import java.time.ZoneId;
import java.util.*;
import javax.servlet.ServletContext;


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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        
        try (PrintWriter out = response.getWriter()) {
            
            UserDAO u_dao = daoFactory.getUserDAO();
            HashMap<String, String> params = new HashMap<>();
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String description = request.getParameter("description");
            String email = request.getParameter("email");
            String timezone = request.getParameter("timezone");
            
            //fill up the user hashmap
            
            params.put("username", username);
            params.put("password", password);
            params.put("description", description);
            params.put("email", email);
            params.put("timezone", timezone);
            
            //fill up the user
            User u = new User(params);
            
            System.err.println("New User:\n" + u.toString());
            
            out.println(u_dao.create(u));
            //response.sendRedirect("/login.jsp");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
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
