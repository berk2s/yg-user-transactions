package com.yataygecisle.user.transactions.web.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketItemTransactionDto {

    private String basketItemId;

    private Timestamp createdAt;

    private Timestamp lastModifiedAt;

}
