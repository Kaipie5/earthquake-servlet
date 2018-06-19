package com.stockcharts.earthquake.servlet;

import static com.stockcharts.earthquake.servlet.Main.DATABASE_URL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author kaimcconnell
 */
public class EarthquakesServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(EarthquakesServlet.class.getName());
    
    public static final String DATABASE_URL = "jdbc:mariadb:aurora://scc-intern-db.couiu6erjuou.us-east-1.rds.amazonaws.com:3306/InternDB?user=intern&password=stockcharts2018&trustServerCertificate=true&connectTimeout=5000";
    
    private static final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.warn("==================================================");
        logger.warn("           sample-servlet : init()");
        logger.warn("==================================================");
        
        
        try {
            Class.forName(DB_DRIVER_CLASS);
        }catch (ClassNotFoundException e){
            logger.fatal("DRIVER CLASS NOT FOUND");
            e.printStackTrace();
            return;
        }
        logger.warn("==================================================");
        logger.warn("       sample-servlet : init() - COMPLETE");
        logger.warn("==================================================");
    }
    
    @Override
    public void destroy() {
        logger.warn("<<<<<<<<<<<<<<<<<<<< ######## >>>>>>>>>>>>>>>>>>>>");
        logger.warn("           sample-servlet : destroy()");
        logger.warn("<<<<<<<<<<<<<<<<<<<< ######## >>>>>>>>>>>>>>>>>>>>");
        
        logger.warn("<<<<<<<<<<<<<<<<<<<< ######## >>>>>>>>>>>>>>>>>>>>");
        logger.warn("       sample-servlet : destroy() - COMPLETE");
        logger.warn("<<<<<<<<<<<<<<<<<<<< ######## >>>>>>>>>>>>>>>>>>>>");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        
        String query = "SELECT * FROM InternDB.Earthquakes WHERE magnitude > 5";
        List<Earthquake> earthquakes = getEarthquakes(query);
        JSONArray ja = new JSONArray();
        for (Earthquake earthquake : earthquakes) {
            ja.put(new JSONObject(earthquake));
        }  
        logger.info(ja.toString());
        try (PrintWriter out = response.getWriter()) {
            
            response.setContentType("application/json");
            
            out.print(ja.toString());
            
            out.flush();
            
        } catch (IOException e) {
            logger.error("ERROR IN DOGET");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }
    
    private List<Earthquake> getEarthquakes(String query) {
        
        logger.info("getEarthquakes Called");
        List<Earthquake> earthquakes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE_URL); 
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
            logger.info("Connection To Database Established");
            
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
                logger.info("Added " + newQuake.getId().toString());
            }
        } catch (SQLException e) {
            logger.info("ERROR querying database: " + e);
        }
        return earthquakes;
    }

}

