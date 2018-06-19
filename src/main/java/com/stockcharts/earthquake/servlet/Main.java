/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcharts.earthquake.servlet;

import java.sql.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author kaimcconnell
 */
public class Main {
    public static final String DATABASE_URL = "jdbc:mariadb:aurora://scc-intern-db"
            + ".couiu6erjuou.us-east-1.rds.amazonaws.com:3306/InternDB?user=intern"
            + "&password=stockcharts2018&trustServerCertificate=true&connectTimeout=5000";
    private static final String DB_DRIVER_CLASS = "org.mariadb.jdbc.Driver";
    public static void main(String[] args) {
        try {
            Class.forName(DB_DRIVER_CLASS);
        }catch (ClassNotFoundException e){
            System.out.println("DRIVER CLASS NOT FOUND");
            e.printStackTrace();
            return;
        }
        
        List<Earthquake> earthquakes = getEarthquakes();
        for (Earthquake earthquake : earthquakes) {
            JSONArray ja = new JSONArray();
            ja.put(new JSONObject(earthquake));
            System.out.println(earthquake);
        }
    }
    private static List<Earthquake> getEarthquakes() {
        String query = "SELECT * FROM InternDB.Earthquakes WHERE magnitude > 5";
        List<Earthquake> earthquakes = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DATABASE_URL); 
            PreparedStatement stmt = conn.prepareStatement(query)) {
            
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
            }
        } catch (SQLException e) {
            System.out.println("ERROR querying database: " + e);
        }
        return earthquakes;
    }
}
