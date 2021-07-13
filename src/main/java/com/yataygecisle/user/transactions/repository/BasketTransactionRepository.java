package com.yataygecisle.user.transactions.repository;

import com.yataygecisle.user.transactions.domain.BasketTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BasketTransactionRepository extends JpaRepository<BasketTransaction, UUID> {

    List<BasketTransaction> findAllByUserId(UUID userId);

}
