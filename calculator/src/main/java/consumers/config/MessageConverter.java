package consumers.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

public class MessageConverter extends Jackson2JsonMessageConverter {
    public MessageConverter() {
        super();
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        message.getMessageProperties().setContentType("application/json");
        return super.fromMessage(message);
    }
}