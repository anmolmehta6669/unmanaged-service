package com.knoldus.unmanaged.impl;

import akka.NotUsed;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.knoldus.unmanaged.api.ServiceUService;
import com.knoldus.unmanaged.api.UnmanagedExampleService;
import com.knoldus.unmanaged.model.Data;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.pcollections.PSet;
import org.pcollections.*;

import java.util.LinkedHashMap;
import java.util.concurrent.CompletableFuture;

/**
 * Created by knoldus on 18/6/17.
 */
public class UnmanagedExampleServiceImpl implements UnmanagedExampleService {

    private final ServiceUService serviceUService;

    @Inject
    UnmanagedExampleServiceImpl(ServiceUService serviceUService) {
        this.serviceUService = serviceUService;
    }

    @Override
    public ServiceCall<NotUsed, Data> showDataFromUnmanagedService() {
        return request -> {
            String responseString;
            Data data = null;
            ObjectMapper jsonMapper = new ObjectMapper();
            try {
                PMap response = serviceUService.getResultFromUnManagedService().invoke().toCompletableFuture
                        ().get();
                responseString = jsonMapper.writeValueAsString(response);
                data = jsonMapper.readValue(responseString, Data.class);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return CompletableFuture.completedFuture(data);

        };
    }

}
