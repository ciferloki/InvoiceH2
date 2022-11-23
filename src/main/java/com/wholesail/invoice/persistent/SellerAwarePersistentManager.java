package com.wholesail.invoice.persistent;

import com.wholesail.invoice.data.entity.Balance;
import com.wholesail.invoice.data.entity.Invoice;
import com.wholesail.invoice.data.entity.Seller;
import com.wholesail.invoice.data.query.BalanceInterface;
import com.wholesail.invoice.data.repository.BalanceRepository;
import com.wholesail.invoice.data.repository.InvoiceRepository;
import com.wholesail.invoice.data.repository.SellerRepository;
import com.wholesail.invoice.exception.InvalidStateException;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * This class persist a list of {@link com.wholesail.invoice.data.entity.Invoice}
 * into DB, assuming the seller records already exist.
 */
@Component
public class SellerAwarePersistentManager {

    private static final Logger logger = LoggerFactory.getLogger(SellerAwarePersistentManager.class);
    @Autowired
    private BalanceRepository balanceRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public void persistInvoices(String sellerProfile, List<Invoice> invoices) throws InvalidStateException {
        Validate.notBlank(sellerProfile);
        Seller seller = getSeller(sellerProfile);
        logger.info("Found Seller record with id:{}, name: {}", seller.getId(), seller.getName());

        logger.info("Persisting invoices...");
        persistInvoices(seller, invoices);
        logger.info("Done. {} invoices persisted.", invoices.size());

        logger.info("Propagating Balance table...");
        List<Balance> balances = calculateBalances(seller);
        logger.info("{} balance entries calculated.", balances.size());

        logger.info("Persisting balances...");
        persistBalances(seller, balances);
    }

    private void persistBalances(Seller seller, List<Balance> balances) {
        persistBalances(balances);
    }

    private void persistBalances(List<Balance> balances) {
        for (Balance balance : balances) {
            List<Balance> existingBalancesWithCustomerName =
                    balanceRepository.findBalanceByCustomerName(balance.getCustomerName());
            if (existingBalancesWithCustomerName.isEmpty()) {
                balanceRepository.saveAndFlush(balance);
            } else {
                // We made an assumption only one balance record would exist for a single customer
                Balance existingBalance = existingBalancesWithCustomerName.stream().findFirst().get();
                existingBalance.setOutstandingBalanceInCents(balance.getOutstandingBalanceInCents());
                existingBalance.setPastDueBalanceInCents(balance.getPastDueBalanceInCents());
                balanceRepository.saveAndFlush(existingBalance);
            }
        }

    }

    private List<Balance> calculateBalances(Seller seller) {
        List<BalanceInterface> balanceInterfaces = invoiceRepository.findBalanceByCustomer();
        List<Balance> balanceEntities = new ArrayList<>();
        for (BalanceInterface balanceInterface : balanceInterfaces) {
            Balance balanceEntity = new Balance();
            balanceEntity.setSeller(seller);
            balanceEntity.setCustomerName(balanceInterface.getCustomerName());
            balanceEntity.setOutstandingBalanceInCents(balanceInterface.getOutstandingBalance());
            balanceEntity.setPastDueBalanceInCents(balanceInterface.getPastDueBalance());
            balanceEntities.add(balanceEntity);
        }
        return balanceEntities;
    }

    private void persistInvoices(Seller seller, List<Invoice> invoices) {
        invoices.forEach(invoice -> fillSellerInfo(seller, invoice));
        persistInvoices(invoices);
    }

    private void persistInvoices(List<Invoice> invoices) {
        for (Invoice invoice : invoices) {
            // TODO: If an invoice is found with the same ID, do nothing here?
            if (invoiceRepository.findByInvoiceId(invoice.getInvoiceId()).isEmpty()) {
                invoiceRepository.saveAndFlush(invoice);
            }
        }
    }

    private void fillSellerInfo(Seller seller, Invoice invoice) {
        invoice.setSeller(seller);
    }

    private Seller getSeller(String sellerProfile) throws InvalidStateException {
        List<Seller> sellers = sellerRepository.findByName(sellerProfile);
        if (sellers.isEmpty()) {
            logger.error("No record found for seller by name {}", sellerProfile);
            throw new InvalidStateException();
        }
        return sellers.stream().findFirst().get();
    }
}
