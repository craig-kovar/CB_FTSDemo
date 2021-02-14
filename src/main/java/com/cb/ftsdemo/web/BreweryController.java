package com.cb.ftsdemo.web;

import com.cb.ftsdemo.entities.Beer;
import com.cb.ftsdemo.entities.Brewery;
import com.cb.ftsdemo.services.BeerServices;
import com.cb.ftsdemo.services.BreweryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class BreweryController {

    @Autowired
    BreweryServices breweryServices;

    @RequestMapping(value = "/brewery/helper", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Brewery getBreweryHelper(@RequestParam("id") String id) {
        return breweryServices.getBreweryHelper(id);
    }

    @RequestMapping(value = "/brewery/deserialize", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Brewery getBreweryDeserialize(@RequestParam("id") String id) {
        return breweryServices.getBreweryDeserialize(id);
    }

    @RequestMapping(value = "/brewery/doProvidedFTS", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Brewery> doProvidedFTS(@RequestParam("term") String term) {
        return breweryServices.providedFTSExample(term);
    }

    @RequestMapping(value = "/brewery/doExampleFTS", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Brewery> doExampleFTS(@RequestParam("idxName") String idxName,
                                      @RequestParam("term") String term,
                                      @RequestParam(value = "field", required = false) String field,
                                      @RequestParam(value = "skip", required = false) Integer skip,
                                      @RequestParam(value = "limit", required = false) Integer limit,
                                      @RequestParam(value = "highlight", required = false) Boolean highlight) {
        return breweryServices.exampleFTSExample(idxName, term, field, skip, limit, highlight);
    }

}
