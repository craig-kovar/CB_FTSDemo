package com.cb.ftsdemo.services;

import com.cb.ftsdemo.entities.Beer;
import com.cb.ftsdemo.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerServicesImpl implements BeerServices {

    @Autowired
    BeerRepository beerRepository;

    @Override
    public Beer getBeerHelper(String id) {
        return beerRepository.getBeerHelper(id);
    }

    @Override
    public Beer getBeerDeserialize(String id) {
        return beerRepository.getBeerDeserialize(id);
    }
}
