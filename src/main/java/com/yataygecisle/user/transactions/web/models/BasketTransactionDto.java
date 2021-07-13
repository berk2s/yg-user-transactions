package com.yataygecisle.user.transactions.web.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketTransactionDto {

    private String transactionId;

    private TransactionType transactionType;

    private UUID performedBy;

    private String userId;

    private String basketId;

    private Timestamp createdAt;

    private Timestamp lastModifiedAt;

    private List<BasketItemTransactionDto> basketItems;

}
