package com.witcalculator.calculator;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import utils.Operands;
import utils.RabbitMQConstants;

import java.math.BigDecimal;

@Component
@RabbitListener(queues = RabbitMQConstants.QUEUE_SUBTRACT)
public class SubtractConsumer {

    @RabbitHandler
    public BigDecimal receiveMessage (Operands operands) {
        return operands.getA().subtract(operands.getB());
    }

}
