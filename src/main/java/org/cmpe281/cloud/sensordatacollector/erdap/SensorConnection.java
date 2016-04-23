package org.cmpe281.cloud.sensordatacollector.erdap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Savani
 * 		reading data from cencoos..
 * 
 */

public class SensorConnection {
	
	public JSONArray sensorDataRetrive(String date){
		JSONObject erdapJSON = null;
		try {
			String date1 = "2016-04-22T00:00:00Z";
			String temp = null;
			String url =  "http://erddap.axiomdatascience.com/erddap/tabledap/"
					+ "cencoos_sensor_service.json?time,latitude,longitude,"
					+ "depth,station,unit,value,parameter&time>="+date1+"&parameter=%22"
					+ "Barometric%20Pressure%22";
			URL erdapURL = new URL(url);
			URLConnection erdapURLConnection = erdapURL.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					erdapURLConnection.getInputStream()));
			StringBuilder responseStrBuilder = new StringBuilder();
			while ((temp = br.readLine()) != null)
				responseStrBuilder.append(temp);
				erdapJSON = new JSONObject(responseStrBuilder.toString()) ;				
		}
		catch (Exception e) { 
			throw new ExceptionInInitializerError("Error while reading");			
		} 
		ObjectMapper objMap = new ObjectMapper();
		objMap.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		//	String timeStamp = "{ Time: "+String.valueOf(System.currentTimeMillis())+"}";
		//String db_input  = JSON.stringify(JSON.parse(input)+JSON.parse(timeStamp));
	//	JSONObject newObject = new JSONObject(input) ;
		//check this code based on the call of the client code..  ?????
		JSONArray barometerReadings = (JSONArray)erdapJSON.getJSONObject("table").
				getJSONArray("rows");
		return barometerReadings;
		
	}

}
