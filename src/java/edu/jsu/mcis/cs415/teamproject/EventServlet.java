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
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import javax.servlet.ServletContext;

public class EventServlet extends HttpServlet {

    
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
        
        try (PrintWriter out = response.getWriter()) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            EventDAO e_dao = daoFactory.getEventDAO();
            out.println(e_dao.find(id));
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
        
        response.setContentType("application/json; charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            EventDAO e_dao = daoFactory.getEventDAO();
            HashMap<String, String> params = new HashMap<>();
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            // get parameters from client
            String description = request.getParameter("description");
            String minute = request.getParameter("minute");
            String hour = request.getParameter("hour");
            String day_of_month = request.getParameter("day_of_month");
            String month_of_year = request.getParameter("month_of_year");
            String day_of_week = request.getParameter("day_of_week");
            String year = request.getParameter("year");
            Long duration  = Long.parseLong(request.getParameter("duration"));
            ZonedDateTime active = ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneId.of("UTC")); // default
            ZonedDateTime inactive = ZonedDateTime.of(2022, 11, 30, 23, 59, 59, 0, ZoneId.of("UTC")); // default
            
            // fill HashMap with parameters
            params.put("description", description);
            params.put("minute", minute);
            params.put("hour", hour);
            params.put("day_of_month", day_of_month);
            params.put("month_of_year", month_of_year);
            params.put("day_of_week", day_of_week);
            params.put("year", year);
            params.put("duration", Long.toString(duration));
            params.put("utc_active", dtf.format(active));
            params.put("utc_inactive", dtf.format(inactive));
            
            // fill Event object
            Event e1 = new Event(params);
            
            // call create() EventDAO method
            out.println(e_dao.create(e1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPut2(HttpServletRequest request, HttpServletResponse response) {

        BufferedReader br = null;
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String p = URLDecoder.decode(br.readLine().trim(), Charset.defaultCharset());
            HashMap<String, String> parameters = new HashMap<>();
            String[] pairs = p.trim().split("&");
            for (int i = 0; i < pairs.length; ++i) {
                String[] pair = pairs[i].split("=");
                if (pair.length > 1) {
                    parameters.put(pair[0], pair[1]);
                }
                else {
                    parameters.put(pair[0], null);
                }
            }
            //String name = parameters.get("name");
            //int id = Integer.parseInt(parameters.get("id"));

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
            
            EventDAO e_dao = daoFactory.getEventDAO();
            HashMap<String, String> params = new HashMap<>();
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            // get parameters from client
            int id = Integer.parseInt(request.getParameter("id"));
            String description = request.getParameter("description");
            String minute = request.getParameter("minute");
            String hour = request.getParameter("hour");
            String day_of_month = request.getParameter("day_of_month");
            String month_of_year = request.getParameter("month_of_year");
            String day_of_week = request.getParameter("day_of_week");
            String year = request.getParameter("year");
            Long duration  = Long.parseLong(request.getParameter("duration"));
            ZonedDateTime active = ZonedDateTime.of(2022, 11, 1, 0, 0, 0, 0, ZoneId.of("UTC")); // default
            ZonedDateTime inactive = ZonedDateTime.of(2022, 11, 30, 23, 59, 59, 0, ZoneId.of("UTC")); // default
            
            // find event
            Event e = e_dao.find(id);
            
            // update event attributes
            e.setDescription(description);
            e.setMinute(minute);
            e.setHour(hour);
            e.setDayOfMonth(day_of_month);
            e.setMonthOfYear(month_of_year);
            e.setDayOfWeek(day_of_week);
            e.setYear(year);
            e.setDuration(duration);
            e.setUtcActive(active);
            e.setUtcInactive(inactive);
            
            // call EventDAO update method
            out.println(e_dao.update(e));
        }
        catch (Exception e) {
            e.printStackTrace();
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
            int id = Integer.parseInt(request.getParameter("id"));
            
            EventDAO e_dao = daoFactory.getEventDAO();
            out.println(e_dao.delete(id));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

   
    @Override
    public String getServletInfo() {
        return "Event Servlet";
    }// </editor-fold>

}
