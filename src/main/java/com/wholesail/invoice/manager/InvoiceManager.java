package com.wholesail.invoice.manager;

import com.wholesail.invoice.data.entity.Invoice;
import com.wholesail.invoice.data.repository.InvoiceRepository;
import com.wholesail.invoice.loader.InvoiceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceManager {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceManager.class);

    @Autowired
    @Qualifier("CSVFileInvoiceLoader")
    private InvoiceLoader invoiceLoader;

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<String> listAvailableRawInvoiceResources() {
        logger.info("Finding available resources...");
        return invoiceLoader.listAvailableResources();
    }

    public void loadResources(final String[] resourceIdentifiers) {
        if(resourceIdentifiers == null) {
            return;
        }
        for(final String ri : resourceIdentifiers) {
            logger.info("Loading invoice from {}...", ri);
            try {
                invoiceLoader.loadResourceByName(ri);
            } catch (Exception e) {
                logger.error("Error while loading invoice from {}", ri, e);
            }
        }
    }

    public List<Invoice> getAllInvoices() {
        logger.info("Loading all persisted invoices...");
        return invoiceRepository.findAll();
    }

}
