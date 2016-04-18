package org.cmpe281.cloud.controller;

import org.cmpe281.cloud.model.BarometerSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Naks on 15-Apr-16.
 * Home Controller Class
 */

@RestController
@RequestMapping(value = "/",
        produces = {"application/xml", "application/json"},
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

   /* @Autowired
    IBarometerRepository bRepo;*/

    @Autowired
    MongoOperations mongoOperations;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getHome() {
        BarometerSensor bs = new BarometerSensor("2016-03-11T00:00:00Z", "34.0158333333333",
                "-119.359722222222", "0.0", "urn:ioos:station:edu.dri.raws:caCANA", "Barometric Pressure",
                "pascals", "104200.0");
        mongoOperations.insert(bs);
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }
}
