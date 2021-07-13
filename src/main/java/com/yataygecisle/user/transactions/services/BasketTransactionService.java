package com.yataygecisle.user.transactions.services;

import com.yataygecisle.user.transactions.web.models.BasketTransactionDto;

import java.util.List;
import java.util.UUID;

public interface BasketTransactionService {

    List<BasketTransactionDto> getUserTransactions(UUID userId);

}
