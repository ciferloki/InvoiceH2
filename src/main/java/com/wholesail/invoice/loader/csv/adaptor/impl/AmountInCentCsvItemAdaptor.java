package com.wholesail.invoice.loader.csv.adaptor.impl;

import com.wholesail.invoice.loader.csv.adaptor.CsvItemAdaptor;

public class AmountInCentCsvItemAdaptor implements CsvItemAdaptor {

    private static final int CENTS_IN_DOLLAR = 100;
    @Override
    public int getAmountValue(String amount) {
        Float amountInFloat = Float.parseFloat(amount);
        return Math.round(amountInFloat * CENTS_IN_DOLLAR);
    }

}
