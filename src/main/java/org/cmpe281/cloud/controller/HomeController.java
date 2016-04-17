package org.cmpe281.cloud.controller;

import org.cmpe281.cloud.dbhandler.IBarometerRepository;
import org.cmpe281.cloud.model.BarometerSensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Naks on 15-Apr-16.
 * Home Controller Class
 */

@Controller
@RequestMapping(value = "/",
        produces = {"application/xml", "application/json"},
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

    @Autowired
    IBarometerRepository bRepo;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getHome() {

        BarometerSensor barometerSensor = new BarometerSensor("xyz", "1234", "56789");
        bRepo.save(barometerSensor);
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }
}
