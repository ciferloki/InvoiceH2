package com.wholesail.invoice.data.query;

public interface BalanceInterface {
    String getCustomerName();
    Integer getOutstandingBalance();
    Integer getPastDueBalance();
}
