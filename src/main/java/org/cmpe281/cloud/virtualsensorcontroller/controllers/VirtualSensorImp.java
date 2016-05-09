package org.cmpe281.cloud.virtualsensorcontroller.controllers;

import org.cmpe281.cloud.model.VirtualSensor;
import org.cmpe281.cloud.virtualsensorcontroller.dbhandler.VirtualSensorDBOperations;
import org.cmpe281.cloud.virtualsensorcontroller.integrate.IVirtualSensor;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Yassaman
 */
public class VirtualSensorImp implements IVirtualSensor {

    private VirtualSensorDBOperations vsDBOperations;

    @Override
    public List<VirtualSensor> getSensorMetadata(String sensorId, String userId) {
        return vsDBOperations.getVirtualSensorListByUserId(sensorId, userId);
    }

    @Override
    public List<VirtualSensor> groupVirtualSensorListByLatLong(String userId, String lat, String lng) {
        return vsDBOperations.groupVirtualSensorListByLatLong(userId, lat, lng);
    }

    @Override
    public List<VirtualSensor> groupVirtualSensorListByTimecreated(String userId, String timecreated) {
        return vsDBOperations.groupVirtualSensorListByTimecreated(userId, timecreated);
    }

    @Override
    public void updateVirtualSensorStatus(String userId, String sensorId, String state) {
        vsDBOperations.updateVirtualSensorStatus(userId, sensorId, state);
    }

    @Override
    public void storeInDB(JSONObject vsJsonObject) {
        vsDBOperations.storeInDB(vsJsonObject);
    }
}
