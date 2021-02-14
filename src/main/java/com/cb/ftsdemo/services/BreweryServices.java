package com.cb.ftsdemo.services;

import com.cb.ftsdemo.entities.Beer;
import com.cb.ftsdemo.entities.Brewery;

import java.util.List;

public interface BreweryServices {

    public Brewery getBreweryHelper(String id);
    public Brewery getBreweryDeserialize(String id);

    public List<Brewery> providedFTSExample(String term);

    public List<Brewery> exampleFTSExample(String idxName, String term, String field, Integer skip, Integer limit, Boolean highlight);

}
