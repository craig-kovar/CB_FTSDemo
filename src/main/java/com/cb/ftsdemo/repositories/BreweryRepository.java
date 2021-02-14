package com.cb.ftsdemo.repositories;

import com.cb.ftsdemo.entities.Beer;
import com.cb.ftsdemo.entities.Brewery;
import com.cb.ftsdemo.utils.ConnectionManager;
import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.core.error.ServerOutOfMemoryException;
import com.couchbase.client.core.error.TimeoutException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.search.HighlightStyle;
import com.couchbase.client.java.search.SearchOptions;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.queries.ConjunctionQuery;
import com.couchbase.client.java.search.queries.MatchQuery;
import com.couchbase.client.java.search.queries.QueryStringQuery;
import com.couchbase.client.java.search.result.SearchResult;
import com.couchbase.client.java.search.result.SearchRow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Component
public class BreweryRepository {

    private Collection collection = ConnectionManager.getCollection();
    private Cluster cluster = ConnectionManager.getCluster();
    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    //private ObjectMapper objectMapper = new ObjectMapper();

    private Brewery deserialize(GetResult result) {
        try {
            JsonObject breweryObject = result.contentAsObject();
            Brewery brewery = objectMapper.readValue(breweryObject.toString(), Brewery.class);
            return brewery;
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Brewery getBreweryDeserialize(String id) {
        try {
            GetResult result = collection.get(id);
            Brewery brewery = deserialize(result);
            return brewery;
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

    public Brewery getBreweryHelper(String id) {
        try {
            GetResult result = collection.get(id);
            Brewery brewery = result.contentAs(Brewery.class);
            return brewery;
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

    public List<String> doProvidedFTSSearch(String term) {

        ConjunctionQuery breweryQuery = SearchQuery.conjuncts(
                SearchQuery.term("brewery").field("type"),
                SearchQuery.queryString(term)
        );

        JsonObject jo = breweryQuery.export();
        System.out.println("Query = " + jo.toString());

        SearchResult results = cluster.searchQuery("clientidx", breweryQuery, SearchOptions.searchOptions().limit(100).highlight(HighlightStyle.ANSI));
        //SearchResult results = cluster.searchQuery("clientidx", breweryQuery);

        System.out.println("Total Rows : " + results.metaData().metrics().totalRows());
        System.out.println("Search Results Rows : " + results.rows().size());

        for (SearchRow row : results.rows()) {
            System.out.println("ID = " + row.id() + " Loc = " + row.locations());
        }

        return null;
    }

    public List<String> doExampleFTSSearch(String idxName, String term, String field, Integer skip, Integer limit, Boolean doHighlight) {

        MatchQuery query = MatchQuery.match(term);
        //QueryStringQuery query = QueryStringQuery.queryString(term);

        SearchOptions searchOptions = SearchOptions.searchOptions();
        if (field != null) {
            System.out.println("Setting fields to - " + field);
            query.field(field);
        }

        if (skip != null) {
            System.out.println("Setting skip to - " + skip);
            searchOptions.skip(skip);
        }

        if (limit != null) {
            System.out.println("Setting limit to - " + limit);
            searchOptions.limit(limit);
        }

        if (doHighlight != null && doHighlight) {
            searchOptions.highlight();
        }

        SearchResult results = cluster.searchQuery(idxName, query, searchOptions);

        System.out.println("Total Rows : " + results.metaData().metrics().totalRows());
        System.out.println("Search Results Rows : " + results.rows().size());
        for (SearchRow row : results.rows()) {
            System.out.println("Row = " + row.toString());
            System.out.println("ID = " + row.id() + " Loc = " + row.locations());
        }

        return null;
    }

}
