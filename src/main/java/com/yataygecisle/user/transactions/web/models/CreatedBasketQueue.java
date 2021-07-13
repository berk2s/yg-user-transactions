package com.yataygecisle.user.transactions.web.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatedBasketQueue implements Serializable {

    static final long serialVersionUID = 123123123123153123L;

    private String userID;

    private String performedBy;

    private String basketName;

    private String basketId;

    private Set<String> basketItems = new HashSet<>();

}
