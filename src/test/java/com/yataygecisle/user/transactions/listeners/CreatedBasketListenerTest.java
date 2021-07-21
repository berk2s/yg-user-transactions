package com.yataygecisle.user.transactions.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yataygecisle.user.transactions.repository.BasketTransactionRepository;
import com.yataygecisle.user.transactions.web.controllers.IntegrationTest;
import com.yataygecisle.user.transactions.web.models.BasketDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.waitAtMost;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreatedBasketListenerTest extends IntegrationTest {

    @Autowired
    BasketTransactionRepository basketTransactionRepository;

    String token;
    String userId;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        token = createAccessToken();
        userId = parseTokenToUserId(token);
    }

    @DisplayName("Verify Created Basket Listener is Called")
    @Test
    void testCreatedBasketListener() throws Exception {

        BasketDto basket = triggerToCreatingBasket(token, userId);

        waitAtMost(10, TimeUnit.SECONDS)
                .untilAsserted(() -> {

                    assertThat(basketTransactionRepository.findAll().size())
                            .isNotEqualTo(0);

                });

    }

}