
package com.stockcharts.earthquake.servlet;

import java.util.*;
import org.apache.log4j.Logger;

/**
 *
 * @author kaimcconnell
 */
public class Earthquake {
    
    private static Logger logger = Logger.getLogger(EarthquakeDAO.class.getName());
    
    private String id;
    private float magnitude;
    private float latitude;
    private float longitude;
    private long time;
    private String place;
    
    public Earthquake() {
        
    }
    public Earthquake withID(String id) {
        this.id = id;
        return this;
    }
    public Earthquake withMagnitude(float magnitude) {
        this.magnitude = magnitude;
        return this;
    }
    public Earthquake withLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }
    public Earthquake withLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }
    public Earthquake withTime(long time) {
        this.time = time;
        return this;
    }
    public Earthquake withPlace(String place) {
        this.place = place;
        return this;
    }

    public String getId() {
        return id;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public long getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }
    
    @Override
    public String toString() {
        //JSONObject jo = new JSONObject()
        String s = "[id=" + id + ", magnitude=" + magnitude + 
                ", latitude=" + latitude + ", longitude=" + longitude + ", time=" + time + ", place=" + place + "]";
        return s;
    }
    
    public static Comparator<Earthquake> Magnitude = new Comparator<Earthquake>(){
        @Override
        public int compare(Earthquake one, Earthquake two) {
            logger.debug("MAGNITUDE COMPARER ENTERED");
            return Float.compare(one.magnitude, two.magnitude);
        }
    };
    public static Comparator<Earthquake> Longitude = new Comparator<Earthquake>(){
        @Override
        public int compare(Earthquake one, Earthquake two) {
            return Float.compare(one.longitude, two.longitude);
        }
    };
    public static Comparator<Earthquake> Time = new Comparator<Earthquake>(){
        @Override
        public int compare(Earthquake one, Earthquake two) {
            logger.debug("TIME COMPARER ENTERED");
            return Float.compare(one.time, two.time);
        }
    };
    public static Comparator<Earthquake> Latitude = new Comparator<Earthquake>(){
        @Override
        public int compare(Earthquake one, Earthquake two) {
            return Float.compare(one.latitude, two.latitude);
        }
    };
    
    
}
