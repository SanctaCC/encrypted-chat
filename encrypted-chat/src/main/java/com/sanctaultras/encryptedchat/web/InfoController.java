package com.sanctaultras.encryptedchat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class InfoController {

    private final ServerProperties serverProperties;
    private final RequestMappingHandlerMapping mappingHandlerMapping;

    public InfoController(ServerProperties serverProperties, RequestMappingHandlerMapping mappingHandlerMapping) {
        this.serverProperties = serverProperties;
        this.mappingHandlerMapping = mappingHandlerMapping;
    }

    @RequestMapping("serverinfo")
    public ResponseEntity info() {
        return ResponseEntity.ok(serverProperties);
    }

    @GetMapping("/mappings")
    public List<String> mappings() {
        return mappingHandlerMapping.getHandlerMethods().keySet().stream().map(p->p.getPatternsCondition().getPatterns())
                .flatMap(p->p.stream()).collect(Collectors.toList());
    }
}
