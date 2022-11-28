package edu.jsu.mcis.cs415.teamproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TimezoneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Set<String> zonesSet = ZoneId.getAvailableZoneIds();
        ArrayList<String> zonesList = new ArrayList<>();
        zonesList.addAll(zonesSet);
        zonesList.sort(String.CASE_INSENSITIVE_ORDER);
        
        request.setAttribute("zonesList", zonesList);
        out.print(zonesList.toString());
        
        request.getRequestDispatcher("/createUser.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   
    @Override
    public String getServletInfo() {
        return "Timezone Servlet";
    }// </editor-fold>

}
