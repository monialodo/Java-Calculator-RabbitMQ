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
public class SubtractConsumer {

    private static final Logger logger = LoggerFactory.getLogger(SubtractConsumer.class);

    @RabbitListener(queues = RabbitMQConstants.QUEUE_SUBTRACT)
    public BigDecimal receiveMessage (Operands operands) {
        logger.info("Received message " + operands);
        BigDecimal result = operands.getA().subtract(operands.getB());
        logger.info("Subtraction result " + result);
        return result;

    }

}
