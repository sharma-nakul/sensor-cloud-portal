package org.cmpe281.cloud.sensordatacollector.integrate;

import java.util.ArrayList;

import org.cmpe281.cloud.model.BarometerSensor;
import org.json.JSONArray;
/**
 * @author Savani
 * Interface for sensor collector
 * 
 */
public interface ISensorCollector {
	public JSONArray getSensorData(String latlang);

}
