package org.cmpe281.cloud.sensordatacollector.controllers;

import java.net.UnknownHostException;

import javax.print.attribute.standard.Media;

import org.cmpe281.cloud.sensordatacollector.dbhandler.SensorDBOperations;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
/**
 * @author Savani
 * service for fetching sensor data based on different parameters
 * 
 */

@RestController
@RequestMapping(value = "/fetch", 
produces = MediaType.APPLICATION_JSON_VALUE,
consumes = MediaType.TEXT_PLAIN_VALUE)
public class SensorDataFetchController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity fetchData(String input){
		System.out.println("Input of fetch...."+input);
		ObjectMapper objMap = new ObjectMapper();
		objMap.setVisibility(PropertyAccessor.ALL, Visibility.ANY);
		SensorDBOperations sdo ;
		JSONArray sensorReadings = new JSONArray();
		try{
			if(SensorDBOperations.getInstance()!=null) 
				sdo = SensorDBOperations.getInstance();
			else sdo = new SensorDBOperations();  //get DB connection	
			if(input == null){
				sensorReadings = sdo.searchAll(); //retrieve all results
			}
			else{
				String[] latlangArr = input.split(",");
				sensorReadings = sdo.search(latlangArr [0], latlangArr [1]); //search on the basis of latitude & longitude
			}
			sdo.closeConnection();  //close DB
		} 
		catch(Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);		
		}
		return new ResponseEntity<>(sensorReadings.toString(), HttpStatus.OK);
	}


}
