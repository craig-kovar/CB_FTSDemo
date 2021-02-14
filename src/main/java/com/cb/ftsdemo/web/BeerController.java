package com.cb.ftsdemo.web;

import com.cb.ftsdemo.entities.Beer;
import com.cb.ftsdemo.services.BeerServices;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BeerController {

    @Autowired
    BeerServices beerServices;

    @RequestMapping(value = "/beer/helper", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Beer getBeerHelper(@RequestParam("id") String id) {
        return beerServices.getBeerHelper(id);
    }

    @RequestMapping(value = "/beer/deserialize", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Beer getBeerDeserialize(@RequestParam("id") String id) {
        return beerServices.getBeerDeserialize(id);
    }

}
