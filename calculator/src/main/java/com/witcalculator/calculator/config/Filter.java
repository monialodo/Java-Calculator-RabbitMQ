package com.witcalculator.calculator.config;

import utils.RabbitMQConstants;
import java.util.UUID;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;


@Component
public class Filter implements MethodInterceptor {

   @Override
    public Object invoke (MethodInvocation methodInvocation) throws Throwable {
        Object [] args = methodInvocation.getArguments();
        Message message = (Message) args [1];
        String requestId =
                (String) message.getMessageProperties().getHeaders().get(RabbitMQConstants.REQUEST_ID);
        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
            message.getMessageProperties().setHeader(RabbitMQConstants.REQUEST_ID, requestId);
        }

        MDC.put(RabbitMQConstants.REQUEST_ID, requestId);
        try {
            return methodInvocation.proceed();
        } finally {
            MDC.remove(RabbitMQConstants.REQUEST_ID);
        }
    }
}
