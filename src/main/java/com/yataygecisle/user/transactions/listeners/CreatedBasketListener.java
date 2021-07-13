package com.yataygecisle.user.transactions.listeners;

import com.yataygecisle.commons.models.Queues;
import com.yataygecisle.user.transactions.domain.BasketItemTransaction;
import com.yataygecisle.user.transactions.domain.BasketTransaction;
import com.yataygecisle.user.transactions.repository.BasketTransactionRepository;
import com.yataygecisle.user.transactions.web.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class CreatedBasketListener {

    private final BasketTransactionRepository basketTransactionRepository;

    @RabbitListener(queues = Queues.CREATED_BASKET)
    public void receivedMessage(CreatedBasketQueue basket) {
        BasketTransaction basketTransaction = new BasketTransaction();
        basketTransaction.setBasketName(basket.getBasketName());
        basketTransaction.setUserId(UUID.fromString(basket.getUserID()));
        basketTransaction.setPerformedBy(UUID.fromString(basket.getPerformedBy()));
        basketTransaction.setBasketId(UUID.fromString(basket.getBasketId()));
        basketTransaction.setTransactionType(TransactionType.CREATED);
        basketTransaction.setBasketTransactionType(BasketTransactionType.CREATED_BASKET);

        basket.getBasketItems().forEach(basketItem -> {
            BasketItemTransaction basketItemTransaction = new BasketItemTransaction();
            basketItemTransaction.setBasketItemId(UUID.fromString(basketItem));

            basketTransaction.getBasketItemTransactions().add(basketItemTransaction);
        });

        BasketTransaction savedBaskedTransaction = basketTransactionRepository.save(basketTransaction);

        log.info("Created Basket transaction has been saved [transactionId: {}]", savedBaskedTransaction.getId().toString());
    }

}
