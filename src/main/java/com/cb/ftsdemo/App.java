package com.cb.ftsdemo;

import com.cb.ftsdemo.utils.ConnectionManager;
import com.couchbase.client.java.Cluster;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        Cluster cluster = ConnectionManager.getCluster();
        SpringApplication.run(App.class, args);
    }
}