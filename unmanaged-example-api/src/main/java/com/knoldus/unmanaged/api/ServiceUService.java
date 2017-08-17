package com.knoldus.unmanaged.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.util.LinkedHashMap;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.transport.Method.GET;

/**
 * Created by knoldus on 19/6/17.
 */
public interface ServiceUService extends Service {
    ServiceCall<NotUsed, LinkedHashMap> getResultFromUnManagedService();

    @Override
    default Descriptor descriptor() {
        return named("serviceU").withCalls(
                Service.restCall(GET, "/key/value/one/two",
                        this::getResultFromUnManagedService)
        ).withAutoAcl(true);
    }
}
