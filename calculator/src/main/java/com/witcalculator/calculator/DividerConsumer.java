package com.witcalculator.calculator;


import utils.RabbitMQConstants;
import utils.Operands;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;



@Component
@RabbitListener(queues = RabbitMQConstants.QUEUE_DIV)
public class DividerConsumer {

    private static Logger logger = LoggerFactory.getLogger(DividerConsumer.class);


    @RabbitHandler
    public BigDecimal receiveMessage (Operands operands) {
        logger.info("Received message " + operands);
        BigDecimal result = operands.getA().divide(operands.getB(), 3, RoundingMode.HALF_EVEN);
        logger.info("Division result " + result);
        return result;
    }


}
