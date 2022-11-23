package com.wholesail.invoice.loader.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByNames;
import lombok.Data;

import static com.wholesail.invoice.loader.csv.CsvProfile.PROFILE_GOLDEN_GATE_PRODUCE;
import static com.wholesail.invoice.loader.csv.CsvProfile.PROFILE_HAPPY_FRUITS;

@Data
public class CsvInvoiceItem {

    @CsvBindByNames({
            @CsvBindByName(column = "customer", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "buyer", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String customer_name;

    @CsvBindByNames({
            @CsvBindByName(column = "id", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "invoiceId", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String invoice_id;

    @CsvBindByNames({
            @CsvBindByName(column = "term_schedule", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "paymentTerm", profiles = PROFILE_HAPPY_FRUITS)
    })
    private int payment_term;

    @CsvBindByNames({
            @CsvBindByName(column = "amount", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "transAmountCents", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String amount;

    @CsvBindByNames({
            @CsvBindByName(column = "adjustment", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "adjustmentAmountCents", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String adjustment_amount;

    @CsvBindByNames({
            @CsvBindByName(column = "paid_amount", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "paidAmountCents", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String paid_amount;

    @CsvBindByNames({
            @CsvBindByName(column = "date", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "transDate", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String date_of_transaction;

    @CsvBindByNames({
            @CsvBindByName(column = "paid_at", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "paidAt", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String date_of_payment_in_full;

    @CsvBindByNames({
            @CsvBindByName(column = "items", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "lines", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String line_items;

    @CsvBindByNames({
            @CsvBindByName(column = "billing_address", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "billingAddress", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String billing_address;

    @CsvBindByNames({
            @CsvBindByName(column = "shipping_address", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "shippingAddress", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String shipping_address;

    @CsvBindByNames({
            @CsvBindByName(column = "note", profiles = PROFILE_GOLDEN_GATE_PRODUCE),
            @CsvBindByName(column = "memo", profiles = PROFILE_HAPPY_FRUITS)
    })
    private String memo;
}
