package org.cmpe281.cloud.virtualsensorcontroller.dbhandler;

import com.google.gson.Gson;
import com.mongodb.*;
import org.cmpe281.cloud.enums.MongoCollection;
import org.cmpe281.cloud.enums.SensorState;
import org.cmpe281.cloud.model.VirtualSensor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Yassaman
 */

@RestController
public class VirtualSensorDBOperations {

    private String virtualSensorCollection = MongoCollection.VirtualSensor.toString();
    private MongoClient dbClient;
    private DB database;
    private DBCollection table;

    private static AtomicReference<VirtualSensorDBOperations> instance = new AtomicReference<>();

    public VirtualSensorDBOperations() {
        super();
        try {
            MongoCredential credential = MongoCredential.createCredential(
                    "cmpe281",
                    "cmpe281project",
                    "team4".toCharArray());
            ServerAddress serverAddress = new ServerAddress(
                    "ds023530.mlab.com",
                    23530);

            System.out.println("*** VirtualSensorDBOperations: Getting new instance.......");
            dbClient = new MongoClient(serverAddress, Arrays.asList(credential));
            this.database = dbClient.getDB("cmpe281project");
            this.table = database.getCollection(virtualSensorCollection);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VirtualSensorDBOperations getInstance(){
        return instance.get();
    }

    // TODO: Add a private method to check if a document exists in the collection and then do the check in the public methods below when necessary.

    //insert JSON object format in the database
    public void storeInDB(JSONObject virtualsensorData){

        // should I check if the document exists in the collection first?
        System.out.println("*** VirtualSensorDBOperations: " + virtualsensorData.toString());
        BasicDBObject document = new BasicDBObject();

        try {
            Date timeCreated = new Date(); // timecreated and timeupdated are the same at this point

            // a unique id is generated
            document.put("sensorid", virtualsensorData.get("sensorid"));
            document.put("userid", virtualsensorData.get("userid"));
            document.put("latitude", virtualsensorData.get("latitude"));
            document.put("longitude", virtualsensorData.get("longitude"));
            document.put("timecreated",timeCreated.toString() );
            document.put("timeupdated",timeCreated.toString() );
            document.put("state",SensorState.RUNNING.toString() );

            table.insert( document);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.close();
        }
    }

    public List<VirtualSensor> getVirtualSensorListByUserId(String sensorId, String userId) {

        Gson gson = new Gson();
        List<VirtualSensor> virtualSensorList= new ArrayList<>();

        BasicDBObject dbQuery = new BasicDBObject();
        List<BasicDBObject> query_parameters = new ArrayList<>();
        DBCursor dbCursor = null;

        try{

            query_parameters.add(new BasicDBObject("userid", userId));
            query_parameters.add(new BasicDBObject("sensorid", sensorId));
            dbQuery.put("$and", query_parameters);

            // execute the search query
            dbCursor= table.find(dbQuery);

            while (dbCursor.hasNext()) {
                DBObject obj = dbCursor.next();
                VirtualSensor vs = gson.fromJson(obj.toString(), VirtualSensor.class);
                virtualSensorList.add(vs);
            }
            return virtualSensorList;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            dbClient.close();
        }
    }

    public List<VirtualSensor> groupVirtualSensorListByLatLong(String userId, String lat, String lng) {

        Gson gson = new Gson();
        List<VirtualSensor> virtualSensorList= new ArrayList<>();

        BasicDBObject dbQuery = new BasicDBObject();
        List<BasicDBObject> query_parameters = new ArrayList<>();
        DBCursor dbCursor = null;

        try{

            query_parameters.add(new BasicDBObject("userid", userId));
            query_parameters.add(new BasicDBObject("latitude", lat));
            query_parameters.add(new BasicDBObject("longitude", lng));
            dbQuery.put("$and", query_parameters);

            // execute the search query
            dbCursor= table.find(dbQuery);

            while (dbCursor.hasNext()) {
                DBObject obj = dbCursor.next();
                VirtualSensor vs = gson.fromJson(obj.toString(), VirtualSensor.class);
                virtualSensorList.add(vs);
            }
            return virtualSensorList;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            dbClient.close();
        }
    }

    public List<VirtualSensor> groupVirtualSensorListByTimecreated(String userId, String timecreated) {

        Gson gson = new Gson();
        List<VirtualSensor> virtualSensorList= new ArrayList<>();

        BasicDBObject dbQuery = new BasicDBObject();
        List<BasicDBObject> query_parameters = new ArrayList<>();
        DBCursor dbCursor = null;

        try{

            query_parameters.add(new BasicDBObject("userid", userId));
            query_parameters.add(new BasicDBObject("timecreated", timecreated));
            dbQuery.put("$and", query_parameters);

            // execute the search query
            dbCursor= table.find(dbQuery);

            while (dbCursor.hasNext()) {
                DBObject obj = dbCursor.next();
                VirtualSensor vs = gson.fromJson(obj.toString(), VirtualSensor.class);
                virtualSensorList.add(vs);
            }
            return virtualSensorList;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            dbClient.close();
        }
    }

    // call this method for soft delete in which the state of the virtual sensor is changed to stop
    public void updateVirtualSensorStatus(String userId, String sensorId, String state) {

        try {
            BasicDBObject newDocument = new BasicDBObject();
            newDocument.append("$set", new BasicDBObject().append("state", state));

            BasicDBObject searchQuery = new BasicDBObject().append("userid", userId).append("sensorid", sensorId);

            table.update(searchQuery, newDocument);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            dbClient.close();
        }
    }
}