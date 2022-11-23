package com.wholesail.invoice.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;


    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "invoice_id")
    private String invoiceId;

    @Column(name = "payment_term")
    private int paymentTerm;

    @Column(name = "amount_in_cents")
    private int amountInCents;

    @Column(name = "adjustment_amount_in_cents")
    private int adjustmentAmountInCents;

    @Column(name = "paid_amount_in_cents")
    private int paidAmountInCents;

    @Column(name = "date_of_transaction")
    private LocalDate dateOfTransaction;

    @Column(name = "date_of_payment_in_full")
    private LocalDate dateOfPaymentInFull;

    @Column(name = "line_items")
    private String lineItems;

    @Column(name = "billing_address")
    private String billingAddress;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "memo")
    private String memo;
}
