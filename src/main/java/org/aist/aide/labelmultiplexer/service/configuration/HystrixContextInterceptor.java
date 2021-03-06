package org.aist.aide.labelmultiplexer.service.configuration;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class HystrixContextInterceptor extends HandlerInterceptorAdapter {

    static HystrixRequestContext globalSharedContext;

    static {
        HystrixRequestContext.initializeContext();
        globalSharedContext = HystrixRequestContext.getContextForCurrentThread();

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HystrixRequestContext.setContextOnCurrentThread(globalSharedContext);
        return super.preHandle(request, response, handler);
    }
}
