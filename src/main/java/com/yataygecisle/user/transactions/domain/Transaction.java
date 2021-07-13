package com.yataygecisle.user.transactions.domain;

import com.yataygecisle.user.transactions.web.models.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
@Entity
@Table(name = "USER_TRANSACTIONS")
public abstract class Transaction extends BaseEntity {

    @Enumerated(EnumType.ORDINAL)
    private TransactionType transactionType;

    @Column(name = "user_id")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID userId;

    @Column(name = "performed_by")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID performedBy;

}
