package com.sanctaultras.zuul;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
@Slf4j
public class MappingZuulFilter extends com.netflix.zuul.ZuulFilter{

    private final InstanceRegisteredHandler handler;

    public MappingZuulFilter(InstanceRegisteredHandler handler) {
        this.handler = handler;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
            HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
            if (!matchExists(handler.getMappings(), request.getRequestURI())) {
                response.sendError(404,"Mapping doesn't exists");
            }
        } catch (IOException e) {
            log.info("{}",e);
        }
        return null;
    }

    private boolean matchExists(Set<String> patterns, String currentUrl) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String pattern : patterns) {
            if (pathMatcher.match(pattern,currentUrl)) {
                return true;
            }
        }
        return false;
    }
}
