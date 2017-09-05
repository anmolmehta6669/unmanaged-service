package com.knoldus.unmanaged.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.annotation.concurrent.Immutable;
import javax.validation.constraints.NotNull;
import org.pcollections.PVector;

import java.util.Collections;

@Immutable
@JsonDeserialize
@Value
@Builder
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class Data {
    @NotNull
    String value1;
    @NotNull
    int value2;

}
