package com.wholesail.invoice.data.repository;

import com.wholesail.invoice.data.entity.Invoice;
import com.wholesail.invoice.data.query.BalanceInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(value =
            "SELECT CUSTOMER_NAME AS customerName," +
            "SUM(AMOUNT_IN_CENTS + ADJUSTMENT_AMOUNT_IN_CENTS - PAID_AMOUNT_IN_CENTS) AS outstandingBalance," +
            "SUM(" +
            "CASE WHEN DATEADD(DAY, PAYMENT_TERM, DATE_OF_TRANSACTION) < '2022-3-31' " +
                    "THEN AMOUNT_IN_CENTS + ADJUSTMENT_AMOUNT_IN_CENTS - PAID_AMOUNT_IN_CENTS Else 0 " +
            "END) AS pastDueBalance " +
            "FROM INVOICE " +
            "GROUP BY CUSTOMER_NAME;"
            , nativeQuery = true)
    List<BalanceInterface> findBalanceByCustomer();

    List<Invoice> findByInvoiceId(String invoiceId);
}
