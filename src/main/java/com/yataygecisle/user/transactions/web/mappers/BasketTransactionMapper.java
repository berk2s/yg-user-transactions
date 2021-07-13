package com.yataygecisle.user.transactions.web.mappers;

import com.yataygecisle.user.transactions.domain.BasketTransaction;
import com.yataygecisle.user.transactions.web.models.BasketTransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.UUID;

@Mapper(imports = {UUID.class}, uses = {BasketItemTransactionMapper.class})
public interface BasketTransactionMapper {

    @Mappings({
            @Mapping(target = "transactionId", expression = "java( basketTransaction.getId().toString() )"),
            @Mapping(target = "userId", expression = "java( basketTransaction.getUserId().toString() )"),
            @Mapping(target = "basketId", expression = "java( basketTransaction.getBasketId().toString() )"),
            @Mapping(source = "basketItemTransactions", target = "basketItems"),
    })
    BasketTransactionDto basketTransactionToBasketTransactionDto(BasketTransaction basketTransaction);

    @Mappings({
            @Mapping(target = "transactionId", expression = "java( basketTransaction.getId().toString() )"),
            @Mapping(target = "userId", expression = "java( basketTransaction.getUserId().toString() )"),
            @Mapping(target = "basketId", expression = "java( basketTransaction.getBasketId().toString() )"),
            @Mapping(source = "basketItemTransactions", target = "basketItems"),
    })
    List<BasketTransactionDto> basketTransactionToBasketTransactionDto(List<BasketTransaction> basketTransaction);

}
