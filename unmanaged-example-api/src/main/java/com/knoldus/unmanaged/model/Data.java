package com.knoldus.unmanaged.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.concurrent.Immutable;

@Immutable
@JsonDeserialize
public class Data {
    String value1;
    String value2;

    @JsonCreator
    Data(@JsonProperty("one") String key1, @JsonProperty("key")String key2) {
        this.value1 = key1;
        this.value2 = key2;
    }
}
