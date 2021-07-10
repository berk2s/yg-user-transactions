package com.yataygecisle.user.transactions.components;

import com.yataygecisle.commons.models.Queues;
import com.yataygecisle.user.transactions.web.models.BasketDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreatedBasketReceiver implements RabbitListenerConfigurer {

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = Queues.CREATED_BASKET)
    public void receivedMessage(BasketDto basket) {
        log.info("Basket Received is.. {}", basket);
    }

}
