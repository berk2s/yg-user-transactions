package com.yataygecisle.user.transactions.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yataygecisle.user.transactions.listeners.CreatedBasketListener;
import com.yataygecisle.user.transactions.repository.BasketTransactionRepository;
import com.yataygecisle.user.transactions.web.models.AddBasketItemDto;
import com.yataygecisle.user.transactions.web.models.BasketDto;
import com.yataygecisle.user.transactions.web.models.BasketItemDto;
import com.yataygecisle.user.transactions.web.models.CreateBasketDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.awaitility.Awaitility.waitAtMost;


public class BasketTransactionControllerTest extends IntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BasketTransactionRepository basketTransactionRepository;

    String token;
    String userId;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        token = createAccessToken();
        userId = parseTokenToUserId(token);
    }


    @DisplayName("Get Basket Transactions By Given User Id")
    @Test
    void getBasketTransactionsByGivenUserId() throws Exception {
        BasketDto basket = triggerToCreatingBasket(token, userId);

        waitAtMost(10, TimeUnit.SECONDS)
                .untilAsserted(() -> {
                    assertThat(basketTransactionRepository.findAll().size())
                            .isNotEqualTo(0);
                });

        mockMvc.perform(get(BasketTransactionController.ENDPOINT + "/" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..transactionId").isNotEmpty())
                .andExpect(jsonPath("$..userId", anyOf(hasItem(userId))))
                .andExpect(jsonPath("$..basketId", anyOf(hasItem(basket.getBasketId()))))
                .andExpect(jsonPath("$..createdAt").isNotEmpty())
                .andExpect(jsonPath("$..lastModifiedAt").isNotEmpty())
                .andExpect(jsonPath("$..basketItems[*].basketItemId", anyOf(hasItem(basket.getBasketItems().get(0).getBasketItemId()))));
    }


}
