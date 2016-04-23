package org.cmpe281.cloud.sensordatacollector.dbhandler;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.annotation.Resource;

import org.bson.BSON;
import org.cmpe281.cloud.enums.MongoCollection;
import org.cmpe281.cloud.model.BarometerSensor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.data.mongodb.UncategorizedMongoDbException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;
import org.bson.Document;

/**
 * @author Savani
 * class for handling database operations..
 * 
 */
@RestController
public class SensorDBOperations {
	String sensorCollection = MongoCollection.VirtualSensor.toString();
	MongoClient dbClient;
	DB database;
	DBCollection table;
	private static AtomicReference<SensorDBOperations> instance = new AtomicReference<>();

	public SensorDBOperations() {
		try {
			MongoCredential credential = MongoCredential.createCredential(
					"cmpe281",
					"cmpe281project",
					"team4".toCharArray());
			ServerAddress serverAddress = new ServerAddress(
					"ds023530.mlab.com",
					23530);

			System.out.println("Getting new instance.......");
			dbClient = new MongoClient(serverAddress, Arrays.asList(credential));//new MongoClient("localhost", 27017)
			this.database = dbClient.getDB("cmpe281project");
			this.table = database.getCollection(MongoCollection.VirtualSensor.toString());//"SensorData11");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static SensorDBOperations getInstance(){
		return instance.get();

	}

	//insert array in JSON format in the database
	public void updateDB(String barometerReadings){
		JSONArray barometerReadingsArr = new JSONArray(barometerReadings);
		DBObject document = new BasicDBObject();
		Object o;
		System.out.println("above loop savvy");
		try{
			for(int i = 0; i < barometerReadingsArr.length(); i++){
				System.out.println(barometerReadingsArr.get(i).toString()+"savvy");
				o = com.mongodb.util.JSON.parse(barometerReadingsArr.get(i).toString());
				document = (DBObject) o;//JSON.parse(barometerReadingsArr.getString(i));
				table.insert(document);
			}} catch(Exception e){
				throw new ExceptionInInitializerError("Error while database writing... "+e);			
			}

	}

	//search based on latitude and longitude
	public JSONArray search(String latitude, String longitude){
		JSONArray sensorReadings  = new JSONArray();
		BasicDBObject nQuery = new BasicDBObject();
		List<BasicDBObject> ls_srch = new ArrayList<BasicDBObject>();
		DBCursor cursor1 = null;
		try{
			ls_srch.add(new BasicDBObject("latitude", "40.9833333333333"));
			ls_srch.add(new BasicDBObject("longitude", "-124.1"));
			nQuery.put("$and", ls_srch);
			cursor1 = table.find(nQuery);
			while(cursor1.hasNext()){
				sensorReadings.put(JSON.serialize(cursor1.next()));
			}} catch(Exception e){
				System.out.println(e);
			}
		return sensorReadings;
	}

	// retrieve all records
	public JSONArray searchAll(){
		JSONArray sensorReadings  = new JSONArray();
		DBCursor cursor =  table.find();
		System.out.println("Total  :"+cursor.count());
		while(cursor.hasNext()){
			sensorReadings.put(JSON.serialize(cursor.next()));
		}
		return sensorReadings;
	}
	public void closeConnection(){
		dbClient.close();
	}
}
