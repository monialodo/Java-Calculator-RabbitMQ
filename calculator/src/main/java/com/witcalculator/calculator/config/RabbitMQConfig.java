package com.witcalculator.calculator.config;

import utils.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin rabbitAdmin() {
        RabbitAdmin rabbitAdmin= new RabbitAdmin(connectionFactory());
        rabbitAdmin.declareExchange(directExchange());
        return rabbitAdmin;
    }

    @Bean
    Queue sumQueue () {
        return new Queue(RabbitMQConstants.QUEUE_SUM, false);
    }

    @Bean
    Queue subtractQueue () {
        return new Queue(RabbitMQConstants.QUEUE_SUBTRACT, false);
    }

    @Bean
    Queue multiplyQueue () {
        return new Queue(RabbitMQConstants.QUEUE_MULT, false);
    }

    @Bean
    Queue divideQueue () {
        return new Queue(RabbitMQConstants.QUEUE_DIV, false);
    }

    @Bean
    DirectExchange directExchange () {
        return new DirectExchange(RabbitMQConstants.NAME_EXCHANGE);
    }


    @Bean
    Binding sumBinding (Queue sumQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(sumQueue).to(directExchange).with(RabbitMQConstants.SUM);
    }

    @Bean
    Binding subtractBinding (Queue subtractQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(subtractQueue).to(directExchange).with(RabbitMQConstants.SUBTRACT);
    }

    @Bean
    Binding multiplyBinding (Queue multiplyQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(multiplyQueue)  .to(directExchange).with(RabbitMQConstants.MULTIPLY);
    }

    @Bean
    Binding divideBinding (Queue divideQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(divideQueue).to(directExchange).with(RabbitMQConstants.DIVIDE);
    }

    @Bean RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(new MessageConverter());
        return rabbitTemplate;
    }


    @Bean(name = "rabbitListenerContainerFactory")
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory());
        factory.setAdviceChain(new Filter());
        return factory;
    }




}
