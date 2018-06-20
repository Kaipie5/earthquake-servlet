/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcharts.earthquake.servlet;

import static com.stockcharts.earthquake.servlet.EarthquakesServlet.DATABASE_URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author kaimcconnell
 */
public class EarthquakeDAO {
    
    private static Logger logger = Logger.getLogger(EarthquakeDAO.class.getName());
    
    public static List<Earthquake> getAllEarthquakes() throws SQLException{
        return getEarthquakes("SELECT * FROM InternDB.Earthquakes");
    }
    
    private static List<Earthquake> getEarthquakes(String query) throws SQLException {
        
        logger.debug("getEarthquakes Called");
        List<Earthquake> earthquakes = new ArrayList<>();
        
        logger.debug("Attempting Connection to Database...");
        try (Connection conn = DriverManager.getConnection(DATABASE_URL); 
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            logger.debug("...Connection To Database Established");
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                String id = rs.getString("id");
                float magnitude = rs.getFloat("magnitude");
                float latitude = rs.getFloat("latitude");
                float longitude = rs.getFloat("longitude");
                long time = rs.getLong("time");
                String place = rs.getString("place");
                Earthquake newQuake = new Earthquake()
                        .withID(id)
                        .withLatitude(latitude)
                        .withLongitude(longitude)
                        .withMagnitude(magnitude)
                        .withPlace(place)
                        .withTime(time);
                earthquakes.add(newQuake);
                logger.debug("Added " + newQuake.getId());
            }
            logger.debug("Finished Adding Quakes");
        }
        logger.debug("Successful Query of Database");
        
        return earthquakes;
    }
}
