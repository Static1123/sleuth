package com.yl.gatewayservice.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

/**
 * @author Liang.Yang5
 * @date 2018/9/13 17:44
 */
@Component
public class LoggerFilter extends ZuulFilter {
    @Autowired
    private Tracer tracer;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        tracer.addTag("operator", "static");
        System.out.println("traceId:" + tracer.getCurrentSpan().traceIdString());
        return null;
    }
}