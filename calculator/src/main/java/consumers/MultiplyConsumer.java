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
public class MultiplyConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MultiplyConsumer.class);


    @RabbitListener(queues = RabbitMQConstants.QUEUE_MULT)
    public BigDecimal receiveMessage (Operands operands) {
        logger.info("Received message " + operands);
        BigDecimal result = operands.getA().multiply(operands.getB());
        logger.info("Multiplication result " + result);
        return result;
    }
}
