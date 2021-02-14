package com.cb.ftsdemo.entities;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Brewery {

    @Getter @Setter
    List<String> address;

    @Getter @Setter
    String city;

    @Getter @Setter
    String code;

    @Getter @Setter
    String country;

    @Getter @Setter
    String description;

    @Getter @Setter
    Geo geo;

    @Getter @Setter
    String name;

    @Getter @Setter
    String phone;

    @Getter @Setter
    String state;

    @Getter @Setter
    String type;

    @Getter @Setter
    String updated;

    @Getter @Setter
    String website;

}
