package com.knoldus.unmanaged.api;

import akka.NotUsed;
import com.knoldus.unmanaged.model.Data;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.named;

public interface UnmanagedExampleService extends Service {
    ServiceCall<NotUsed, Data> showDataFromUnmanagedService();

    @Override
    default Descriptor descriptor() {
        return named("unmanagedService").withCalls(
                Service.restCall(Method.GET,"/get/data", this::showDataFromUnmanagedService)
        ).withAutoAcl(true);
    }
}
