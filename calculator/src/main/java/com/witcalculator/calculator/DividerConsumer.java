package com.witcalculator.calculator;



import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import utils.Operands;
import utils.RabbitMQConstants;


import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RabbitListener(queues = RabbitMQConstants.QUEUE_DIV)
public class DividerConsumer {

    @RabbitHandler
    public BigDecimal receiveMessage (Operands operands) {
        return operands.getA().divide(operands.getB(), RoundingMode.HALF_EVEN);
    }


}
