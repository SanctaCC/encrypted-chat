package com.sanctaultras.zuul;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class AfterDiscovery implements ApplicationListener<EurekaInstanceRegisteredEvent> {

    private final RestTemplate template;
    private final Set<String> mappings;

    public AfterDiscovery(RestTemplate template) {
        this.template = template;
        this.mappings = new HashSet<>();
    }

    @Override
    public void onApplicationEvent(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent) {
        mappings.addAll(template.getForEntity
                (eurekaInstanceRegisteredEvent.getInstanceInfo().getHomePageUrl() + "mappings", Set.class).getBody());
    }
}
