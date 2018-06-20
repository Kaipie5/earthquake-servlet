package com.stockcharts.earthquake.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONArray;

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
        
        logger.warn("Getting DB DRIVER Class...");
        try {
            Class.forName(DB_DRIVER_CLASS);
        }catch (ClassNotFoundException e){
            logger.fatal("DRIVER CLASS NOT FOUND", e);
            throw new UnavailableException("Servlet Unavailable Failed To Get DB Driver Class");
        }
        logger.warn("...DB Driver Class Found");
        
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
        
        //String query = "SELECT * FROM InternDB.Earthquakes WHERE magnitude > 5";
        //String query = request.getQueryString();
        
        List<Earthquake> earthquakes = new ArrayList<>();
        try {
            earthquakes = EarthquakeDAO.getAllEarthquakes();
        } catch (SQLException exz) {
            logger.error("Earthquakes Get all Earthquakes Error");
        }
        
        String queryVal = request.getParameter("sort");
        
        if (queryVal == null) {
            queryVal = "noSort";
        }
        switch (queryVal) {
            case "time" :
                logger.debug("TIME SORT");
                Collections.sort(earthquakes, Earthquake.Time);
                break;
//            case "magnitude" :
//                logger.debug("MAGNITUDE SORT");
//                Collections.sort(earthquakes, Earthquake.Magnitude);
//                break;
            default :
                logger.debug("NO SORT");
                break;
        }
        
        JSONArray ja = new JSONArray(earthquakes);
        
        
        
        
        logger.debug("Trying to write response to client...");
        try (PrintWriter out = response.getWriter()) {
            
            response.setHeader("Connection", "close");
            response.setContentType("application/json");
            
            //logger.debug("ARRAY WRITTEN: " + ja.toString());
            
            out.print(ja.toString());
            
            out.flush();
            
        } catch (IOException e) {
            logger.error("ERROR Writing Response To Client", e);
        }
        logger.debug("...Response written to client");
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }
    
    

}

