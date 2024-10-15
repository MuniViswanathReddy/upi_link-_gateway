package com.bank.baroda.businessobject;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDetails {
    private String accountHolderName;
    private String accountType;
    private String phoneNo;
    private String branch;
    private String panCard;
    private float balance;
}

