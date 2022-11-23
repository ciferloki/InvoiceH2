package com.wholesail.invoice.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Balance {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "outstanding_balance_in_cents")
    private int outstandingBalanceInCents;

    @Column(name = "past_due_balance_in_cents")
    private int pastDueBalanceInCents;
}
