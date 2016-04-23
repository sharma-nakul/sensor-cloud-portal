package org.cmpe281.cloud.sensordatacollector.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.cmpe281.cloud.enums.MongoCollection;
import org.cmpe281.cloud.model.BarometerSensor;
import org.cmpe281.cloud.sensordatacollector.dbhandler.SensorDBOperations;
import org.cmpe281.cloud.sensordatacollector.erdap.SensorConnection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * @author Savani
 * service for updating sensor data in mongodb by reading from cencoos
 * 
 */
@RestController
@RequestMapping(value = "/update", 
produces = MediaType.TEXT_PLAIN_VALUE,
consumes = MediaType.APPLICATION_JSON_VALUE)
public class SensorDataCollectController {
	@Autowired
	MongoOperations mongoOperations;
	Gson gson = new Gson();
	SensorConnection senConnect = new SensorConnection();
	String sensorCollection = MongoCollection.VirtualSensor.toString();

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity setData(String date) throws JsonParseException, JsonMappingException, IOException 
	{
		try {
			JSONArray barometerReadings = senConnect.sensorDataRetrive(date); //retrive data from cencoos
			createJSONObject(barometerReadings); //create and save in DB
		}catch(Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}    
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET)
	public String getData(){
		return "Hello World";
	}

	public String createJSONObject(JSONArray barometerReadings){
		String temp = null;
		String[] columns;
		ArrayList<BarometerSensor> readings = new ArrayList<BarometerSensor>();
		temp = barometerReadings.get(0).toString();
		for(int i = 0; i<2; i++){
			temp = temp.replace("[", "").replace("]", "");
			columns = temp.split(",");
			BarometerSensor bs = new BarometerSensor(columns[0],columns[1], 
					columns[2], columns[3],columns[4], columns[7], columns[5], columns[6]);
			readings.add(bs);
		}
		String json = gson.toJson(readings);
		System.out.println(json);
		//
		//		b1 = mongoOperations.findOne(new Query(Criteria.where("time").
		//				is("2016-03-23T00:00:00Z")), BarometerSensor.class, sensorCollection);
		//	
		//		
		SensorDBOperations sdo = null;
		try{
		if(SensorDBOperations.getInstance()!=null) 
			sdo = SensorDBOperations.getInstance();
		else sdo = new SensorDBOperations(); //get DB connection
		sdo.updateDB(json);  //update DB
		sdo.closeConnection(); //close connection
		}catch(Exception e){
			throw new ExceptionInInitializerError("DB Error"+e);
		}
		return json;

	}

}