package consumers;

import utils.RabbitMQConstants;
import utils.Operands;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SumConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SumConsumer.class);


    @RabbitListener(queues = RabbitMQConstants.QUEUE_SUM)
    public BigDecimal receiveMessage (Operands operands) {
        logger.info("Received message " + operands);
        BigDecimal result = operands.getA().add(operands.getB());
        logger.info("Sum result " + result);
        return result;
    }
}
