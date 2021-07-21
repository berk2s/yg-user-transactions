package com.yataygecisle.user.transactions.services.impl;

import com.yataygecisle.user.transactions.domain.BasketTransaction;
import com.yataygecisle.user.transactions.repository.BasketTransactionRepository;
import com.yataygecisle.user.transactions.repository.TransactionRepository;
import com.yataygecisle.user.transactions.services.BasketTransactionService;
import com.yataygecisle.user.transactions.web.mappers.BasketTransactionMapper;
import com.yataygecisle.user.transactions.web.models.BasketItemTransactionDto;
import com.yataygecisle.user.transactions.web.models.BasketTransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BasketTransactionServiceImpl implements BasketTransactionService {

    private final BasketTransactionRepository basketTransactionRepository;
    private final BasketTransactionMapper basketTransactionMapper;

    @Override
    public List<BasketTransactionDto> getUserTransactions(UUID userId) {

        List<BasketTransaction> basketTransactions = basketTransactionRepository
                .findAllByUserId(userId);

        log.info("User's transactions are listed by given user id [userId: {}]", userId.toString());

        return basketTransactionMapper.basketTransactionToBasketTransactionDto(basketTransactions);
    }
}
