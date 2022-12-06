
package edu.jsu.mcis.cs415.teamproject;

import edu.jsu.mcis.cs415.teamproject.dao.DAOFactory;
import edu.jsu.mcis.cs415.teamproject.dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class CreateUserServlet extends HttpServlet {

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        {
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
            
            Integer userid = u_dao.create(u);
            
            // Create Response Data
            
            JSONObject json = new JSONObject();
            json.put("success", (userid != null));
            json.put("userid", userid);
            
            out.println( JSONValue.toJSONString(json) );
            
            //response.sendRedirect("/login.jsp");
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
       
    }

   
    @Override
    public String getServletInfo() {
        return "Create User Servlet";
    }// </editor-fold>

}
