package com.knoldus.unmanaged.impl;

import com.google.inject.AbstractModule;
import com.knoldus.unmanaged.api.ServiceUService;
import com.knoldus.unmanaged.api.UnmanagedExampleService;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * Created by knoldus on 18/6/17.
 */
public class UnmanagedExampleModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure () {
        bindService(UnmanagedExampleService.class, UnmanagedExampleServiceImpl.class);
        bindClient(ServiceUService.class);
    }
}
