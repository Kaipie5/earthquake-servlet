/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stockcharts.earthquake.servlet;

/**
 *
 * @author kaimcconnell
 */
public class Earthquake {
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
    
    public String toString() {
        //System.out.println("[id=" + id + ", " + );
        return null;
    }
}
