package org.cmpe281.cloud.controller;

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
@RequestMapping(value = "test",
        produces = {"application/xml", "application/json"},
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getHome() {
        return new ResponseEntity("Hello World", HttpStatus.OK);
    }
}
