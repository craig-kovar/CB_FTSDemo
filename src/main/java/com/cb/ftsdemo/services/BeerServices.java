package com.cb.ftsdemo.services;

import com.cb.ftsdemo.entities.Beer;

public interface BeerServices {

    public Beer getBeerHelper(String id);
    public Beer getBeerDeserialize(String id);

}
