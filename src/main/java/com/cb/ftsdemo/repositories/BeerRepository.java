package com.cb.ftsdemo.repositories;

import com.cb.ftsdemo.entities.Beer;
import com.cb.ftsdemo.utils.ConnectionManager;
import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.core.error.DecodingFailureException;
import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.core.error.TimeoutException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Component
public class BeerRepository {

    private Collection collection = ConnectionManager.getCollection();
    private Cluster cluster = ConnectionManager.getCluster();
    //private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private ObjectMapper objectMapper = new ObjectMapper();

    private Beer deserialize(GetResult result) {
        try {
            JsonObject beerObject = result.contentAsObject();
            Beer beer = objectMapper.readValue(beerObject.toString(), Beer.class);
            return beer;
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Beer getBeerDeserialize(String id) {
        try {
            GetResult result = collection.get(id);
            Beer beer = deserialize(result);
            return beer;
        } catch (DocumentNotFoundException de) {
            System.out.println("Document does not exist");
        } catch (TimeoutException te) {
            System.out.println("Timed out retrieving doc : " + id);
            te.printStackTrace();
        } catch (CouchbaseException ce) {
            System.out.println("Couchbase Exception detected");
            ce.printStackTrace();
        }

        return null;
    }

    public Beer getBeerHelper(String id) {
        try {
            GetResult result = collection.get(id);
            Beer beer = result.contentAs(Beer.class);
            return beer;
        } catch (DocumentNotFoundException de) {
            System.out.println("Document does not exist");
        } catch (TimeoutException te) {
            System.out.println("Timed out retrieving doc : " + id);
            te.printStackTrace();
        } catch (CouchbaseException ce) {
            System.out.println("Couchbase Exception detected");
            ce.printStackTrace();
        }

        return null;
    }

}
