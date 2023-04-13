package io.renren.config;

import io.renren.modules.subject.cxf.ZyddService;
import org.apache.cxf.Bus;

import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;


@Configuration
public class CxfConfig {
    @Autowired
    private ZyddService zyddService;

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, zyddService);
        endpoint.publish("/zyddservice");
        return endpoint;
    }
}