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
                LinkedHashMap response = serviceUService.getResultFromUnManagedService().invoke().toCompletableFuture
                        ().get();
                responseString = jsonMapper.writeValueAsString(response);
                data = jsonMapper.readValue(responseString, Data.class);
                igniteCache();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            return CompletableFuture.completedFuture(data);
        };
    }
    public void igniteCache() {
        try (Ignite ignite = Ignition.start("example-ignite.xml")) {
            // Put values in cache.
            IgniteCache<Integer, String> cache = ignite.getOrCreateCache("myCache");
            cache.put(1, "Hello");
            cache.put(2, "World!");

            // Get values from cache
            // Broadcast 'Hello World' on all the nodes in the cluster.
            System.out.println(cache.get(1) + " " + cache.get(2));
        }
    }
}
