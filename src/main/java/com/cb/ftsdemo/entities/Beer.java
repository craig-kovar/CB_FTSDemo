package com.cb.ftsdemo.entities;

//import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Beer {

    @Getter @Setter
    Double abv;

    @Getter @Setter
    String brewery_id;

    @Getter @Setter
    String category;

    @Getter @Setter
    String description;

    @Getter @Setter
    Double ibu;

    @Getter @Setter
    String name;

    @Getter @Setter
    Double srm;

    @Getter @Setter
    String style;

    @Getter @Setter
    String type;

    @Getter @Setter
    Double upc;

    @Getter @Setter
    String updated;

}
