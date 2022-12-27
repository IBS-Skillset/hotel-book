package com.happystays.book.cmd.filter;

import com.happystays.book.cmd.config.RequestContext;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.happystays.book.common.utils.Constants.TOKEN;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest) req).getHeader(HttpHeaders.AUTHORIZATION);
        MDC.put(TOKEN,((HttpServletRequest) req).getHeader(HttpHeaders.AUTHORIZATION).substring("Bearer ".length()));
        RequestContext.getContext().setToken(token);
        chain.doFilter(req, res);
        MDC.remove(TOKEN);
    }
}