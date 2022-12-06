
package edu.jsu.mcis.cs415.teamproject;

import edu.jsu.mcis.cs415.teamproject.dao.DAOFactory;
import edu.jsu.mcis.cs415.teamproject.dao.UserDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.Charset;
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
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {

        DAOFactory daoFactory = null;
        BufferedReader br = null;

        ServletContext context = request.getServletContext();

        if (context.getAttribute("daoFactory") == null) {
            System.err.println("*** Creating new DAOFactory ...");
            daoFactory = new DAOFactory();
            context.setAttribute("daoFactory", daoFactory);
        } else {
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
        }
        catch (Exception e) {
            e.printStackTrace();
        } 
        finally {

            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
   
    @Override
    public String getServletInfo() {
        return "Create User Servlet";
    }// </editor-fold>

}
