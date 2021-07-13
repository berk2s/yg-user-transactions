package com.yataygecisle.user.transactions.web.controllers;

import com.yataygecisle.user.transactions.services.BasketTransactionService;
import com.yataygecisle.user.transactions.web.models.BasketItemTransactionDto;
import com.yataygecisle.user.transactions.web.models.BasketTransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping(BasketTransactionController.ENDPOINT)
@RestController
public class BasketTransactionController {

    public static final String ENDPOINT = "/basket";

    private final BasketTransactionService basketTransactionService;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<List<BasketTransactionDto>> getUserBasketTransactions(@PathVariable UUID userId) {
        return new ResponseEntity<>(basketTransactionService.getUserTransactions(userId), HttpStatus.OK);
    }

}
