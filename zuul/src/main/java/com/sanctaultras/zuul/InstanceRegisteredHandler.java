package com.sanctaultras.zuul;

import lombok.Getter;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

@Configuration
public class InstanceRegisteredHandler implements ApplicationListener<EurekaInstanceRegisteredEvent> {

    private final RestTemplate template;
    @Getter
    private final Set<String> mappings;

    public InstanceRegisteredHandler(RestTemplate template) {
        this.template = template;
        this.mappings = new CopyOnWriteArraySet<>();
    }

    @Override
    public void onApplicationEvent(EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent) {
        Set<String> set= template.getForEntity
                (eurekaInstanceRegisteredEvent.getInstanceInfo().getHomePageUrl() + "mappings", Set.class).getBody();
        mappings.addAll(set.stream().map(p->"/"+eurekaInstanceRegisteredEvent.getInstanceInfo().getAppName().toLowerCase() + p)
                .collect(Collectors.toSet()));
    }
}
