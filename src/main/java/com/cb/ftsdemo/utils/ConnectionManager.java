package com.cb.ftsdemo.utils;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;

import java.time.Duration;

public class ConnectionManager {

    private static final String host = "localhost";
    private static final String bucketName = "beer-sample";
    private static final String userName = "Administrator";
    private static final String password = "password";

    private static Cluster cluster;
    private static Collection collection;

    private ConnectionManager() {
        connectCluster();
    }

    private static void connectCluster() {
        if (cluster == null) {
            cluster = Cluster.connect(host, userName, password);
            Bucket bucket = cluster.bucket(bucketName);
            bucket.waitUntilReady(Duration.ofSeconds(30L));
            collection = bucket.defaultCollection();
        }
    }

    public static Cluster getCluster() {
        if (cluster == null) {
            connectCluster();
        }

        return cluster;
    }

    public static Collection getCollection() {
        if (collection == null) {
            connectCluster();
        }

        return collection;
    }

}
