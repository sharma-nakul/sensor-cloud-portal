package org.cmpe281.cloud.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Naks on 13-Apr-16.
 * POJO class for Barometer Sensors.
 */


@Document
public class BarometerSensor {

    @Id
    private String id;
    private String time;
    private String latitude;
    private String longitude;
    /*private String depth;
    private String station;
    private String parameter;
    private String unit;
    private String value;
    private String coordinate;*/

    public BarometerSensor(String time, String latitude, String longitude/*, String depth, String station, String parameter, String unit, String value*/) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
       /* this.depth = depth;
        this.station = station;
        this.parameter = parameter;
        this.unit = unit;
        this.value = value;
        this.coordinate=latitude+","+longitude;*/
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
/*
    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }*/
}

