package com.labs.android.lab7.lab7;

import java.io.Serializable;

public class Point implements Serializable {

    private static final long serialVersionUID = 1L;

    private double Latitude;
    private double Longitude;
    private String Name;

    public Point(double latitude, double longitude, String name) {
        Latitude = latitude;
        Longitude = longitude;
        Name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(int latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(int longitude) {
        Longitude = longitude;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
