package com.wholesail.invoice.loader.csv.adaptor;

import com.wholesail.invoice.data.entity.Invoice;
import com.wholesail.invoice.loader.csv.CsvInvoiceItem;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public interface CsvItemAdaptor {

    DateTimeFormatter DATE_TIME_FORMATTER =
            new DateTimeFormatterBuilder()
                    .appendOptional(DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.US))
                    .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US))
                    .toFormatter();

    /**
     * Create a persistent Invoice item from a csv invoice item.
     * @param csvItem
     * @return
     */
    default Invoice toInvoiceEntity(CsvInvoiceItem csvItem) throws ParseException {
        Invoice invoiceEntity = new Invoice();

        invoiceEntity.setInvoiceId(csvItem.getInvoice_id());

        invoiceEntity.setCustomerName(csvItem.getCustomer_name());

        invoiceEntity.setLineItems(csvItem.getLine_items());
        invoiceEntity.setMemo(csvItem.getMemo());
        invoiceEntity.setBillingAddress(csvItem.getBilling_address());
        invoiceEntity.setShippingAddress(csvItem.getShipping_address());

        invoiceEntity.setPaymentTerm(csvItem.getPayment_term());

        // Set amounts
        if(StringUtils.isNotBlank(csvItem.getAmount())) {
            invoiceEntity.setAmountInCents(getAmountValue(csvItem.getAmount()));
        }
        if(StringUtils.isNotBlank(csvItem.getAdjustment_amount())) {
            invoiceEntity.setAdjustmentAmountInCents(getAmountValue(csvItem.getAdjustment_amount()));
        }
        if(StringUtils.isNotBlank(csvItem.getPaid_amount())) {
            invoiceEntity.setPaidAmountInCents(getAmountValue(csvItem.getPaid_amount()));
        }

        // Set dates
        if(StringUtils.isNotBlank(csvItem.getDate_of_transaction())) {
            invoiceEntity.setDateOfTransaction(getDateValue(csvItem.getDate_of_transaction()));
        }
        if(StringUtils.isNotBlank(csvItem.getDate_of_payment_in_full())) {
            invoiceEntity.setDateOfPaymentInFull(getDateValue(csvItem.getDate_of_payment_in_full()));
        }
        return invoiceEntity;
    }

    default int getAmountValue(String amount) {
        return Integer.parseInt(amount);
    }

    default LocalDate getDateValue(String date) throws ParseException {
        return LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

}
