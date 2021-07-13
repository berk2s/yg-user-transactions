package com.yataygecisle.user.transactions.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Immutable
@Embeddable
public class BasketItemTransaction {

    @Column(name = "basket_item_id")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID basketItemId;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp lastModifiedAt;

}
