package org.cmpe281.cloud.virtualsensorcontroller.integrate;

import org.cmpe281.cloud.model.VirtualSensor;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Yassaman
 */
public interface IVirtualSensor {

    void storeInDB (JSONObject vsJsonObject);
    List<VirtualSensor> getSensorMetadata (String sensorId, String userId);
    List<VirtualSensor> groupVirtualSensorListByLatLong(String userId, String lat, String lng);
    List<VirtualSensor> groupVirtualSensorListByTimecreated(String userId, String timecreated);
    void updateVirtualSensorStatus(String userId, String sensorId, String state);
}
