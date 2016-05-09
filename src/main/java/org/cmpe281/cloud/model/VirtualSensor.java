package org.cmpe281.cloud.model;

import org.cmpe281.cloud.enums.SensorState;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by Yassaman
 */
public class VirtualSensor {

    @Id
    private String id;
    private String name;
    private String userId;
    private String latitude;
    private String longitude;
    private String timeCreated;
    private String timeUpdated;
    private SensorState state;
    // For now there's only one type. Add an attribute for type later

    public VirtualSensor() {
        super();
    }

    public VirtualSensor(String id, String name, String userId, String latitude, String longitude) {
        super();

        Date currentDate= new Date();

        this.id= id;
        this.name = name;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeCreated = currentDate.toString();
        this.timeUpdated = currentDate.toString();
        this.state = SensorState.RUNNING;
    }

    public VirtualSensor(String id, String name, String userId, String latitude, String longitude, String timeCreated,
                         String timeUpdated, SensorState state) {
        super();
        this.id= id;
        this.name = name;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(String timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public SensorState getState() {
        return state;
    }

    public void setState(SensorState state) {
        this.state = state;
    }

    public String toString() {

        return "VirtualSensor [ id=" + id + ", name= " + name + ", userId= " + userId + ", latitude= " + latitude +
                ", longitude= " + longitude + ", timeCreated= " + timeCreated + ", timeUpdated= " + timeUpdated +
                ", state= " + state.toString() + "]";
    }
}
