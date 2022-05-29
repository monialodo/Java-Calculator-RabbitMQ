package service;


import service.errors.DivideByZeroException;
import utils.RabbitMQConstants;
import utils.Operands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CalculatorController {

    @Autowired private RabbitTemplate rabbitTemplate;
    private static final String NAME_DIRECT_EXCHANGE = RabbitMQConstants.NAME_EXCHANGE;
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    @GetMapping(value = "/sum")
    public ResponseEntity<CalculatorResult> sum (@RequestParam ("a") BigDecimal a, @RequestParam ("b") BigDecimal b)
            throws NumberFormatException {
        BigDecimal result = sanitizeAndSendMessage(a, b, RabbitMQConstants.SUM);
        return new ResponseEntity<>(new CalculatorResult(result), HttpStatus.OK);
    }

    @GetMapping("/subtract")
    public ResponseEntity<CalculatorResult> subtract (@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b)
            throws NumberFormatException {
        BigDecimal result = sanitizeAndSendMessage(a, b, RabbitMQConstants.SUBTRACT);
        return new ResponseEntity<>(new CalculatorResult(result), HttpStatus.OK);
    }

    @GetMapping("/multiply")
    public ResponseEntity<CalculatorResult> multiply (@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b)
            throws NumberFormatException {
        BigDecimal result = sanitizeAndSendMessage(a, b, RabbitMQConstants.MULTIPLY);
        return new ResponseEntity<>(new CalculatorResult(result), HttpStatus.OK);
    }

    @GetMapping("/divide")
    public ResponseEntity<CalculatorResult> divide (@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b)
            throws DivideByZeroException, NumberFormatException {

        if (b.equals(BigDecimal.ZERO)) {
            throw new DivideByZeroException ("Impossible to divide by zero");
        }
        BigDecimal result = sanitizeAndSendMessage(a, b, RabbitMQConstants.DIVIDE);
        return new ResponseEntity<>(new CalculatorResult(result), HttpStatus.OK);
    }

    private void setRequestId() {
        rabbitTemplate.setBeforePublishPostProcessors(message -> {
            String requestId = MDC.get(RabbitMQConstants.REQUEST_ID);
            if (requestId != null && !requestId.isEmpty()) {
                message.getMessageProperties().setHeader(RabbitMQConstants.REQUEST_ID, requestId);
            }
            return message;
        });
    }

    private BigDecimal sanitizeAndSendMessage(BigDecimal a, BigDecimal b, String operation)
            throws NumberFormatException {
        logger.info("received payload /" + operation + "?a=" + a + "&b=" + b);
        try {
            Operands operands = new Operands(a, b);
            setRequestId();
            BigDecimal result = (BigDecimal) rabbitTemplate.convertSendAndReceive(NAME_DIRECT_EXCHANGE,
                    operation, operands);
            logger.info("Received result " + result);
            return result;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid operand passed");
        }
    }
}
