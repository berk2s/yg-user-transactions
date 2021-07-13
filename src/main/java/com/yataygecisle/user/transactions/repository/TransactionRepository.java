package com.yataygecisle.user.transactions.repository;

import com.yataygecisle.user.transactions.domain.BasketTransaction;
import com.yataygecisle.user.transactions.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("select t from Transaction t where TYPE(t) = 'BASKET'")
    List<BasketTransaction> findAllByUserId(UUID userId);

}
