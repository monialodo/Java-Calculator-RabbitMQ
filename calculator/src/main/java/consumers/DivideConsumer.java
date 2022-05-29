package consumers;


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
public class DivideConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DivideConsumer.class);


    @RabbitListener(queues = RabbitMQConstants.QUEUE_DIV)
    public BigDecimal receiveMessage (Operands operands) {
        logger.info("Received message " + operands);
        BigDecimal result = operands.getA().divide(operands.getB(), 3, RoundingMode.HALF_EVEN);
        logger.info("Division result " + result);
        return result;
    }


}
