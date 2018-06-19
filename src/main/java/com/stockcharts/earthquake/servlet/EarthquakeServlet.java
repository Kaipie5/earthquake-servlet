
package com.stockcharts.earthquake.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;

/**
 *
 * @author kaimcconnell
 */
public class EarthquakeServlet extends HttpServlet {

    private final Logger logger = Logger.getLogger(EarthquakeServlet.class.getName());
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.warn("==================================================");
        logger.warn("           sample-servlet : init()");
        logger.warn("==================================================");
        
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
        JSONObject jo = new JSONObject();
        jo.put("name", "Kai McConnell");
        jo.put("age", 21);
        jo.put("favoriteColor", "green");
        
        try (PrintWriter out = response.getWriter()) {
            
            response.setContentType("application/json");
            
            out.print(jo.toString());
            
        } catch (IOException e) {
            logger.error("ERROR IN DOGET");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        
    }


}

