package org.cmpe281.cloud.sensordatacollector.controllers;

import java.util.ArrayList;
import java.util.List;

import org.cmpe281.cloud.model.BarometerSensor;
import org.cmpe281.cloud.sensordatacollector.dbhandler.SensorDBOperations;
import org.cmpe281.cloud.sensordatacollector.integrate.ISensorCollector;
import org.json.JSONArray;
import org.json.JSONString;

import com.google.gson.Gson;
/**
 * @author Savani
 * implementation for IsensorCollector..
 * 
 */

public class SensorCollectorImp implements ISensorCollector {

	@Override
	public JSONArray getSensorData(String latlang) {
		ArrayList<BarometerSensor> readingsList = new ArrayList<>();
		SensorDBOperations sdo = new SensorDBOperations();
		String[] input = latlang.split(",");
		JSONArray sensorReadings = new JSONArray();
		sensorReadings = sdo.search(input[0], input[1]);
//		Gson gson = new Gson();
//		for(int i= 0; i < sensorReadings.length(); i++){
//			readingsList = gson.fromJson(sensorReadings.getString(i), BarometerSensor.class);
//
//		}

		return sensorReadings;
	}

}
