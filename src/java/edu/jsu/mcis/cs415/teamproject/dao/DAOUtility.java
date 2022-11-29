package edu.jsu.mcis.cs415.teamproject.dao;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Set;

public class DAOUtility {

    public static String getTimeZonesListHTML() {
        
        StringBuilder s = new StringBuilder();
        
        Set<String> zonesSet = ZoneId.getAvailableZoneIds();
        ArrayList<String> zonesList = new ArrayList<>();
        zonesList.addAll(zonesSet);
        zonesList.sort(String.CASE_INSENSITIVE_ORDER);
        
        s.append("<select id=\"timezone\" name=\"timezone\">");
        
        for (String z : zonesList) {
            s.append("<option value=\"").append(z).append("\">").append(z).append("</option>");
        }
        
        s.append("</select>");
        
        return (s.toString().trim());

    }
    
}
