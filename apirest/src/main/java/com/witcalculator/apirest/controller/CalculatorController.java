package com.witcalculator.apirest.controller;

import com.witcalculator.apirest.service.CalculatorResult;
import org.slf4j.MDC;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.Operands;
import utils.RabbitMQConstants;


import java.math.BigDecimal;



@RestController
@RequestMapping
public class CalculatorController {

    private final RabbitTemplate rabbitTemplate;

    private static final String NAME_DIRECT_EXCHANGE = RabbitMQConstants.NAME_EXCHANGE;

    public CalculatorController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @GetMapping(value = "/sum")
    public ResponseEntity<CalculatorResult> sum (@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b)
            throws NumberFormatException {
        BigDecimal result = sanitizeAndSendMessage(a, b, RabbitMQConstants.SUM);
        return new ResponseEntity<>(new CalculatorResult(result), HttpStatus.OK);
    }



    @GetMapping("/substract")
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
            throws NumberFormatException {

        if (b.equals(BigDecimal.ZERO)) {
            throw new Error("Impossible to divide by zero");
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

    private BigDecimal sanitizeAndSendMessage (BigDecimal a, BigDecimal b, String operation)
            throws NumberFormatException {
        try {
            Operands operands = new Operands(a, b);
            setRequestId();
            return (BigDecimal) rabbitTemplate.convertSendAndReceive(NAME_DIRECT_EXCHANGE, operation, operands);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid operand");
        }
    }









}
