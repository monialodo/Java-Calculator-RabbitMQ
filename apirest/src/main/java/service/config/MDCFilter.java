package service.config;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import utils.RabbitMQConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class MDCFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal (HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestId = UUID.randomUUID().toString();
        MDC.put(RabbitMQConstants.REQUEST_ID, requestId);

        response.addHeader(RabbitMQConstants.REQUEST_ID, requestId);

        filterChain.doFilter(request, response);

        MDC.remove(RabbitMQConstants.REQUEST_ID);
    }
}