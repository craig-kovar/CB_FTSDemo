package com.cb.ftsdemo.services;

import com.cb.ftsdemo.entities.Beer;
import com.cb.ftsdemo.entities.Brewery;
import com.cb.ftsdemo.repositories.BeerRepository;
import com.cb.ftsdemo.repositories.BreweryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BreweryServicesImpl implements BreweryServices {

    @Autowired
    BreweryRepository breweryRepository;

    @Override
    public Brewery getBreweryHelper(String id) {
        return breweryRepository.getBreweryHelper(id);
    }

    @Override
    public Brewery getBreweryDeserialize(String id) {
        return breweryRepository.getBreweryDeserialize(id);
    }

    @Override
    public List<Brewery> providedFTSExample(String term) {
        breweryRepository.doProvidedFTSSearch("ohio");
        return null;
    }

    @Override
    public List<Brewery> exampleFTSExample(String idxName, String term, String field, Integer skip, Integer limit, Boolean highlight) {
        breweryRepository.doExampleFTSSearch(idxName, term, field, skip, limit, highlight);
        return null;
    }
}
