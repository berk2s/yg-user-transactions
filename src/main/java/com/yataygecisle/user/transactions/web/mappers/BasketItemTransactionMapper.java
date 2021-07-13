package com.yataygecisle.user.transactions.web.mappers;

import com.yataygecisle.user.transactions.domain.BasketItemTransaction;
import com.yataygecisle.user.transactions.web.models.BasketItemTransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(imports = {UUID.class})
public interface BasketItemTransactionMapper {

    @Mapping(target = "basketItemId", expression = "java( basketItemTransaction.getBasketItemId().toString() )")
    BasketItemTransactionDto basketItemTransactionToBasketItemTransactionDto(BasketItemTransaction basketItemTransaction);

}
