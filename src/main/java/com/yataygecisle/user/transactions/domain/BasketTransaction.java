package com.yataygecisle.user.transactions.domain;

import com.yataygecisle.user.transactions.web.models.BasketTransactionType;
import com.yataygecisle.user.transactions.web.models.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("BASKET")
@Immutable
@Entity
public class BasketTransaction extends Transaction {

    @Column(name = "basket_name")
    private String basketName;

    @Column(name = "basketId")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID basketId;

    @Enumerated(EnumType.ORDINAL)
    private BasketTransactionType basketTransactionType;

    @ElementCollection
    @CollectionTable(
            name = "basket_transaction_items",
            joinColumns = @JoinColumn(name = "basket_transaction_id")
    )
    private List<BasketItemTransaction> basketItemTransactions = new ArrayList<>();

}
